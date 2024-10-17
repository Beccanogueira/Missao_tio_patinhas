package br.com.duckchain.dao;

import br.com.duckchain.exception.EntidadeNaoEncontradaException;
import br.com.duckchain.model.Usuario;
import br.com.duckchain.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private Connection conexao;

    public UsuarioDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }

    public void cadastrar(Usuario usuario) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("INSERT INTO t_usuario (id_usuario, cd_cpf, dr_email, nm_nome, pw_senha) VALUES (?,?,?,?,?)");
        stm.setInt(1, usuario.getId());
        stm.setString(2, usuario.getCpf());
        stm.setString(3, usuario.getEmail());
        stm.setString(4, usuario.getNome());
        stm.setString(5, usuario.getSenha());
        stm.executeUpdate();
    }
    public void fecharConexao() throws SQLException {
        conexao.close();
    }

    public Usuario pesquisar(int idUsuario) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_usuario WHERE id_usuario = ?");
        stm.setLong(1, idUsuario);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new EntidadeNaoEncontradaException("Usuario não encontrado");

        return parseUsuario(result);
    }

    private Usuario parseUsuario(ResultSet result) throws SQLException {
        int id = result.getInt("id_usuario");
        String cpf = result.getString("cd_cpf");
        String email = result.getString("dr_email");
        String nome = result.getString("nm_nome");
        String senha = result.getString("pw_senha");
        return new Usuario(id, nome, email, senha, cpf);
    }

    public List<Usuario> listar() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_usuario");
        ResultSet result = stm.executeQuery();
        List<Usuario> lista = new ArrayList<>();
        while (result.next()){

            lista.add(parseUsuario(result));
        }
        return lista;
    }
    public void atualizar(Usuario usuario) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("UPDATE t_usuario SET cd_cpf = ?, dr_email = ?, nm_nome = ?, pw_senha = ? where id_usuario = ?");
        stm.setString(1, usuario.getCpf());
        stm.setString(2, usuario.getEmail());
        stm.setString(3, usuario.getNome());
        stm.setString(4, usuario.getSenha());
        stm.setInt(5, usuario.getId());
        stm.executeUpdate();
    }



    public void remover(int idUsuario) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from t_usuario where id_usuario = ?");
        stm.setLong(1, idUsuario);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new EntidadeNaoEncontradaException("Usuario não encontrado para ser removido");
    }
}