package com.projeto.sistema.controle;

import com.projeto.sistema.modelos.Estado;
import com.projeto.sistema.repositorios.EstadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class EstadoController {

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @GetMapping("/cadastroEstado")
    public ModelAndView cadastrar(Estado estado) {
        // Apenas prepara a tela de cadastro/edição
        ModelAndView mv = new ModelAndView("adm/estados/cadastro");
        mv.addObject("estado", estado);
        return mv;
    }

    @GetMapping("/listarEstado")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("adm/estados/lista");
        mv.addObject("ListaEstados", estadoRepositorio.findAll());
        return mv;
    }

    @GetMapping("/editarEstado/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Estado> estado = estadoRepositorio.findById(id);
        if(estado.isPresent()) {
            return cadastrar(estado.get());
        }
        return new ModelAndView("redirect:/listarEstado");
    }

    @GetMapping("/removerEstado/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes attributes) {
        Optional<Estado> estado = estadoRepositorio.findById(id);
        if(estado.isPresent()) {
            estadoRepositorio.delete(estado.get());
            attributes.addFlashAttribute("sucesso", "Estado removido com sucesso!");
        } else {
            attributes.addFlashAttribute("erro", "Estado não encontrado!");
        }
        // Redireciona para a listagem (agora o flashAttribute funciona!)
        return new ModelAndView("redirect:/listarEstado");
    }

    @PostMapping("/salvarEstado")
    public ModelAndView salvar(Estado estado, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            return cadastrar(estado);
        }

        estadoRepositorio.saveAndFlush(estado);
        attributes.addFlashAttribute("sucesso", "Estado salvo com sucesso!");

        // Redireciona de volta para o cadastro limpo (ou para a lista)
        return new ModelAndView("redirect:/cadastroEstado");
    }
}