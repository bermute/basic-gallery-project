package kr.co.wikibook.basicgallery.cart.dto;

import kr.co.wikibook.basicgallery.cart.entity.Cart;
import lombok.Getter;

@Getter
public class CartRequest {

    private Integer itemId;// 상품 필드

    //엔티티 객체로 변환
    public Cart toEntity(Integer memberId){// 장바구니  상품 담기 요청 dto
        return new Cart(memberId, itemId);
    }
}
