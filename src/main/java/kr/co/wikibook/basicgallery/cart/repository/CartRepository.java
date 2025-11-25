package kr.co.wikibook.basicgallery.cart.repository;

import kr.co.wikibook.basicgallery.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    // 인터페이스 확장, 제네릭 매개변수로 장바구니 엔티티 Cart와 이엔티티 아이디의 데이터 타입인 Integer 를 지정합니다.

    //장바구니 목록 조회(특정 회원)
    List<Cart> findAllByMemberId(Integer memberId);
    // 특정 회원의 장바구니 목록을 조회하는 메서드 입니다. 매개변수로 회원 아이디를 받습니다.

    // 장바구니 정보 조회(특정 회원 및 특정 상품)
    Optional<Cart> findByMemberIdAndItemId(Integer memberId, Integer itemId);//

    // 장바구니 삭제(특정 회원)
    void deleteByMemberId(Integer memberId);

    // 장바구니 삭제(특정 회원 및 특정 상품)
    void deleteByMemberIdAndItemId(Integer memberId, Integer itemId);
}
