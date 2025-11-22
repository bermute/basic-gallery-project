package kr.co.wikibook.basicgallery.cart.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.wikibook.basicgallery.account.helper.AccountHelper;
import kr.co.wikibook.basicgallery.cart.dto.CartRead;
import kr.co.wikibook.basicgallery.cart.dto.CartRequest;
import kr.co.wikibook.basicgallery.cart.service.CartService;
import kr.co.wikibook.basicgallery.item.dto.ItemRead;
import kr.co.wikibook.basicgallery.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService; // 스프링 컨테이너에 의해 의존성 주입될 장바구니 서비스필드
    private final ItemService itemService; //  ==
    private final AccountHelper accountHelper; // ==

    @GetMapping("/api/cart/items")
    public ResponseEntity<?> readAll(HttpServletRequest req){ // 로그인 회원 장바구니 상품 목록 조회 메서드
        //로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        //장바구니 목록 조회
        List<CartRead> carts = cartService.findAll(memberId);

        // 장바구니 안에 있는 상품 아이디로 상품을 조회
        List<Integer> itemIds = carts.stream().map(CartRead::getItemId).toList();
        List<ItemRead> items = itemService.findAll(itemIds);

        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    @PostMapping("/api/carts")
    public ResponseEntity<?> push(HttpServletRequest req, @RequestBody CartRequest cartReq){
        // 로그인 회원의 장바구니 데이터 추가 메서드

        //로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        //장바구니 데이터 조회 (특정 상품)
        CartRead cart = cartService.find(memberId, cartReq.getItemId());

        // 장바구니 데이터가 없다면
        if (cart == null) {
            cartService.save(cartReq.toEntity(memberId));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/cart/items/{itemId}")
    public ResponseEntity<?> remove(HttpServletRequest req, @PathVariable("itemId") Integer itemId){
        // 로그인 회원의 장바구니의 특정 상품 데이터 삭제 메서드

        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        cartService.remove(memberId, itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
