package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

// interagir com o banco de dados
public interface EstadoRepositorio extends JpaRepository<Estado, Long> {

}
