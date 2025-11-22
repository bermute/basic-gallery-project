package kr.co.wikibook.basicgallery.cart.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartRead {
    private Integer id; // 장바구니 데이터의 아이디 필드
    private Integer itemId; // 장바구니에 담긴 상품 데이터의 아이디 필드
}
