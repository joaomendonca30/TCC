package com.cadastro.SistemaOdontologico.controller;
import com.cadastro.SistemaOdontologico.models.Administrador;
import com.cadastro.SistemaOdontologico.repository.AdministradoresRepo;
import com.cadastro.SistemaOdontologico.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private AdministradoresRepo repo;

    @GetMapping("/login")
    public String index() {
        return "login/index";
    }

    @PostMapping("/logar")
    public String logar(Model model, Administrador admParam, String lembrar, HttpServletResponse response) {

        Administrador adm = this.repo.Login(admParam.getEmail(), admParam.getSenha());
        if (adm != null){
            int tempoLogado = (60*60); //1 hora
            if(lembrar != null) tempoLogado = (60*60*24*365);
            
            CookieService.setCookie(response, "usuarioId",String.valueOf(adm.getId()) ,60);
            return "redirect:/home";
        }
        model.addAttribute("erro", "Usuário e senha invalidos");
        return "login/index";
    }

}
