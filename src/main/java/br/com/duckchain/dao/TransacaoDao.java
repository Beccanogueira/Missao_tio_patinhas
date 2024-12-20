package br.com.duckchain.dao;

import br.com.duckchain.factory.ConnectionFactory;
import br.com.duckchain.model.Conta;
import br.com.duckchain.model.Transacao;
import br.com.duckchain.model.Transferencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TransacaoDao {

    private Connection conexao;

    public TransacaoDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }

    public void cadastrar(Transacao transacao) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("INSERT INTO t_transacao (id_transacao, id_conta, dt_transacao, ds_tipotransacao, vl_valor, ds_descricao, id_usuario, id_moeda) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        stm.setInt(1, gerarId());
        stm.setInt(2, transacao.getIdConta());
        stm.setTimestamp(3,  java.sql.Timestamp.valueOf(transacao.getDataHora()));
        stm.setString(4, transacao.getTipoTransacao());
        stm.setDouble(5, transacao.getValor());
        stm.setString(6, transacao.getDescricao());
        stm.setInt(7, transacao.getIdUsuario());
        stm.setInt(8, transacao.getIdMoeda());
        stm.executeUpdate();
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }

    private int gerarId() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT s_transacao_id.nextval as id_transacao FROM dual");
        ResultSet result = stm.executeQuery();
        int id = 0;
        while (result.next()) {
            id = result.getInt("id_transacao");
        }
        return id;
    }
}