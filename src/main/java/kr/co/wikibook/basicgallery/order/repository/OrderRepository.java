package kr.co.wikibook.basicgallery.order.repository;

import kr.co.wikibook.basicgallery.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> { // 1

//    // 주문 목록 조회
//    List<Order> findAllByMemberIdOrderByIdDesc(Integer memberId); //
//
//    // 주문 정보 조회( 특정 아이디 및 특정 회원 )
//    Optional<Order> findByIdAndMemberId(Integer id, Integer memberId); //

    // 주문 목록 조회
    Page<Order> findAllByMemberIdOrderByIdDesc(Integer memberId, Pageable pageable); // ③

    // 주문 정보 조회(특정 아이디 및 특정 회원)
    Optional<Order> findByIdAndMemberId(Integer id, Integer memberId);
}


