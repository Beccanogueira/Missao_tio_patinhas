package br.com.criptomoedas.view;

import br.com.criptomoedas.dao.UsuarioDao;
import br.com.criptomoedas.exception.EntidadeNaoEncontradaException;
import br.com.criptomoedas.model.Usuario;

import java.sql.SQLException;
public class PesquisaUsuarioPorIdView {
    public static void main(String[] args) {
        try {
            UsuarioDao dao = new UsuarioDao();
            Usuario usuario = dao.pesquisar(1);
            System.out.println(STR."\{usuario.getIdUsuario()} \{usuario.getCpf()}, \{usuario.getEmail()}, \{usuario.getNome()}");
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Codigo não existe na tabela");
        }
    }
}