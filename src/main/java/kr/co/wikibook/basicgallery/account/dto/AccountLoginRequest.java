package kr.co.wikibook.basicgallery.account.dto;

import lombok.Getter;

@Getter // 모든 필드의 게터를 자동으로 생성해주는 애너테이션
public class AccountLoginRequest { // 로그인 용 dto
    private String loginId; //로그인을 요청할 때 사용되는 로그인 아이디필드
    private String loginPw; // 로그인을 요청할때 사용되는 로그인 패스워드

}
