package br.com.duckchain.view;
import br.com.duckchain.dao.TransferenciaDao;
import br.com.duckchain.model.Transferencia;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TransferenciaView {

    private static int ultimoId = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        executar(scanner);
        scanner.close();

    }

    public static void executar(Scanner scanner) {
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
        }
    }

    private static void cadastrar(Scanner scanner, TransferenciaDao dao) {
        try {
            System.out.print("ID da Conta de Origem: ");
            int idContaOrigem = scanner.nextInt();

            System.out.print("ID da Conta de Destino: ");
            int idContaDestino = scanner.nextInt();

            System.out.print("Valor da Transferência: ");
            double valor = scanner.nextDouble();

            System.out.print("ID do Usuário que está realizando a transferência: ");
            int idUsuario = scanner.nextInt();

            LocalDateTime dataTransferencia = LocalDateTime.now();

            System.out.println("\n===== Dados da Transferência =====");
            System.out.println("ID da Conta de Origem: " + idContaOrigem);
            System.out.println("ID da Conta de Destino: " + idContaDestino);
            System.out.println("Valor: " + valor);
            System.out.println("ID do Usuário: " + idUsuario);
            System.out.println("Data e Hora da Transferência: " + dataTransferencia.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            int id = gerarId();
            Transferencia transferencia = new Transferencia(id, idContaOrigem, idContaDestino, valor, idUsuario, dataTransferencia);

            dao.cadastrar(transferencia);
            System.out.println("\nTransferência cadastrada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar a transferência: " + e.getMessage());
        }
    }

    private static int gerarId() {
        return ++ultimoId;
    }
}