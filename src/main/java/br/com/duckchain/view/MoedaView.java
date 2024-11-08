package br.com.duckchain.view;
import br.com.duckchain.dao.MoedaDao;
import br.com.duckchain.exception.EntidadeNaoEncontradaException;
import br.com.duckchain.model.Moeda;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MoedaView extends BaseView{

    private MoedaDao dao;

    public MoedaView(String title, Scanner scanner){
        super(title, scanner);
        try{
            this.dao = new MoedaDao();
        }catch (SQLException e){
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    @Override
    public void cadastrar() {
        cadastrarMoeda(scanner, dao);
    }

    @Override
    public void listar() {
        listarMoeda(scanner, dao);
    }

    @Override
    public void pesquisar() {
        pesquisarMoeda(scanner, dao);
    }

    @Override
    public void atualizar() {
        atualizarMoeda(scanner, dao);
    }

    @Override
    public void remover() {
        removerMoeda(scanner, dao);
    }

    private void cadastrarMoeda(Scanner scanner, MoedaDao dao){
        System.out.println("\n--------CADASTRAR MOEDA---------");
        System.out.println("Digite o nome da moeda/ativo:");
        String nome = scanner.next() + scanner.nextLine();
        System.out.println("Digite a cotação atual:");
        double cotacaoAtual = lerDecimal();
        System.out.println("Digite a variação em percentual das últimas 24 horas:");
        double variacao24H = lerDecimal();
        System.out.println("Digite a variação em volume das últimas 24 horas:");
        double volume24H = lerDecimal();
        Moeda novaMoeda = new Moeda(0, nome, cotacaoAtual, variacao24H, volume24H);
        try{
            dao.cadastrar(novaMoeda);
            System.out.println("--------------------------------\nMoeda cadastrada com sucesso!");
        }catch (SQLException e){
            System.err.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private void listarMoeda(Scanner scanner, MoedaDao dao){
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
            System.out.println("\n-------MOEDAS CADASTRADAS-------");
            for (Moeda moeda : moedas) {
                System.out.println("ID: " + moeda.getIdMoeda());
                System.out.println("Nome: " + moeda.getNome());
                System.out.println("Cotação Atual: " + moeda.getCotacaoAtual());
                System.out.println("Variação 24H: " + moeda.getVariacao24H());
                System.out.println("Volume 24H: " + moeda.getVolume24H());
                System.out.println("--------------------------------");
            }
        }
    }

    private void pesquisarMoeda(Scanner scanner, MoedaDao dao){
        System.out.print("Digite o ID da moeda que deseja pesquisar: ");
        int idMoeda = lerInteiro();
        //scanner.nextLine();

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

    private void atualizarMoeda(Scanner scanner, MoedaDao dao){

    }

    private void removerMoeda(Scanner scanner, MoedaDao dao){
        System.out.println("Digite o código da moeda que deseja remover:");
        int idMoeda = lerInteiro();
        try {
            dao.remover(idMoeda);
            System.out.println("Removido com sucesso!");
        } catch (SQLException | EntidadeNaoEncontradaException e) {
            System.err.println("Erro ao remover produto: " + e.getMessage());
        }
    }
}