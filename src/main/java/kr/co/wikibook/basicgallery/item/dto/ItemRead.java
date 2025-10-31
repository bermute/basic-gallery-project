package kr.co.wikibook.basicgallery.item.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemRead {
    private Integer id; // 상품아이디
    private String name; // 상품명
    private String imgPath; // 사진경로
    private Integer price; // 가격 필드
    private Integer discountPer; // 상품의 할인율
}
