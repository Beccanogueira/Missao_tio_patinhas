package br.com.criptomoedas.database;
import java.sql.Connection;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) {
        try {
            Connection conexao = ConexaoFactory.getConnection();
            System.out.println("Conexão realizada!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}