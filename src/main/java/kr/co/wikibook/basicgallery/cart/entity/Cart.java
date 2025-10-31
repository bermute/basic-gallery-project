package kr.co.wikibook.basicgallery.cart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 아이디 필드입니다. 애너테이션을 지정해 해당 필드가 기본키이며,
                        // 테이블의 기본키 컬럼과 매핑된다는 것을 나타냅니다.

    @Column(nullable = false)
    private Integer memberId; // 회원 아이디 필드입니다. 저장해 해당 필드가 테이블 컬럼과 매핑된다는 것을 나타냅니다.

    @Column(nullable = false)
    private Integer itemId; // 상품 아이디 필드입니다. column 애너테이션을 지정해 해당 필드가 테이블 칼럼과 매핑된다는 것을
                            // 나타냅니다. 그리고 null 을 허용하지 않게 합니다.

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created; // 생성 일시 필드 입니다.

    public Cart(){ //클래스의 생성자입니다. 매개변수가 없는 생성자를 추가하고, 매개변수로 회원 아이디와 상품 아이디를 받는 생성자도
                    // 추가 합니다.

    }

    public Cart(Integer memberId, Integer itemId){ //
        this.memberId = memberId;
        this.itemId = itemId;
    }
}
