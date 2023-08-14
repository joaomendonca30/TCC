package com.cadastro.SistemaOdontologico.controller;
import com.cadastro.SistemaOdontologico.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException{
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        return "index";
    }
}
