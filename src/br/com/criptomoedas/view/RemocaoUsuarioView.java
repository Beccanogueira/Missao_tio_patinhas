package br.com.criptomoedas.view;

import br.com.criptomoedas.dao.UsuarioDao;
import br.com.criptomoedas.exception.EntidadeNaoEncontradaException;

import java.sql.SQLException;

public class RemocaoUsuarioView {
    public static void main(String[] args) {
        try {
            UsuarioDao dao = new UsuarioDao();
            dao.remover(1);
            dao.fecharConexao();
            System.out.println("Usuário Removido!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            System.err.println("Usuário não encontrado");
        }
    }
}