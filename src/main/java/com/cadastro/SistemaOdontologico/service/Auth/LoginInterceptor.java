package com.cadastro.SistemaOdontologico.service.Auth;

import com.cadastro.SistemaOdontologico.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        try{
            if(CookieService.getCookie(request, "usuarioId") != null){
                return true;
            }
        }
        catch(Exception erro) {}

        response.sendRedirect("/login");
        return false;
    }


}
