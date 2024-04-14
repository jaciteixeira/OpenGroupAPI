package br.com.fiap.opengroup.repository;

import br.com.fiap.opengroup.entity.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    List<Arquivo> findAllByDadosClienteId(Long dadosClienteId);
}
