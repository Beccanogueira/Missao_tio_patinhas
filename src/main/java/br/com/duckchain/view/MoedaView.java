package br.com.duckchain.view;
import br.com.duckchain.dao.MoedaDao;
import br.com.duckchain.model.Moeda;
import br.com.duckchain.exception.*;
import java.sql.*;
import java.util.*;

public class MoedaView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        executar(scanner);
        scanner.close();
    }

    public static void executar(Scanner scanner) {
        MoedaDao dao;
        try{
            dao = new MoedaDao();
            int escolha;
            do{
                System.out.println("--------------------------------\nMOEDA - MENU:\n 1 - CADASTRAR MOEDA\n 2 - PESQUISAR MOEDA\n 3 - LISTAR MOEDAS\n 4 - DELETAR MOEDA\n 0 - SAIR \n--------------------------------\nDigite o número da função desejada:");

                try {
                    escolha = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Entrada Inválida. Por favor, tente novamente e digite um número inteiro.")
                    scanner.nextLine();
                    continue;
                }

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
                        listar(scanner, dao);
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
        }
    }

    private static void cadastrar(Scanner scanner, MoedaDao dao){
        System.out.println("Digite o nome da moeda/ativo:");
        String nome = scanner.next() + scanner.nextLine();
        System.out.println("Digite a cotação atual:");
        double cotacaoAtual = scanner.nextDouble();
        System.out.println("Digite a variação em percentual das últimas 24 horas:");
        double variacao24H = scanner.nextDouble();
        System.out.println("Digite a variação em volume das últimas 24 horas:");
        double volume24H = scanner.nextDouble();
        Moeda novaMoeda = new Moeda(0, nome, cotacaoAtual, variacao24H, volume24H);
        try{
            dao.cadastrar(novaMoeda);
            System.out.println("--------------------------------\nMoeda cadastrada com sucesso!");
        }catch (SQLException e){
            System.err.println("Erro ao cadastrar: " + e.getMessage());
        }
    }
    private static void pesquisar(Scanner scanner, MoedaDao dao) {
        System.out.print("Digite o ID da moeda que deseja pesquisar: ");
        int idMoeda = scanner.nextInt();
        scanner.nextLine();

        Moeda moeda = null;
        try {
            moeda = dao.pesquisar(idMoeda);
        } catch (Exception e) {
            System.out.println("Erro ao buscar a moeda: " + e.getMessage());
            return;
        }

        if (moeda == null) {
            System.out.println("Moeda com ID " + idMoeda + " não encontrada.");
        } else {
            System.out.println("\nMoeda encontrada:");
            System.out.println("ID: " + moeda.getIdMoeda());
            System.out.println("Nome: " + moeda.getNome());
            System.out.println("Cotação Atual: " + moeda.getCotacaoAtual());
            System.out.println("Variação 24H: " + moeda.getVariacao24H());
            System.out.println("Volume 24H: " + moeda.getVolume24H());
        }

    }

    private static void listar(Scanner scanner, MoedaDao dao) {
        List<Moeda> moedas = null;
        try {
            moedas = dao.listar();
        } catch (Exception e) {
            System.out.println("Erro ao listar as moedas: " + e.getMessage());
            return;
        }

        if (moedas.isEmpty()) {
            System.out.println("Nenhuma moeda encontrada.");
        } else {
            for (Moeda moeda : moedas) {
                System.out.println("----------------------------");
                System.out.println("ID: " + moeda.getIdMoeda());
                System.out.println("Nome: " + moeda.getNome());
                System.out.println("Cotação Atual: " + moeda.getCotacaoAtual());
                System.out.println("Variação 24H: " + moeda.getVariacao24H());
                System.out.println("Volume 24H: " + moeda.getVolume24H());
            }
        }
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






















