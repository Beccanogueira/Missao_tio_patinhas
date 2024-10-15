package br.com.criptomoedas.view;

import br.com.criptomoedas.dao.ContaDao;
import br.com.criptomoedas.model.Conta;
import br.com.criptomoedas.model.Transacao;
import br.com.criptomoedas.model.Transferencia;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastroContaView {

    public static void main(String[] args) {
        try {
            ContaDao dao = new ContaDao() {
                @Override
                public void cadastrar(Conta conta) throws SQLException {

                }

                @Override
                public void cadastrar(Transacao transacao) throws SQLException {

                }

                @Override
                public void cadastrar(Transferencia transferencia) throws SQLException {

                }

                @Override
                protected Conta parseConta(ResultSet result) throws SQLException {
                    return null;
                }
            };
            Conta conta = new Conta(1, 12345, 1000.0, 2);
            dao.cadastrar(conta);
            dao.fecharConexao();
            System.out.println("Conta cadastrada com sucesso!");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
