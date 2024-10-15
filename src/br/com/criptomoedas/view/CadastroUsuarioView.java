package br.com.criptomoedas.view;

import br.com.criptomoedas.dao.UsuarioDao;
import br.com.criptomoedas.model.Usuario;

import java.sql.SQLException;

public class CadastroUsuarioView {

    public static void main(String[] args) {
        try {
            UsuarioDao dao = new UsuarioDao();
            Usuario usuario = new Usuario(2, "Rebecca", "rebecca.com", "1234", "12345678910");
            dao.cadastrar(usuario);
            dao.fecharConexao();
            System.out.println("Usuário cadastrado!");


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }


}


