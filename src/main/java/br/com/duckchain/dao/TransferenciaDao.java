package br.com.duckchain.dao;

import br.com.duckchain.factory.ConnectionFactory;
import br.com.duckchain.model.Transferencia;
import br.com.duckchain.exception.EntidadeNaoEncontradaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransferenciaDao {

    private Connection conexao;

    public TransferenciaDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }

    public void cadastrar(Transferencia transferencia) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("INSERT INTO t_transferencia (id_transferencia, id_conta_origem, id_conta_destino, vl_valor, id_usuario, dt_transferencia) VALUES (?, ?, ?, ?, ?, ?)");
        stm.setInt(1, gerarId());
        stm.setInt(2, transferencia.getIdContaOrigem());
        stm.setDouble(3, transferencia.getIdContaDestino());
        stm.setDouble(4, transferencia.getValor());
        stm.setInt(5, transferencia.getIdUsuario());
        stm.setTimestamp(6, java.sql.Timestamp.valueOf(transferencia.getDataTransferencia()));
        stm.executeUpdate();
    }

    protected Transferencia parseTransferencia(ResultSet result) throws SQLException {
        int idTransferencia = result.getInt("id_transferencia");
        int idContaOrigem = result.getInt("id_conta_origem");
        int idContaDestino = result.getInt("id_conta_destino");
        double saldoTotal = result.getDouble("vl_valor");
        int idUsuario = result.getInt("id_usuario");
        LocalDateTime dataTransferencia = result.getTimestamp("dt_transferencia").toLocalDateTime();

        return new Transferencia(idTransferencia, idContaOrigem, idContaDestino, saldoTotal, idUsuario, dataTransferencia);
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }

    private int gerarId() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT s_transferencia_id.nextval as id_transferencia FROM dual");
        ResultSet result = stm.executeQuery();
        int id = 0;
        while (result.next()) {
            id = result.getInt("id_transferencia");
        }
        return id;
    }
    public List<Transferencia> listar(int idUsuario) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_transferencia WHERE id_usuario = ?");
        stm.setInt(1, idUsuario);
        ResultSet result = stm.executeQuery();
        List<Transferencia> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseTransferencia(result));
        }
        return lista;
    }

}