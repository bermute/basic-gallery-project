import axios from "axios";

// 회원가입
export const join = (args) => {// 회원 가입 처라 메서드
    return axios.post("/v1/api/accounts/join", args).catch(e => e.response);
};

// 로그인
export const login = (args) => {// 로그인 처리하는 메서드
    return axios.post("/v1/api/accounts/login", args).catch(e => e.response);
};

// 로그인 여부 확인
export const check = () => {// 로그인 여부 메서드
    return axios.get("/v1/api/account/check").catch(e => e.response);
};

// 로그아웃
export const logout = () => { // 로그아웃 처리 메서드
    return axios.post("/v1/api/accounts/logout").catch(e => e.response);
};
