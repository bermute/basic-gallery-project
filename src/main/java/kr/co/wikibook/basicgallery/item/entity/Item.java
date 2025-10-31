package kr.co.wikibook.basicgallery.item.entity;

import jakarta.persistence.*;
import kr.co.wikibook.basicgallery.item.dto.ItemRead;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String imgPath;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer discountPer;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    public ItemRead toRead() {
        return ItemRead.builder()
                .id(id)
                .name(name)
                .price(price)
                .discountPer(discountPer)
                .imgPath(imgPath)
                .build();
    }
}
