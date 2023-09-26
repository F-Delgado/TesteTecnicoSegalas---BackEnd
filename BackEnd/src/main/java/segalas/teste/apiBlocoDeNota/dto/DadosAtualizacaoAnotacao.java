package segalas.teste.apiBlocoDeNota.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAnotacao(
        @NotNull
        Long id,
        String titulo,
        String descricao) {
}
