package kr.co.wikibook.basicgallery.order.dto;

import kr.co.wikibook.basicgallery.item.dto.ItemRead;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderRead {
    private Integer id; // 주문 아이디
    private String name; // 주문자명
    private String address; // 배송 주소
    private String payment; // 결제 수단
    private Long amount; // 결제 금액
    private LocalDateTime created; // 생성 일자
    private List<ItemRead> items; // 상품 목록
}
