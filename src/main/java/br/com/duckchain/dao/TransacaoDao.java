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
        stm.setInt(1, transacao.getIdConta());
        stm.setInt(2, transacao.getIdUsuario());
        stm.setDouble(3, transacao.getSaldoTotal());
        stm.setInt(4, transacao.getNumeroConta());
        stm.setInt(5, transacao.getIdTransacao());
        stm.setInt(6, transacao.getIdMoeda());
        stm.setTimestamp(7, java.sql.Timestamp.valueOf(transacao.getDataHora()));
        stm.setString(8, transacao.getTipoTransacao());
        stm.setDouble(9, transacao.getQuantidadeMoeda());
        stm.setDouble(10, transacao.getPrecoMoeda());
        stm.executeUpdate();
    }

    protected Transacao parseTransacao(ResultSet result) throws SQLException {
        int idConta = result.getInt("id_conta");
        int idUsuario = result.getInt("id_usuario");
        double saldoTotal = result.getDouble("saldo_total");
        int numeroConta = result.getInt("numero_conta");
        int idTransacao = result.getInt("id_transacao");
        int idMoeda = result.getInt("id_moeda");
        LocalDateTime dataHora = result.getTimestamp("data_hora").toLocalDateTime();
        String tipoTransacao = result.getString("tipo_transacao");
        int quantidadeMoeda = result.getInt("quantidade_moeda");
        double precoMoeda = result.getDouble("preco_moeda");

        return new Transacao(idConta, idUsuario, saldoTotal, numeroConta, idTransacao, idMoeda, dataHora, tipoTransacao, quantidadeMoeda, precoMoeda);
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}