package br.com.fiap.opengroup.repository;

import br.com.fiap.opengroup.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosClienteRepository extends JpaRepository<Cliente, Long> {
}
