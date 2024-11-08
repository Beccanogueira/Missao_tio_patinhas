package br.com.duckchain;
import br.com.duckchain.view.*;

import java.util.*;

public class Main
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        int escolha;
        System.out.println("\n>> Bem-vindo(a) ao DuckChain!!! <<");
        do{
            System.out.print("--------------------------------\n>> MENU GERAL:\n 1 - USUÁRIOS\n 2 - CONTAS\n 3 - MOEDAS/CRIPTOATIVOS\n 4 - TRANSAÇÕES\n 5 - TRANSFERÊNCIAS\n 0 - ENCERRAR PROGRAMA\n--------------------------------\nDigite o número da função desejada:");
            escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha){
                case 0:
                    System.out.println("Encerrando programa...");
                    break;
                case 1:
                    UsuarioView.executar(scanner);
                    break;
                case 2:
                    ContaView contaView = new ContaView("2 - CONTAS", scanner);
                    contaView.exibirMenu();
                    break;
                case 3:
                    MoedaView moedaView = new MoedaView("3 - MOEDAS/CRIPTOATIVOS", scanner);
                    moedaView.exibirMenu();
                    break;
                case 4:
                    TransacaoView.executar(scanner);
                    break;
                case 5:
                    TransferenciaView.executar(scanner);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente...");
            }
        }while (escolha!=0);
        scanner.close();
    }
}
