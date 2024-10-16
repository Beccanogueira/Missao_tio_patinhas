package br.com.duckchain.dao;

import br.com.duckchain.exception.EntidadeNaoEncontradaException;
import br.com.duckchain.model.Conta;
import br.com.duckchain.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ContaDao {
    protected Connection conexao;

    public ContaDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }
    
    public Conta pesquisar(int idConta) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_conta WHERE id_conta = ?");
        stm.setInt(1, idConta);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new EntidadeNaoEncontradaException("Conta não encontrada");

        return parseConta(result);
    }

    protected abstract Conta parseConta(ResultSet result) throws SQLException;

    public void fecharConexao() throws SQLException {
        conexao.close();
    }

    public List<Conta> listar() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_conta");
        ResultSet result = stm.executeQuery();
        List<Conta> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseConta(result));
        }
        return lista;
    }

    public void atualizar(Conta conta) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("UPDATE t_conta SET id_usuario = ?, saldo_total = ?, numero_conta = ? WHERE id_conta = ?");
        stm.setInt(1, conta.getIdUsuario());
        stm.setDouble(2, conta.getSaldoTotal());
        stm.setInt(3, conta.getNumeroConta());
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
}