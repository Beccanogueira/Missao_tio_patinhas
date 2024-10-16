package br.com.duckchain.view;
import br.com.duckchain.dao.TransferenciaDao;
import java.sql.*;
import java.util.*;

public class TransferenciaView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TransferenciaDao dao;
        try{
            dao = new TransferenciaDao();
            int escolha;
            do {
                System.out.println("--------------------------------\nTRANSFERENCIA - MENU:\n 1 - CADASTRAR TRANSFERENCIA\n 0 - SAIR \n--------------------------------\nDigite o número da função desejada:");
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
    private static void cadastrar(Scanner scanner, TransferenciaDao dao) {
    }


}