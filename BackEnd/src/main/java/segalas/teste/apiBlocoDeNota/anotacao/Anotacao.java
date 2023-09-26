package segalas.teste.apiBlocoDeNota.anotacao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import segalas.teste.apiBlocoDeNota.dto.DadosAtualizacaoAnotacao;
import segalas.teste.apiBlocoDeNota.dto.DadosCadastroAnotacao;

@Table(name = "Anotacoes")
@Entity(name = "Anotacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Anotacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;

    public Anotacao(DadosCadastroAnotacao dados) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
    }

    public void atualizarInformacoes(DadosAtualizacaoAnotacao dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
    }
}
