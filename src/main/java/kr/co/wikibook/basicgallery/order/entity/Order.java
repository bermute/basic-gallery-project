package kr.co.wikibook.basicgallery.order.entity;

import jakarta.persistence.*;
import kr.co.wikibook.basicgallery.order.dto.OrderRead;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 아이디 필드 기본키

    @Column(nullable = false)
    private Integer memberId; // 주문자 회원 아이디 필드

    @Column(length = 50, nullable = false)
    private String name; // 주문자명 필드

    @Column(length = 500, nullable = false)
    private String address; // 배송 주소 필드

    @Column(length = 10, nullable = false)
    private String payment; // 결제 수단 필드

    @Column(length = 50)
    private String cardNumber; // 카드 번호 필드

    @Column(nullable = false)
    private Long amount; // 최종 결제 금액 필드

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created; // 생성 일시 필드

    public Order(){ // 생성자
    }

    public Order(Integer memberId, String name, String address, String payment, String cardNumber, Long amount) {
        // 생성자
        this.memberId = memberId;
        this.name = name;
        this.address = address;
        this.payment = payment;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    // 주문 조회 DTO로 변환
    // 엔티티 객체를 주문 조회 dto 로 변환 /데이터 조회용
    public OrderRead toRead(){
        return OrderRead.builder()
                .id(id)
                .name(name)
                .address(address)
                .payment(payment)
                .amount(amount)
                .created(created)
                .build();
    }
}
