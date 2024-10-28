package br.com.duckchain.view;
import br.com.duckchain.dao.ContaDao;
import br.com.duckchain.model.Conta;

import java.sql.SQLException;
import java.util.Scanner;

public class ContaView {

    private static int ultimoId = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        executar(scanner);
        scanner.close();
    }

    public static void executar(Scanner scanner) {
        ContaDao dao;
        try {
            dao = new ContaDao();
            int escolha;
            do {
                System.out.println("--------------------------------\nCONTA - MENU:\n 1 - CADASTRAR CONTA\n 2 - PESQUISAR CONTA\n 3 - ATUALIZAR CONTA\n 4 - REMOVER CONTA\n 0 - SAIR \n--------------------------------\nDigite o número da função desejada:");
                escolha = scanner.nextInt();

                switch (escolha) {
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
            } while (escolha != 0);
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private static void cadastrar(Scanner scanner, ContaDao dao) {
        System.out.print("Digite o ID do usuário: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Consumir a linha nova após o número

        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();

        System.out.print("Digite o saldo inicial: ");
        double saldoTotal = scanner.nextDouble();
        scanner.nextLine();

        int idConta = gerarId();

        Conta novaConta = new Conta(idConta, idUsuario, saldoTotal, numeroConta);

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
            System.out.println("Conta cadastrada com sucesso!");
        } else {
            System.out.println("Cadastro de conta cancelado.");
        }

    }

    private static void pesquisar(Scanner scanner, ContaDao dao) {
        System.out.print("Digite o ID da conta que deseja pesquisar: ");
        int idConta = scanner.nextInt();
        scanner.nextLine();

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
    private static void atualizar(Scanner scanner, ContaDao dao) {
        System.out.print("Digite o ID da conta que deseja atualizar: ");
        int idConta = scanner.nextInt();
        scanner.nextLine();

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
            int idUsuario = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite o novo número da conta (atual: " + conta.getNumeroConta() + "): ");
            String numeroConta = scanner.nextLine();

            System.out.print("Digite o novo saldo total (atual: " + conta.getSaldoTotal() + "): ");
            double saldoTotal = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("\nConfirma a atualização da conta? (s/n): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                conta.setIdUsuario(idUsuario);
                conta.setNumeroConta(numeroConta);
                conta.setSaldoTotal(saldoTotal);

                try {
                    dao.atualizar(conta);
                    System.out.println("Conta atualizada com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro ao atualizar a conta: " + e.getMessage());
                }
            } else {
                System.out.println("Atualização de conta cancelada.");
            }
        }

    }

    private static void remover(Scanner scanner, ContaDao dao) {
        System.out.print("Digite o ID da conta que deseja remover: ");
        int idConta = scanner.nextInt();
        scanner.nextLine();

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

    private static int gerarId() {
        return ++ultimoId;
    }
}
