<script setup>
import {useAccountStore} from "@/stores/account";
import {logout} from "@/services/accountService";
import {useRouter} from "vue-router";


//계정 스토어
const accountStore = useAccountStore();

//라우터 객체
const router = useRouter();

//로그아웃
const logoutAccount = async () => { // 로그아웃 처리 메서드
    const res = await logout();
    if(res.status === 200){
        accountStore.setAccessToken(""); // 로그아웃을 위한 logoutAccount메서드 수정: 로그아웃 성공 시 계정 스토어의 액세스 토큰 값을 초기화 해서 이후의 http 요청에서 토큰이 사용되지 않게 한다.
        accountStore.setLoggedIn(false);
        await router.push("/");
    }
}
</script>

<template>
    <header>
        <div class="navbar navbar-dark bg-dark text-white shadow-sm">
            <div class="container"> 
                <router-link to = "/" class="navbar-brand"> 
                    <strong>Gallery</strong>
                </router-link>
                <div class="menus d-flex gap-3">
                    <template v-if="!accountStore.loggedIn">
                        <router-link to="/login">로그인</router-link>
                        <router-link to="/join">회원가입</router-link>
                    </template>
                    <template v-else>
                        <a @click="logoutAccount()">로그아웃</a>
                        <router-link to="/orders"> 주문 내역</router-link>
                        <router-link to="/cart">장바구니</router-link>
                    </template>
                </div>
            </div>
        </div>
    </header>
</template>

<style lang="scss">
header{
    .menus{
        a{ 
            cursor: pointer;
            color: #fff;
            text-decoration: none;
        }
    }
}
</style>