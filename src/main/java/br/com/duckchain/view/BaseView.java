package br.com.duckchain.view;
import java.util.Scanner;

public abstract class BaseView {
    protected Scanner scanner;
    protected String title;

    public BaseView(String title, Scanner scanner){
        this.title = title;
        this.scanner=scanner;
    }

    public void exibirMenu() {
        int escolha;
        do{
            System.out.print("\n--------------------------------" +
                    "\n>> MENU " + title +":"+
                    "\n 1 - CADASTRAR" +
                    "\n 2 - PESQUISAR" +
                    "\n 3 - LISTAR" +
                    "\n 4 - DELETAR " +
                    "\n 5 - ATUALIZAR" +
                    "\n 0 - SAIR " +
                    "\n--------------------------------" +
                    "\nDigite o número da função desejada:");
            escolha=lerInteiro();

            switch (escolha) {
                case 0:
                    System.out.println("Saindo e retornando ao MENU GERAL...");
                    break;
                case 1:
                    cadastrar();
                    break;
                case 2:
                    pesquisar();
                    break;
                case 3:
                    listar();
                    break;
                case 4:
                    remover();
                    break;
                case 5:
                    atualizar();
                    break;
                default:
                    System.err.println("Opção inválida. Verifique as opções do menu e tente novamente...");
            }
        }while (escolha!=0);
    }

    protected int lerInteiro(){
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida. Digite apenas números inteiros. Tente novamente...");
            }
        }
    }

    protected double lerDecimal(){
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida. Digite apenas valores numéricos. Tente novamente...");
            }
        }
    }

    protected abstract void cadastrar();

    protected abstract void listar();

    protected abstract void pesquisar();

    protected abstract void atualizar();

    protected abstract void remover();
}
