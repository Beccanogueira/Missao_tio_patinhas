package br.com.criptomoedas.dao;

import br.com.criptomoedas.database.ConexaoFactory;
import br.com.criptomoedas.exception.EntidadeNaoEncontradaException;
import br.com.criptomoedas.model.TipoMoeda;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoMoedaDao {
    private Connection conexao;

    public TipoMoedaDao() throws SQLException {
        conexao = ConexaoFactory.getConnection();
    }

    public void cadastrar(TipoMoeda moeda) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("INSERT INTO t_moeda (id_moeda, nome, cotacao_atual, variacao_24h, volume_24h) VALUES (seq_moeda.nextval, ?, ?, ?, ?)");
        stm.setString(1, moeda.getNome());
        stm.setDouble(2, moeda.getCotacaoAtual());
        stm.setDouble(3, moeda.getVariacao24H());
        stm.setDouble(4, moeda.getVolume24H());
        stm.executeUpdate();
    }

    public TipoMoeda pesquisar(int idMoeda) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_moeda WHERE id_moeda = ?");
        stm.setInt(1, idMoeda);
        ResultSet result = stm.executeQuery();
        if (!result.next()) throw new EntidadeNaoEncontradaException("Moeda não encontrada");

        return parseMoeda(result);
    }

    private TipoMoeda parseMoeda(ResultSet result) throws SQLException {
        int idMoeda = result.getInt("id_moeda");
        String nome = result.getString("nome");
        double cotacaoAtual = result.getDouble("cotacao_atual");
        double variacao24H = result.getDouble("variacao_24h");
        double volume24H = result.getDouble("volume_24h");

        return new TipoMoeda(idMoeda, nome, cotacaoAtual, variacao24H, volume24H);
    }

    public <TipoMoeda> List<TipoMoeda> listar() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_moeda");
        ResultSet result = stm.executeQuery();
        List<TipoMoeda> lista = new ArrayList<>();
        while (result.next()) {

            lista.add(parseTipoMoeda(result));
        }
        return lista;
    }

    private <TipoMoeda> TipoMoeda parseTipoMoeda(ResultSet result) {
        return null;
    }
}
