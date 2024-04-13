package br.com.fiap.opengroup.repository;

import br.com.fiap.opengroup.entity.ItemRecomendado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRecomendadoRepository extends JpaRepository<ItemRecomendado, Long> {
}
