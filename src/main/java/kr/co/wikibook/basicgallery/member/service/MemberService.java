package kr.co.wikibook.basicgallery.member.service;

import kr.co.wikibook.basicgallery.member.entity.Member;

public interface MemberService {

    //회원 데이터 저장
    // 회원 데이터를 저장하는 메서드입니다.
    // 매개변수로 회원의 이름, 로그인 아이디 , 로그인 패스워드를 받습니다.
    void save(String name, String loginId, String loginPw);

    //회원 데이터 조회
    // 회원 데이터를 조회하는 메서드입니다.
    // 매개변수로 로그인 아이디와 패스워드를 받습니다.
    // 리턴 타입으로 회원 엔티티를 지정합니다.
    Member find(String loginId, String loginPw);

    // 회원 데이터 조회
    Member find(String loginId);
}
