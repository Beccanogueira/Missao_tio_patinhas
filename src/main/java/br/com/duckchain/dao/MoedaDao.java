package br.com.duckchain.dao;
import java.sql.*;
import java.util.*;
import br.com.duckchain.exception.*;
import br.com.duckchain.factory.ConnectionFactory;
import br.com.duckchain.model.Moeda;

public class MoedaDao {
    //Conexão com o banco
    private Connection conexao;
    public MoedaDao() throws SQLException{
        conexao = ConnectionFactory.getConnection();
    }
    public void fecharConexao() throws SQLException {
        conexao.close();
    }
    //Métodos CRUD
    public void cadastrar(Moeda moeda) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("INSERT INTO t_moeda (id_moeda, nm_nome, vl_cotacaoatual, nr_variacao24h, nr_volume24h) VALUES (?, ?, ?, ?, ?)");
        stm.setInt(1, gerarId());
        stm.setString(2, moeda.getNome());
        stm.setDouble(3, moeda.getCotacaoAtual());
        stm.setDouble(4, moeda.getVariacao24H());
        stm.setDouble(5, moeda.getVolume24H());
        stm.executeUpdate();
    }

    public Moeda pesquisar(int idMoeda) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_moeda WHERE id_moeda = ?");
        stm.setInt(1, idMoeda);
        ResultSet result = stm.executeQuery();
        if (!result.next()) throw new EntidadeNaoEncontradaException("Moeda não encontrada");

        return parseMoeda(result);
    }

    private Moeda parseMoeda(ResultSet result) throws SQLException {
        int idMoeda = result.getInt("id_moeda");
        String nome = result.getString("nm_nome");
        double cotacaoAtual = result.getDouble("vl_cotacaoatual");
        double variacao24H = result.getDouble("nr_variacao24h");
        double volume24H = result.getDouble("nr_volume24h");

        return new Moeda(idMoeda, nome, cotacaoAtual, variacao24H, volume24H);
    }

    public List<Moeda> listar() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_moeda");
        ResultSet result = stm.executeQuery();
        List<Moeda> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseMoeda(result));
        }
        return lista;
    }

    public void remover(int idMoeda) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("DELETE FROM t_moeda WHERE id_moeda = ?");
        stm.setInt(1, idMoeda);
        int linhasAfetadas = stm.executeUpdate();
        if (linhasAfetadas == 0) {
            throw new EntidadeNaoEncontradaException("Moeda não encontrada");
        }
    }

    private int gerarId() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT s_moeda_id.nextval as id_moeda FROM dual");
        ResultSet result = stm.executeQuery();
        int id = 0;
        while (result.next()) {
            id = result.getInt("id_moeda");
        }
        return id;
    }
}
