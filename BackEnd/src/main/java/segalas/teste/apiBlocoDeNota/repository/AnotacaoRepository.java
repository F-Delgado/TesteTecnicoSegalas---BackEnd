package segalas.teste.apiBlocoDeNota.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import segalas.teste.apiBlocoDeNota.anotacao.Anotacao;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {
    Page<Anotacao> findAll(Pageable paginacao);
}
