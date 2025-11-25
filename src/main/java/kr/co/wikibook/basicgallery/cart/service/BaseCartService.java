package kr.co.wikibook.basicgallery.cart.service;

import jakarta.transaction.Transactional;
import kr.co.wikibook.basicgallery.cart.dto.CartRead;
import kr.co.wikibook.basicgallery.cart.entity.Cart;
import kr.co.wikibook.basicgallery.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //
@RequiredArgsConstructor //
public class BaseCartService implements CartService {
    private final CartRepository cartRepository; //

    // 장바구니 상품 데이터 목록 조회 ( 특정 회원 )
    @Override
    public List<CartRead> findAll(Integer memberId) { //  장바구니 서비스 인터페이스의 메서드를 구현합니다.
         // 리스트 갑들을 DTO로 변환해서 리턴
        return cartRepository.findAllByMemberId(memberId).stream().map(Cart::toRead).toList();
    }

    // 장바구니 상품 데이터 조회( 특정 회원의 특정 상품 )
    @Override
    public CartRead find(Integer memberId, Integer itemId) { // 장바구니 서비스 인터페이스의 메서드를 구현합니다.
        Optional<Cart> cartOptional = cartRepository.findByMemberIdAndItemId(memberId, itemId);
        // 값이 있으면 DTO로 변환해서 리턴, 없으면 null 리턴
        return cartOptional.map(Cart::toRead).orElse(null);
    }
    // 장바구니 상품 데이터 전체 삭제 ( 특정 회원 )
    @Override
    @Transactional
    public void removeAll(Integer memberId) { // 장바구니 서비스 인터페이스의 메서드를 구현합니다.
        cartRepository.deleteByMemberId(memberId);
    }

    @Override
    @Transactional
    public void remove(Integer memberId, Integer itemId) { // 장바구니 서비스 인터페이스의 메서드를 구현합니다.
        cartRepository.deleteByMemberIdAndItemId(memberId, itemId);
    }

    @Override
    @Transactional
    public void save(Cart cart) { // 장바구니 서비스 인터페이스의 메서드를 구현합니다.
        cartRepository.save(cart);
    }


}
