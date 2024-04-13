package br.com.fiap.opengroup.service;

import br.com.fiap.opengroup.dto.ServiceDTO;
import br.com.fiap.opengroup.dto.request.ItemRequest;
import br.com.fiap.opengroup.dto.response.ItemResponse;
import br.com.fiap.opengroup.entity.ItemRecomendado;
import br.com.fiap.opengroup.repository.ItemRecomendadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ItemRecomendadoService implements ServiceDTO<ItemRecomendado, ItemRequest, ItemResponse> {

    @Autowired
    private ItemRecomendadoRepository repo;

    @Override
    public ItemRecomendado toEntity(ItemRequest r) {
        if (Objects.isNull(r)) return null;
        return ItemRecomendado.builder()
                .nome(r.nome())
                .descricao(r.descricao())
                .build();
    }

    @Override
    public ItemResponse toResponse(ItemRecomendado e) {
        if (Objects.isNull(e)) return null;
        return ItemResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .descricao(e.getDescricao())
                .build();
    }

    @Override
    public List<ItemRecomendado> findAll() {
        return repo.findAll();
    }

    @Override
    public List<ItemRecomendado> findAll(Example<ItemRecomendado> example) {
        return repo.findAll(example);
    }

    @Override
    public ItemRecomendado findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public ItemRecomendado save(ItemRequest r) {
        return repo.save(toEntity(r));
    }
}
