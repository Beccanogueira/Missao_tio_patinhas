package br.com.duckchain.factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {
    private static final String URL =
            "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USUARIO = "rm552330";
    private static final String SENHA = "080604";
    // Método para obter uma conexão com o banco de dados
    public static Connection getConnection() throws
            SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}