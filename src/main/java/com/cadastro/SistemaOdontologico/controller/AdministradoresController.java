package com.cadastro.SistemaOdontologico.controller;
import com.cadastro.SistemaOdontologico.models.Administrador;
import com.cadastro.SistemaOdontologico.repository.AdministradoresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AdministradoresController {

    @Autowired
    private AdministradoresRepo repo;

    @GetMapping("/administradores")
    public String index(Model model){
        List<Administrador> administradores = (List<Administrador>)repo.findAll();
        model.addAttribute("administradores", administradores);
        return "administradores/index";
    }

    @GetMapping("/administradores/novo")
    public String novo(){
        return "administradores/novo";
    }

    @PostMapping("/administradores/criar")
    public String criar(Administrador administrador){
        repo.save(administrador);
        return "redirect:/administradores";
    }

    @GetMapping("/administradores/{id}")
    public String busca(@PathVariable int id, Model model){
        Optional<Administrador> admin = repo.findById(id);
        try{
            model.addAttribute("administrador", admin.get());
        }
        catch(Exception err){ return "redirect:/administradores"; }

        return "/administradores/editar";
    }

    @PostMapping("/administradores/{id}/atualizar")
    public String atualizar(@PathVariable int id, Administrador administrador){
        // if(!repo.exist(id)){
        if(!repo.existsById(id)){
            return "redirect:/administradores";
        }

        repo.save(administrador);

        return "redirect:/administradores";
    }


    @GetMapping("/administradores/{id}/excluir")
    public String excluir(@PathVariable int id){
        repo.deleteById(id);
        return "redirect:/administradores";
    }
}
