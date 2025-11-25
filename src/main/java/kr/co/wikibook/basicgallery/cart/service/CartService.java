package kr.co.wikibook.basicgallery.cart.service;

import kr.co.wikibook.basicgallery.cart.dto.CartRead;
import kr.co.wikibook.basicgallery.cart.entity.Cart;

import java.util.List;

public interface CartService {

    // 장바구니 상품 데이터 목록 조회(특정 회원)
    List<CartRead> findAll(Integer memberId); // 특정 회원의 전체 장바구니 데이터 목록 조회 메서드

    //장바구니 상품 데이터 조회 (특정 회원의 특정 상품)
    CartRead find(Integer memberId, Integer itemId); // 특정 상품을 조회하는 메서드

    // 장바구니 상품 데이터 전체 삭제(특정 회원)
    void removeAll(Integer memberId); // 특정 회원의 장바구니에 있는 전체 데이터 삭제 메서드

    // 장바구니 상품 데이터 삭제(특정 회원의 특정 상품)
    void remove(Integer memberId, Integer itemId); // 특정 회원의 장바구니에 있는 데이터 삭제 메서드

    // 장바구니 데이터 저장(특정 회원의 특정 상품)
    void save(Cart cart); // 특정 회원의 장바구니 상품 저장 메서드
}
