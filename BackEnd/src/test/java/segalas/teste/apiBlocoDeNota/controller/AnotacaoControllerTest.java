package segalas.teste.apiBlocoDeNota.controller;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import segalas.teste.apiBlocoDeNota.dto.DadosAtualizacaoAnotacao;
import segalas.teste.apiBlocoDeNota.dto.DadosCadastroAnotacao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AnotacaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAtualizacaoAnotacao> atualizaAnotacaoJson;

    @Test
    @DisplayName("API ira enviar uma requisição para realizar o cadastro da anotação no banco de dados sem informação no body")
    void saveAnotacao() throws Exception{
        var response = mvc.perform(post("/anotacao"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("API ira enviar uma requisição para atualizar uma anotação no banco de dados sem informar o ID no body do JSON")
    void atualizarAnotacao() throws Exception{
        Long id =null;
        var response = mvc.perform(put("/anotacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atualizaAnotacaoJson.write(
                                new DadosAtualizacaoAnotacao(null,"teste titulo","teste descrição")
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("API ira enviar uma requisição para buscar todos as anotações realizada no banco de dados")
    void getAll() throws Exception{
        var response = mvc.perform(get("/anotacao"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("API ira enviar uma requisição para buscar uma anotação pelo ID no banco de dados, porem vamos informar um ID que não existe")
    void getById() throws Exception {
        var response = mvc.perform(get("/anotacao/"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("API ira enviar uma requisição para deletar uma anotação no banco de dados sem enviar o ID")
    void excluirAnotacao() throws Exception{
        var response = mvc.perform(delete("/anotacao/"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }
}