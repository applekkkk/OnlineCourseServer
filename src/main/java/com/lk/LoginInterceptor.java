package com.lk;

import com.lk.utils.JwtUtil;
import com.lk.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("Authorization");
        try{
            Map<String,Object> claim= JwtUtil.parseToken(token);
            ThreadLocalUtil.set(claim);
            return true;
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }
    }
}
