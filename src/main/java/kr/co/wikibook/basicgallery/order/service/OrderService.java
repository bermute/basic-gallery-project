package kr.co.wikibook.basicgallery.order.service;

import kr.co.wikibook.basicgallery.order.dto.OrderRead;
import kr.co.wikibook.basicgallery.order.dto.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
//    //주문 목록 조회
//    List<OrderRead> findAll(Integer memberId);
//
//    // 주문 상세 조회
//    OrderRead find(Integer id, Integer memberId);
//
//    // 주문 내역 저장
//    void order(OrderRequest orderReq, Integer memberId);

    //페이지 네이션용

    // 주문 목록 조회
    Page<OrderRead> findAll(Integer memberId, Pageable pageable); // ③

    // 주문 상세 조회
    OrderRead find(Integer id, Integer memberId);

    // 주문 내역 저장
    void order(OrderRequest params, Integer memberId);
}
