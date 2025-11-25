import {defineStore} from 'pinia'

export const useAccountStore = defineStore("account", { // pinia 에서 제공하는 definestore 메서드를 호출
    state: () => ({
        checked: false, // 로그인 체크 여부 프로퍼티
        loggedIn: false, // 로그인 여부를 나타내는 프로퍼티
    }),
    actions: {
        setChecked(val) {  // 로그인 체크 여부 값을 수정하는 메서드
            this.checked = val;
        },
        setLoggedIn(val) {  // 사용자의 로그인 여부 값을 수정 하는 메서드
            this.loggedIn = val;
        },
    },
});