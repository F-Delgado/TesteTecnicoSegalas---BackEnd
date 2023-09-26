package segalas.teste.apiBlocoDeNota.dto;

import jakarta.validation.constraints.NotBlank;
public record DadosCadastroAnotacao(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao) {
}
