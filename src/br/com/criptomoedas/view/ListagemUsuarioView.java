package br.com.criptomoedas.view;

import br.com.criptomoedas.dao.UsuarioDao;
import br.com.criptomoedas.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class ListagemUsuarioView {

    public static void main(String[] args) {
        try {
            UsuarioDao dao = new UsuarioDao();
            List<Usuario> usuarios = dao.listar();
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getIdUsuario() + " " + usuario.getCpf() + ", " + usuario.getEmail() + ", " + usuario.getNome() + ", " + usuario.getSenha());
            }
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
