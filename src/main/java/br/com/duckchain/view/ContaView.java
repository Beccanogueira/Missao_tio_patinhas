package br.com.duckchain.view;
import br.com.duckchain.dao.ContaDao;
import br.com.duckchain.dao.MoedaDao;
import br.com.duckchain.exception.EntidadeNaoEncontradaException;
import br.com.duckchain.model.Conta;
import br.com.duckchain.model.Moeda;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ContaView extends BaseView{
    private ContaDao dao;

    public ContaView(String title, Scanner scanner){
        super(title, scanner);
        try{
            this.dao = new ContaDao();
        }catch (SQLException e){
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    @Override
    public void cadastrar() {
        cadastrarConta(scanner, dao);
    }

    @Override
    public void listar() {
        listarConta(scanner, dao);
    }

    @Override
    public void pesquisar() {
        pesquisarConta(scanner, dao);
    }

    @Override
    public void atualizar() {
        atualizarConta(scanner, dao);
    }

    @Override
    public void remover() {
        removerConta(scanner, dao);
    }

    private void cadastrarConta(Scanner scanner, ContaDao dao){
        System.out.println("\n--------CADASTRAR CONTA---------");
        System.out.print("Digite o ID do usuário: ");
        int idUsuario = lerInteiro();

        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();

        System.out.print("Digite o saldo inicial: ");
        double saldoTotal = lerDecimal();

        Conta novaConta = new Conta(0, idUsuario, saldoTotal, numeroConta);

        System.out.println("\nDados da nova conta:");
        System.out.println("ID Usuário: " + idUsuario);
        System.out.println("Número da Conta: " + numeroConta);
        System.out.println("Saldo Inicial: " + saldoTotal);

        System.out.print("\nDeseja confirmar o cadastro da conta? (s/n): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            try {
                dao.cadastrar(novaConta);
            } catch (SQLException e) {
                System.out.println(e);
            }
            System.out.println("--------------------------------");
            System.out.println("Conta cadastrada com sucesso!");
        } else {
            System.out.println("--------------------------------");
            System.out.println("Cadastro de conta cancelado.");
        }
    }

    private void listarConta(Scanner scanner, ContaDao dao){

    }

    private void pesquisarConta(Scanner scanner, ContaDao dao){
        System.out.print("Digite o ID da conta que deseja pesquisar: ");
        int idConta = lerInteiro();
        //scanner.nextLine();

        Conta conta = null;
        try {
            conta = dao.pesquisar(idConta);
        } catch (Exception e) {
            System.out.println("Erro ao buscar a conta: " + e.getMessage());
        }

        if (conta == null) {
            System.out.println("Conta com ID " + idConta + " não encontrada.");
        } else {
            System.out.println("\nDetalhes da Conta:");
            System.out.println("ID da Conta: " + conta.getIdConta());
            System.out.println("ID do Usuário: " + conta.getIdUsuario());
            System.out.println("Saldo Total: " + conta.getSaldoTotal());
            System.out.println("Número da Conta: " + conta.getNumeroConta());
        }
    }

    private void atualizarConta(Scanner scanner, ContaDao dao){
        System.out.print("Digite o ID da conta que deseja atualizar: ");
        int idConta = lerInteiro();
        //scanner.nextLine();

        Conta conta = null;
        try {
            conta = dao.pesquisar(idConta);
        } catch (Exception e) {
            System.out.println("Erro ao buscar a conta: " + e.getMessage());
            return;
        }

        if (conta == null) {
            System.out.println("Conta com ID " + idConta + " não encontrada.");
        } else {
            System.out.println("\nDados atuais da conta:");
            System.out.println("ID do Usuário: " + conta.getIdUsuario());
            System.out.println("Saldo Total: " + conta.getSaldoTotal());
            System.out.println("Número da Conta: " + conta.getNumeroConta());

            System.out.print("\nDigite o novo ID do usuário (atual: " + conta.getIdUsuario() + "): ");
            int idUsuario = lerInteiro();
            //scanner.nextLine();

            System.out.print("Digite o novo número da conta (atual: " + conta.getNumeroConta() + "): ");
            String numeroConta = scanner.nextLine();

            System.out.print("Digite o novo saldo total (atual: " + conta.getSaldoTotal() + "): ");
            double saldoTotal = lerDecimal();
            //scanner.nextLine();

            System.out.println("\nConfirma a atualização da conta? (s/n): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                conta.setIdUsuario(idUsuario);
                conta.setNumeroConta(numeroConta);
                conta.setSaldoTotal(saldoTotal);

                try {
                    dao.atualizar(conta);
                    System.out.println("--------------------------------");
                    System.out.println("Conta atualizada com sucesso!");
                } catch (Exception e) {
                    System.out.println("--------------------------------");
                    System.out.println("Erro ao atualizar a conta: " + e.getMessage());
                }
            } else {
                System.out.println("--------------------------------");
                System.out.println("Atualização de conta cancelada.");
            }
        }
    }

    private void removerConta(Scanner scanner, ContaDao dao){
        System.out.print("Digite o ID da conta que deseja remover: ");
        int idConta = lerInteiro();
        //scanner.nextLine();

        boolean contaExiste = false;
        try {
            contaExiste = dao.pesquisar(idConta) != null;
        } catch (Exception e) {
            System.out.println("Erro ao buscar a conta: " + e.getMessage());
            return;
        }

        if (!contaExiste) {
            System.out.println("Conta com ID " + idConta + " não encontrada.");
        } else {
            System.out.println("\nVocê está prestes a remover a conta com ID: " + idConta);
            System.out.println("Essa ação é irreversível.");

            System.out.print("Confirma a remoção da conta? (s/n): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                try {
                    dao.remover(idConta);
                    System.out.println("Conta removida com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro ao remover a conta: " + e.getMessage());
                }
            } else {
                System.out.println("Remoção da conta cancelada.");
            }
        }
    }
}
