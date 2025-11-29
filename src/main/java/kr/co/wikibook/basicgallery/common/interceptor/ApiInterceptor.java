package kr.co.wikibook.basicgallery.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.wikibook.basicgallery.account.helper.AccountHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class ApiInterceptor implements HandlerInterceptor {

    private final AccountHelper accountHelper;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler){
        if (accountHelper.getMemberId(req) == null) {
            res.setStatus(401);
            return false;
        }

        return true;
    }
}
