package kr.co.wikibook.basicgallery.account.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.wikibook.basicgallery.account.dto.AccountJoinRequest;
import kr.co.wikibook.basicgallery.account.dto.AccountLoginRequest;
import kr.co.wikibook.basicgallery.account.etc.AccountConstants;
import kr.co.wikibook.basicgallery.account.helper.AccountHelper;
import kr.co.wikibook.basicgallery.block.service.BlockService;
import kr.co.wikibook.basicgallery.common.util.HttpUtils;
import kr.co.wikibook.basicgallery.common.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class AccountController {
    private final AccountHelper accountHelper; // 스프링 컨테이너에 의해 의존성 주입될 계정 헬퍼 필드
    private final BlockService blockService; // ①


    @PostMapping("/api/account/join")
    public ResponseEntity<?> join(@RequestBody AccountJoinRequest joinReq){ // 회원가입을 처리하는 메서드
        //입력 값이 비어 있다면
        if (!StringUtils.hasLength(joinReq.getName()) ||
                !StringUtils.hasLength(joinReq.getLoginId()) ||
                !StringUtils.hasLength(joinReq.getLoginPw())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        accountHelper.join(joinReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/account/login") // 로그인 처리 메서드
    public ResponseEntity<?> login(HttpServletRequest req, HttpServletResponse res, @RequestBody AccountLoginRequest loginReq){
        //입력 값이 비어 있다면
        if (!StringUtils.hasLength(loginReq.getLoginId()) ||
                !StringUtils.hasLength(loginReq.getLoginPw())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String output = accountHelper.login(loginReq, req, res);

        if (output == null){ // 로그인 실패 시
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping("/api/account/check") // 로그인 여부 조회 메서드
    public ResponseEntity<?> check(HttpServletRequest req){
        return new ResponseEntity<>(accountHelper.isLoggedIn(req), HttpStatus.OK);
    }

    @PostMapping("/api/account/logout") // 로그아웃 메서드
    public ResponseEntity<?> logout(HttpServletRequest req, HttpServletResponse res){
        accountHelper.logout(req, res);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/account/token")
    public ResponseEntity<?> regenerate(HttpServletRequest req) { // 액세스 토큰을 재발급하는 메서드
        String accessToken = "";
        String refreshToken = HttpUtils.getCookieValue(req, AccountConstants.REFRESH_TOKEN_NAME);

        // 리프레시 토큰이 유효하다면
        if (StringUtils.hasLength(refreshToken) && TokenUtils.isValid(refreshToken) && !blockService.has(refreshToken)) {
            // 리프레시 토큰의 내부 값 조회
            Map<String, Object> tokenBody = TokenUtils.getBody(refreshToken);

            // 리프레시 토큰의 회원 아이디 조회
            Integer memberId = (Integer) tokenBody.get(AccountConstants.MEMBER_ID_NAME);

            // 액세스 토큰 발급
            accessToken = TokenUtils.generate(AccountConstants.ACCESS_TOKEN_NAME, AccountConstants.MEMBER_ID_NAME, memberId, AccountConstants.ACCESS_TOKEN_EXP_MINUTES);
        }

        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }



}
