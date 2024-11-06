package br.com.duckchain.view;
import br.com.duckchain.dao.ContaDao;
import br.com.duckchain.dao.TransacaoDao;
import br.com.duckchain.dao.UsuarioDao;
import br.com.duckchain.model.Conta;
import br.com.duckchain.model.Transacao;
import br.com.duckchain.model.Usuario;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TransacaoView {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        executar(scanner);
        scanner.close();

    }

    public  static void executar(Scanner scanner) {
        TransacaoDao dao;
        ContaDao contaDao;
        UsuarioDao usuarioDao;
        try{
            dao = new TransacaoDao();
            contaDao = new ContaDao();
            usuarioDao = new UsuarioDao();

            int escolha;
            do {
                System.out.println("--------------------------------\nTRANSFERENCIA - MENU:\n 1 - CADASTRAR TRANSAÇÃO\n 0 - SAIR \n--------------------------------\nDigite o número da função desejada:");
                escolha = scanner.nextInt();
                switch (escolha){
                    case 0:
                        System.out.println("Saindo e retornando ao MENU GERAL...");
                        break;
                    case 1:
                        cadastrar(scanner, dao, contaDao, usuarioDao);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente...");
                }
            } while (escolha !=0);
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private static void cadastrar(Scanner scanner, TransacaoDao dao, ContaDao contaDao, UsuarioDao usuarioDao) {
        try {
            System.out.print("ID do Usuário: ");
            int idUsuario = scanner.nextInt();

            Usuario usuario = usuarioDao.pesquisar(idUsuario);

            System.out.print("ID da Conta: ");
            int idConta = scanner.nextInt();

            Conta conta = contaDao.pesquisar(idConta);

            if (conta.getIdUsuario() != usuario.getId()) {
                throw new Exception("Essa conta não pertence a esse usuário!");
            }

            System.out.print("Tipo de Transação (1: Saque; 2: Depósito): ");
            int tpTransacao = scanner.nextInt();
            String tipoTransacao;

            if (tpTransacao != 1 && tpTransacao != 2) {
                throw new Exception("Tipo de Transação inválida!");
            }

            tipoTransacao = tpTransacao == 1 ? "Saque" : "Depósito";

            System.out.print("Valor da Transação: ");
            double valor = scanner.nextDouble();

            // se é um saque maior que o saldo não permite
            if (tpTransacao == 1 && valor>conta.getSaldoTotal()) {
                throw new Exception("Saldo insuficiente para saque!");
            }

            if (tpTransacao == 1) {
                conta.removeSaldo(valor);
            } else {
                conta.addSaldo(valor);
            }

            System.out.print("Descrição da Transação: ");
            scanner.nextLine(); // Consumir a linha pendente
            String descricao = scanner.nextLine();

            System.out.print("ID da Moeda: ");
            int idMoeda = scanner.nextInt();

            LocalDateTime dataHora = LocalDateTime.now();

            System.out.println("\n===== Dados da Transação =====");
            System.out.println("ID da Conta: " + idConta);
            System.out.println("ID do Usuário: " + idUsuario);
            System.out.println("Tipo de Transação: " + tipoTransacao);
            System.out.println("Valor: " + valor);
            System.out.println("Descrição: " + descricao);
            System.out.println("ID da Moeda: " + idMoeda);
            System.out.println("Data e Hora: " + dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            Transacao transacao = new Transacao(0, idConta, dataHora, tipoTransacao, valor, descricao, idUsuario, idMoeda);

            dao.cadastrar(transacao);

            contaDao.atualizar(conta);

            System.out.println("\nTransação cadastrada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar a transação: " + e.getMessage());
        }
    }
}



