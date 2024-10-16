package br.com.duckchain.view;
import br.com.duckchain.dao.UsuarioDao;
import br.com.duckchain.model.Usuario;
import java.sql.SQLException;
import java.util.*;

public class UsuarioView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDao dao;
        try{
            dao = new UsuarioDao();
            int escolha;
            do{
                System.out.println("--------------------------------\nUSUARIO - MENU:\n 1 - CADASTRAR USUARIO\n 2 - PESQUISAR USUARIO\n 3 - ATUALIZAR USUARIO\n 4 - DELETAR USUARIO\n 5 - EXIBIR TODAS OS USUARIOS CADASTRADOS\n 0 - SAIR \n--------------------------------\nDigite o número da função desejada:");
                escolha = scanner.nextInt();
                switch (escolha){
                    case 1:
                        cadastrar(scanner, dao);
                        break;
                    case 2:
                        pesquisar(scanner, dao);
                        break;
                    case 3:
                        atualizar(scanner, dao);
                        break;
                    case 4:
                        remover(scanner, dao);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente...");
                }
            }while (escolha!=0);
            dao.fecharConexao();
        }catch (SQLException e){
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }finally {
            scanner.close();
        }
    }
    private static void cadastrar(Scanner scanner, UsuarioDao dao) {
    }
    private static void pesquisar(Scanner scanner, UsuarioDao dao) {
    }
    private static void atualizar(Scanner scanner, UsuarioDao dao) {
    }
    private static void remover(Scanner scanner, UsuarioDao dao) {
    }
}
