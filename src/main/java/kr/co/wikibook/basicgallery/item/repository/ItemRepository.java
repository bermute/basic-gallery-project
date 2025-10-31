package kr.co.wikibook.basicgallery.item.repository;

import kr.co.wikibook.basicgallery.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 제네릭 매개변수로 상품 엔티티 item과 이 엔티티 아이디의 데이터 타입인 Integer를 지정합니다.
public interface ItemRepository extends JpaRepository<Item, Integer> {
    //여러 아이디로 상품을 조회
    //여러 상품 아이디로 상품 데이터를 조회하는 메서드입니다. 이름 끝 부분의 IN은 매개변수의 값이 배열이나 리스트 타입일 때 해당
    //값을 모두 포함하는 조건을 의미합니다. 리턴 타입으로 상품 엔티티 리스트를 지정합니다.
    List<Item> findAllByIdIn(List<Integer> ids);
}
