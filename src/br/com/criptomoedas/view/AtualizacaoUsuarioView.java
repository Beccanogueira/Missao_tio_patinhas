package br.com.criptomoedas.view;

import br.com.criptomoedas.dao.UsuarioDao;
import br.com.criptomoedas.exception.EntidadeNaoEncontradaException;
import br.com.criptomoedas.model.Usuario;

import java.sql.SQLException;

public class AtualizacaoUsuarioView {
    public static void main(String[] args) {
        try {
            UsuarioDao dao = new UsuarioDao();
            Usuario usuario = dao.pesquisar(1);
            usuario.setCpf("12345678910");
            usuario.setEmail("rebecca_fiap@gmail.com");
            usuario.setNome("Becca");
            usuario.setSenha("45321");
            dao.atualizar(usuario);
            dao.fecharConexao();
            System.out.println("Produto atualizado!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Produto não encontrado");
        }
    }
}