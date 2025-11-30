package kr.co.wikibook.basicgallery.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HttpUtils {
    // 세션 입력
    // http 세션 데이터를 입력하는 메서드, 매개변수로 http 요청 객체와 세션의 키, 그리고 세션의 값을 받습니다.
    // 이후에 로그인에 성공한 사용자의 정보를 저장할 때 사용될 것
    public static void setSession(HttpServletRequest req, String key, Object value) {
        req.getSession().setAttribute(key, value);
    }

    // 세션 값 조회
    // http 세션의 특정 값을 조회하는 메서드입니다. 매개변수로 HTTP 요청 객체와 세션의 키를 받습니다.
    // 이후에 로그인 사용자의 회원의 아이디를 조회하는 목적으로 사용될 것입니다.
    public static Object getSessionValue(HttpServletRequest req, String key) {
        return req.getSession().getAttribute(key);
    }

    // 세션 삭제
    // HTTP 세션의 값을 삭제하는 메서드입니다.
    // 매개변수로 HTTP요청 객체를 받습니다.
    // 이후에 로그아웃을 요청한 사용자의 세션을 삭제하는 목적으로 사용될 것입니다.
    public static void removeSession(HttpServletRequest req, String key) {
        req.getSession().removeAttribute(key);
    }

    //쿠키 입력
    public static void setCookie(HttpServletResponse res, String name, String value, int expSeconds){
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        if (expSeconds > 0) {
            cookie.setMaxAge(expSeconds);
        }

        res.addCookie(cookie);
    }

    //쿠키 값 조회
    public static String getCookieValue(HttpServletRequest req, String name){
        Cookie[] cookies = req.getCookies();

        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(name)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    //쿠키 삭제
    public static void removeCookie(HttpServletResponse res, String name){
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }

    // 토큰 조회
    public static String getBearerToken(HttpServletRequest req){
        String authorization = req.getHeader("Authorization");
        if (authorization != null){
            return authorization.replace("Bearer ", "").trim(); // Bearer 값 조회
        }
        return null;
    }

}
