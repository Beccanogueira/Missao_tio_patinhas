package br.com.duckchain.dao;

import br.com.duckchain.exception.EntidadeNaoEncontradaException;
import br.com.duckchain.model.Conta;
import br.com.duckchain.factory.ConnectionFactory;
import br.com.duckchain.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDao {
    protected Connection conexao;

    public ContaDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }

    public void cadastrar(Conta conta) throws SQLException {
        String sql = "INSERT INTO t_conta (id_conta, id_usuario, vl_saldototal, cd_conta) VALUES (?, ?, ?, ?)";
        PreparedStatement stm = conexao.prepareStatement(sql);

        stm.setInt(1, gerarId());
        stm.setInt(2, conta.getIdUsuario());
        stm.setDouble(3, conta.getSaldoTotal());
        stm.setString(4, conta.getNumeroConta());

        stm.executeUpdate();
    }

    public Conta pesquisar(int idConta) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_conta WHERE id_conta = ?");
        stm.setInt(1, idConta);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new EntidadeNaoEncontradaException("Conta não encontrada");

        return parseConta(result);
    }

    public List<Conta> listar(int idUsuario) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_conta WHERE id_usuario = ?");
        stm.setInt(1, idUsuario);
        ResultSet result = stm.executeQuery();
        List<Conta> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseConta(result));
        }
        return lista;
    }


    public void atualizar(Conta conta) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("UPDATE t_conta SET id_usuario = ?, vl_saldototal = ?, cd_conta = ? WHERE id_conta = ?");
        stm.setInt(1, conta.getIdUsuario());
        stm.setDouble(2, conta.getSaldoTotal());
        stm.setString(3, conta.getNumeroConta());
        stm.setInt(4, conta.getIdConta());
        stm.executeUpdate();
    }

    public void remover(int idConta) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("DELETE FROM t_conta WHERE id_conta = ?");
        stm.setInt(1, idConta);
        int linhasAfetadas = stm.executeUpdate();
        if (linhasAfetadas == 0) {
            throw new EntidadeNaoEncontradaException("Conta não encontrada para ser removida");
        }
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }

    private Conta parseConta(ResultSet result) throws SQLException {
        int id = result.getInt("id_conta");
        String numeroConta = result.getString("cd_conta");
        int idUsuario = result.getInt("id_usuario");
        double saldototal = result.getDouble("vl_saldototal");

        return new Conta(id, idUsuario, saldototal, numeroConta);
    }

    private int gerarId() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT s_conta_id.nextval as id_conta FROM dual");
        ResultSet result = stm.executeQuery();
        int id = 0;
        while (result.next()) {
            id = result.getInt("id_conta");
        }
        return id;
    }
}