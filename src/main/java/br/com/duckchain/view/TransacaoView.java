package br.com.duckchain.view;
import br.com.duckchain.dao.TransacaoDao;
import java.sql.*;
import java.util.*;

public class TransacaoView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TransacaoDao dao;
        try{
            dao = new TransacaoDao();
            int escolha;
            do {
                System.out.println("--------------------------------\nTRANSFERENCIA - MENU:\n 1 - CADASTRAR TRANSAÇÃO\n 0 - SAIR \n--------------------------------\nDigite o número da função desejada:");
                escolha = scanner.nextInt();
                switch (escolha){
                    case 0:
                        System.out.println("Saindo e retornando ao MENU GERAL...");
                        break;
                    case 1:
                        cadastrar(scanner, dao);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente...");
                }
            } while (escolha !=0);
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    private static void cadastrar(Scanner scanner, TransacaoDao dao) {
    }


}



