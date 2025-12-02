import axios from 'axios';
import {useAccountStore} from "@/stores/account"; // 계정 스토어 객체 생성 시 사용하는 메서드

const instance = axios.create(); 

// 인터셉터(응답 시)
instance.interceptors.response.use((res) => { 
    return res;
}, async (err) => {
    switch (err.response.status) {
        case 400:
            window.alert('잘못된 요청입니다.');
            break;

        case 401:// 액세스 토큰이 만료된 것일 수 있으니 쿠키에 있는 리프레시 토큰으로 액세스 토큰을 다시 요청 -> 그다음 액세스 토큰을 다시 받았으면 토큰을 교체해서 다시 http 요청을 수행
            const config = err.config;
            if (config.retried) { // 재요청 여부
                window.alert("권한이 없습니다.");
                window.location.replace("/");
                return;
            }
            
            // (쿠키에 있는) 리프레시 토큰으로 액세스 토큰 요청
            const res = await axios.get("/v1/api/account/token");

            // 액세스 토큰
            const accessToken = res.data;

            // 계정 스토어
            const accountStore = useAccountStore();

            // 계정 스토어의 액세스 토큰 변경
            accountStore.setAccessToken(accessToken);

            // 요청 액세스 토큰 교체
            config.headers.authorization = `Bearer ${accountStore.accessToken}`;

            // 중복 재요청 방지를 위한 프로퍼티 추가
            config.retried = true;

            // 재요청
            return instance(config);

     
        case 500:
            window.alert('오류가 있습니다. 관리자에게 문의해주세요.');
            break;
    }
    return Promise.reject(err);
});

// HTTP 요청 설정 생성
const generateConfig = () => { // 액세스 토큰이 있는 경우에는 인증 프로퍼티가 포함된 헤더가 있는 객체를 리턴하고, 없는 경우에는 빈 객체를 리턴
    // 계정 스토어
    const accountStore = useAccountStore();

    if (accountStore.accessToken) {
        return {
            headers: {authorization: `Bearer ${accountStore.accessToken}`}
        };
    }

    return {};
};


export default {
    get(url, params) { // axios 객체의 메서드를 호출해 http get 요청을 하는 메서드 호출시 generateConfig 메서드를 호출 해서 인수로 입력
        const config = generateConfig();
        config.params = params;
        return instance.get(url, config);
    },
    post(url, params) { // generateConfig 메서드를 호출해서 인수로 입력
        return instance.post(url, params, generateConfig());
    },
    put(url, params) { // generateConfig 메서드를 호출해서 인수로 입력
        return instance.put(url, params, generateConfig());
    },
    delete(url) { // ⑦
        return instance.delete(url, generateConfig());
    },
};
