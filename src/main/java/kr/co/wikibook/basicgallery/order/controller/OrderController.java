package kr.co.wikibook.basicgallery.order.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.wikibook.basicgallery.account.helper.AccountHelper;
import kr.co.wikibook.basicgallery.order.dto.OrderRead;
import kr.co.wikibook.basicgallery.order.dto.OrderRequest;
import kr.co.wikibook.basicgallery.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class OrderController {
    private final AccountHelper accountHelper;
    private final OrderService orderService;

    // 주문 목록 조회
    @GetMapping("/api/orders")
    public ResponseEntity<?> readAll(HttpServletRequest req, Pageable pageable){ //로그인 회원의 전체 주문목록을 조회
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        //주문 목록
//        List<OrderRead> orders = orderService.findAll(memberId);
        Page<OrderRead> orders = orderService.findAll(memberId, pageable);


        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // 주문 상세 조회
    @GetMapping("/api/orders/{id}")
    public ResponseEntity<?> read(HttpServletRequest req, @PathVariable Integer id){  // 로그인 회원의 특정 주문 내역을 조회하는 메서드 입니다.
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        //주문 조회
        OrderRead order = orderService.find(id,memberId);

        if (order == null){ // 주문 데이터가 없는 경우
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // 주문 삽입
    @PostMapping("/api/orders")
    public ResponseEntity<?> add(HttpServletRequest req, @RequestBody OrderRequest orderReq){ // 신규 주문 내역을 추가하는 메서드 입니다.
        //로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        //주문 입력
        orderService.order(orderReq, memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
