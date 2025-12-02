package kr.co.wikibook.basicgallery.member.repository;

import kr.co.wikibook.basicgallery.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 제네릭 매개변수로 회원 엔티티 Member와 이 엔티티 아이디의 데이터 타입인 Integer를 지정합니다.
public interface MemberRepository extends JpaRepository<Member, Integer> {
    //아이디와 패스워드로 회원 정보를 조회
    //회원 정보 데이터를 조회하는 메서드입니다.
    //매개변수로 로그인 아이디와 패스워드를 받습니다.
    // 조회된 데이터가 없을 수 있기 때문에 리턴 타입을 Optional 로 지정합니다.
    // Optional 을 사용하면 null을 안전하게 처리하고 명시적으로 값이 없음을 나타낼 수 있다는 장점이 있습니다.
    Optional<Member> findByLoginIdAndLoginPw(String loginId, String loginPw);


    // 아이디로 회원 정보 조회
    Optional<Member> findByLoginId(String loginId);
}
