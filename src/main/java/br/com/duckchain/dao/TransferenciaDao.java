package br.com.duckchain.dao;

import br.com.duckchain.model.Conta;
import br.com.duckchain.model.Transacao;
import br.com.duckchain.model.Transferencia;
import br.com.duckchain.exception.EntidadeNaoEncontradaException

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TransferenciaDao extends ContaDao {

    public TransferenciaDao() throws SQLException {
        super();
    }

    public void cadastrar(Transferencia transferencia) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("INSERT INTO t_transferencia (id_conta, id_usuario, saldo_total, numero_conta, id_transferencia, id_conta_destino, data_hora, valor_transferido) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        stm.setInt(1, transferencia.getIdConta());
        stm.setInt(2, transferencia.getIdUsuario());
        stm.setDouble(3, transferencia.getSaldoTotal());
        stm.setInt(4, transferencia.getNumeroConta());
        stm.setInt(5, transferencia.getIdTransferencia());
        stm.setInt(6, transferencia.getIdContaDestino());
        stm.setTimestamp(7, java.sql.Timestamp.valueOf(transferencia.getDataHora()));
        stm.setDouble(8, transferencia.getValorTransferido());
        stm.executeUpdate();
    }

    @Override
    protected Transferencia parseConta(ResultSet result) throws SQLException {
        int idConta = result.getInt("id_conta");
        int idUsuario = result.getInt("id_usuario");
        double saldoTotal = result.getDouble("saldo_total");
        int numeroConta = result.getInt("numero_conta");
        int idTransferencia = result.getInt("id_transferencia");
        int idContaDestino = result.getInt("id_conta_destino");
        LocalDateTime dataHora = result.getTimestamp("data_hora").toLocalDateTime();
        double valorTransferido = result.getDouble("valor_transferido");

        return new Transferencia(idConta, idUsuario, saldoTotal, numeroConta, idTransferencia, idContaDestino, dataHora, valorTransferido);
    }
}