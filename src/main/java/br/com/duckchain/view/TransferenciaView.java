package br.com.duckchain.view;
import br.com.duckchain.dao.ContaDao;
import br.com.duckchain.dao.TransferenciaDao;
import br.com.duckchain.dao.UsuarioDao;
import br.com.duckchain.model.Conta;
import br.com.duckchain.model.Transferencia;
import br.com.duckchain.model.Usuario;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TransferenciaView {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        executar(scanner);
        scanner.close();

    }

    public static void executar(Scanner scanner) {
        TransferenciaDao dao;
        ContaDao contaDao;
        UsuarioDao usuarioDao;
        try{
            dao = new TransferenciaDao();
            contaDao = new ContaDao();
            usuarioDao = new UsuarioDao();
            int escolha;
            do {
                System.out.println("--------------------------------\nTRANSFERENCIA - MENU:\n 1 - CADASTRAR TRANSFERENCIA\n 0 - SAIR \n--------------------------------\nDigite o número da função desejada:");
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

    private static void cadastrar(Scanner scanner, TransferenciaDao dao, ContaDao contaDao, UsuarioDao usuarioDao) {
        try {
            System.out.print("ID do Usuário que está realizando a transferência: ");
            int idUsuario = scanner.nextInt();

            Usuario usuario = usuarioDao.pesquisar(idUsuario);

            System.out.print("ID da Conta de Origem: ");
            int idContaOrigem = scanner.nextInt();

            Conta contaOrigem = contaDao.pesquisar(idContaOrigem);

            if (usuario.getId() != contaOrigem.getIdUsuario()) {
                throw new Exception("A conta origem não pertence ao usuário informado!");
            }

            System.out.print("ID da Conta de Destino: ");
            int idContaDestino = scanner.nextInt();

            Conta contaDestino = contaDao.pesquisar(idContaDestino);

            System.out.print("Valor da Transferência: ");
            double valor = scanner.nextDouble();

            if (valor > contaOrigem.getSaldoTotal()) {
                throw new Exception("Saldo insuficiente na conta origem!");
            }

            contaOrigem.removeSaldo(valor);
            contaDestino.addSaldo(valor);

            LocalDateTime dataTransferencia = LocalDateTime.now();

            System.out.println("\n===== Dados da Transferência =====");
            System.out.println("ID da Conta de Origem: " + idContaOrigem);
            System.out.println("ID da Conta de Destino: " + idContaDestino);
            System.out.println("Valor: " + valor);
            System.out.println("ID do Usuário: " + idUsuario);
            System.out.println("Data e Hora da Transferência: " + dataTransferencia.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            Transferencia transferencia = new Transferencia(0, idContaOrigem, idContaDestino, valor, idUsuario, dataTransferencia);

            dao.cadastrar(transferencia);
            contaDao.atualizar(contaOrigem);
            contaDao.atualizar(contaDestino);

            System.out.println("\nTransferência cadastrada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar a transferência: " + e.getMessage());
        }
    }
}