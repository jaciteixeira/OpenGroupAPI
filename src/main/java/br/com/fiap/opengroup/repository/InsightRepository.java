package br.com.fiap.opengroup.repository;

import br.com.fiap.opengroup.entity.Insight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsightRepository extends JpaRepository<Insight, Long> {
}
