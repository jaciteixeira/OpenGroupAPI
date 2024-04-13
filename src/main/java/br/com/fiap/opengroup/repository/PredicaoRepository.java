package br.com.fiap.opengroup.repository;

import br.com.fiap.opengroup.entity.Predicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredicaoRepository extends JpaRepository<Predicao, Long> {
}
