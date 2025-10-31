package kr.co.wikibook.basicgallery.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountJoinRequest { // 회원 가입용 dto
    private String name;    // 회원명
    private String loginId; // 로그인 아이디
    private String loginPw; // 로그인 패스워드
}
