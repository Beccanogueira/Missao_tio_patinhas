package br.com.duckchain.view;
import br.com.duckchain.dao.TransacaoDao;
import br.com.duckchain.model.Transacao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TransacaoView {
    private static int ultimoId = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        executar(scanner);
        scanner.close();

    }

    public  static void executar(Scanner scanner) {
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
        }
    }

    private static void cadastrar(Scanner scanner, TransacaoDao dao) {
        try {
            System.out.print("ID da Conta: ");
            int idConta = scanner.nextInt();

            System.out.print("ID do Usuário: ");
            int idUsuario = scanner.nextInt();

            System.out.print("Tipo de Transação (Ex: Compra, Venda, Transferência): ");
            String tipoTransacao = scanner.next();

            System.out.print("Valor da Transação: ");
            double valor = scanner.nextDouble();

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

            int id = gerarId();
            Transacao transacao = new Transacao(id, idConta, dataHora, tipoTransacao, valor, descricao, idUsuario, idMoeda);

            dao.cadastrar(transacao);
            System.out.println("\nTransação cadastrada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar a transação: " + e.getMessage());
        }
    }

    private static int gerarId() {
        return ++ultimoId;
    }
}



