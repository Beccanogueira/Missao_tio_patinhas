package br.com.duckchain.view;

import br.com.duckchain.dao.UsuarioDao;
import br.com.duckchain.exception.EntidadeNaoEncontradaException;
import br.com.duckchain.model.Usuario;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class UsuarioView {
    private static int ultimoId = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        executar(scanner);
        scanner.close();
    }

    public static void executar(Scanner scanner) {
        UsuarioDao dao;
        try {
            dao = new UsuarioDao();
            int escolha;
            do {
                System.out.println("--------------------------------\nUSUARIO - MENU:\n 1 - CADASTRAR USUARIO\n 2 - PESQUISAR USUARIO\n 3 - ATUALIZAR USUARIO\n 4 - DELETAR USUARIO\n 0 - SAIR \n--------------------------------\nDigite o número da função desejada:");
                escolha = scanner.nextInt();
                switch (escolha) {
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

    private static void cadastrar(Scanner scanner, UsuarioDao dao) {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.next();

        System.out.print("Digite o e-mail: ");
        String email = scanner.next();

        System.out.print("Digite a senha: ");
        String senha = scanner.next();

        System.out.print("Digite o CPF: ");
        String cpf = scanner.next();

        int id = gerarId();
        LocalDateTime dataCriacao = LocalDateTime.now();

        Usuario novoUsuario = new Usuario(id, nome, email, senha, cpf);
        novoUsuario.setDataCriacao(dataCriacao);

        System.out.println("\nResumo do cadastro:");
        System.out.println("ID: " + novoUsuario.getId());
        System.out.println("Nome: " + novoUsuario.getNome());
        System.out.println("E-mail: " + novoUsuario.getEmail());
        System.out.println("CPF: " + novoUsuario.getCpf());
        System.out.println("Data de Criação: " + novoUsuario.getDataCriacao());

        System.out.print("\nDeseja confirmar o cadastro? (s/n): ");
        String confirmacao = scanner.next();

        if (confirmacao.equalsIgnoreCase("s")) {
            try {
                dao.cadastrar(novoUsuario);
                System.out.println("Usuário cadastrado com sucesso!");
            } catch (SQLException error) {
                System.out.println(error);
            }
        } else {
            System.out.println("Cadastro cancelado.");
        }

    }

    private static void pesquisar(Scanner scanner, UsuarioDao dao) {
        System.out.print("Digite o ID do usuário que deseja pesquisar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = null;
        try {
            usuario = dao.pesquisar(id);
        } catch (EntidadeNaoEncontradaException error) {
            System.out.println("Usuário com ID " + id + " não encontrado.");
            return;

        } catch (SQLException error) {
            System.out.println(error);
        }

        System.out.println("\nDetalhes do usuário:");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("E-mail: " + usuario.getEmail());
        System.out.println("CPF: " + usuario.getCpf());
        System.out.println("Data de Criação: " + usuario.getDataCriacao());
    }

    private static void atualizar(Scanner scanner, UsuarioDao dao) {
        System.out.print("Digite o ID do usuário que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = null;
        try {
            usuario = dao.pesquisar(id);
        } catch (EntidadeNaoEncontradaException error) {
            System.out.println("Usuário com ID " + id + " não encontrado.");
            return;

        } catch (SQLException error) {
            System.out.println(error);
        }

        System.out.println("\nDados atuais do usuário:");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("E-mail: " + usuario.getEmail());
        System.out.println("CPF: " + usuario.getCpf());
        System.out.println("Data de Criação: " + usuario.getDataCriacao());

        System.out.println("\n==== Informe os novos dados ou pressione ENTER para manter o valor atual ====");

        System.out.print("Novo nome (" + usuario.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.trim().isEmpty()) {
            usuario.setNome(nome);
        }

        System.out.print("Novo e-mail (" + usuario.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.trim().isEmpty()) {
            usuario.setEmail(email);
        }

        System.out.print("Nova senha (deixe em branco para manter a atual): ");
        String senha = scanner.nextLine();
        if (!senha.trim().isEmpty()) {
            usuario.setSenha(senha);
        }

        System.out.print("Novo CPF (" + usuario.getCpf() + "): ");
        String cpf = scanner.nextLine();
        if (!cpf.trim().isEmpty()) {
            usuario.setCpf(cpf);
        }

        System.out.println("\nConfirma a atualização dos dados?");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("E-mail: " + usuario.getEmail());
        System.out.println("CPF: " + usuario.getCpf());

        System.out.print("\nDeseja confirmar a atualização? (s/n): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            try {
                dao.atualizar(usuario);
            } catch (SQLException e) {
                System.out.println(e);
            }

            System.out.println("Usuário atualizado com sucesso!");
        } else {
            System.out.println("Atualização cancelada.");
        }
    }

    private static void remover(Scanner scanner, UsuarioDao dao) {
        System.out.print("Digite o ID do usuário que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = null;
        try {
            usuario = dao.pesquisar(id);
        } catch (EntidadeNaoEncontradaException error) {
            System.out.println("Usuário com ID " + id + " não encontrado.");
            return;

        } catch (SQLException error) {
            System.out.println(error);
        }

        if (usuario == null) {
            System.out.println("Usuário com ID " + id + " não encontrado.");
        } else {
            System.out.println("\nDetalhes do usuário:");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("E-mail: " + usuario.getEmail());
            System.out.println("CPF: " + usuario.getCpf());

            System.out.print("\nDeseja realmente remover este usuário? (s/n): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                try {
                    dao.remover(id);
                } catch (SQLException e) {
                    System.out.println(e);
                } catch (EntidadeNaoEncontradaException e) {

                }

                System.out.println("Usuário removido com sucesso!");
            } else {
                System.out.println("Remoção cancelada.");
            }
        }
    }

    private static int gerarId() {
        return ++ultimoId;
    }
}
