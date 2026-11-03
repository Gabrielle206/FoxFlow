package com.projeto.sistema.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/adm")
    public String acessarPrincical() {
        return "adm/index";
    }

}
