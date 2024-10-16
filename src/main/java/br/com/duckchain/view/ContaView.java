package br.com.duckchain.view;
import br.com.duckchain.dao.ContaDao;
import java.sql.SQLException;
import java.util.Scanner;

public class ContaView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ContaDao dao;
        try {
            dao = new ContaDao();
            int escolha;
            do {
                System.out.println("--------------------------------\nCONTA - MENU:\n 1 - PESQUISAR CONTA\n 2 - ATUALIZAR CONTA\n 3 - REMOVER CONTA\n 0 - SAIR \n--------------------------------\nDigite o número da função desejada:");
                escolha = scanner.nextInt();

                switch (escolha) {
                    case 0:
                        System.out.println("Saindo e retornando ao MENU GERAL...");
                        break;
                    case 1:
                        pesquisar(scanner, dao);
                        break;
                    case 2:
                        atualizar(scanner, dao);
                        break;
                    case 3:
                        remover(scanner, dao);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente...");
                }
            } while (escolha != 0);
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void pesquisar(Scanner scanner, ContaDao dao) {

    }
    private static void atualizar(Scanner scanner, ContaDao dao) {
    }

    private static void remover(Scanner scanner, ContaDao dao) {
    }
}
