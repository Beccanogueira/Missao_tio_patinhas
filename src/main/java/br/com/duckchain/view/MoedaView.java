package br.com.duckchain.view;
import br.com.duckchain.dao.MoedaDao;
import br.com.duckchain.model.Moeda;
import br.com.duckchain.exception.*;
import java.sql.*;
import java.util.*;

public class MoedaView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoedaDao dao;
        try{
            dao = new MoedaDao();
            int escolha;
            do{
                System.out.println("--------------------------------\nMOEDA - MENU:\n 1 - CADASTRAR MOEDA\n 2 - PESQUISAR MOEDA\n 3 - ATUALIZAR MOEDA\n 4 - DELETAR MOEDA\n 5 - EXIBIR TODAS AS MOEDAS CADASTRADAS\n 0 - SAIR \n--------------------------------\nDigite o número da função desejada:");
                escolha = scanner.nextInt();
                switch (escolha){
                    case 0:
                        System.out.println("Saindo e retornando ao MENU GERAL...");
                        break;
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
    private static void cadastrar(Scanner scanner, MoedaDao dao){
        System.out.println("Digite o id da moeda:");
        int idMoeda = scanner.nextInt();
        System.out.println("Digite o nome da moeda/ativo:");
        String nome = scanner.next() + scanner.nextLine();
        System.out.println("Digite a cotação atual:");
        double cotacaoAtual = scanner.nextDouble();
        System.out.println("Digite a variação em percentual das últimas 24 horas:");
        double variacao24H = scanner.nextDouble();
        System.out.println("Digite a variação em volume das últimas 24 horas:");
        double volume24H = scanner.nextDouble();
        Moeda novaMoeda = new Moeda(idMoeda, nome, cotacaoAtual, variacao24H, volume24H);
        try{
            dao.cadastrar(novaMoeda);
            System.out.println("--------------------------------\nMoeda cadastrada com sucesso!");
        }catch (SQLException e){
            System.err.println("Erro ao cadastrar: " + e.getMessage());
        }
    }
    private static void pesquisar(Scanner scanner, MoedaDao dao) {

    }
    private static void atualizar(Scanner scanner, MoedaDao dao) {
    }
    private static void remover(Scanner scanner, MoedaDao dao) {
        System.out.println("Digite o código da moeda que deseja remover:");
        int idMoeda = scanner.nextInt();
        try {
            dao.remover(idMoeda);
            System.out.println("Removido com sucesso!");
        } catch (SQLException | EntidadeNaoEncontradaException e) {
            System.err.println("Erro ao remover produto: " + e.getMessage());
        }
    }
}