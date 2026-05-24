package SistemaDeFinancas;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        TransacaoDAO dao = new TransacaoDAO();


        int opcao;

        do {
            System.out.println("\n===SISTEMA FINANCEIRO==");
            System.out.println("1 - Cadastrar receita");
            System.out.println("2 - Cadastrar despesas");
            System.out.println("3 - Listar receitas");
            System.out.println("4 - Listar despesas");
            System.out.println("5 - Ver saldo");
            System.out.println("6 - Excluir transação");
            System.out.println("7 - Atualizar transação");
            System.out.println("0 - Sair");

            System.out.println("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Descrição da receita: ");
                    String descReceita = sc.nextLine();

                    System.out.println("Valor: ");
                    double valorReceita = sc.nextDouble();

                    Transacao t1 = new Transacao(descReceita, valorReceita, "ENTRADA");

                    dao.cadastrar(t1);


                    System.out.println("Receita cadastrada! ");
                    break;

                case 2:
                    System.out.println("Descrição da despesa: ");
                    String descDespesa = sc.nextLine();

                    System.out.println("Valor: ");
                    double valorDespesa = sc.nextDouble();

                    Transacao t2 = new Transacao(
                            descDespesa,
                            valorDespesa,
                            "SAIDA"
                    );
                    dao.cadastrar(t2);

                    break;

                case 3:

                    ArrayList<Transacao> lista = dao.listar();
                    System.out.println("\n===RECEITAS==");

                    for (Transacao t3 : lista) {
                        t3.mostrar();
                        System.out.println("----------------------");
                    }

                    break;

                case 4:
                    ArrayList<Transacao> listad = dao.listar();
                    System.out.println("\n===DESPESA===");

                    for (Transacao t4 : listad) {
                        t4.mostrar();
                        System.out.println("-------------------");
                    }
                    break;

                case 0 :
                    System.out.println("Sistema encerrado!");
                    break;

                case 5:

                    ArrayList<Transacao> listaSaldo = dao.listar();
                    double saldo = dao.pegarSaldo();

                    System.out.println("\n===SALDO ATUAL===");
                    System.out.println("R$ " + saldo);
                    break;

                case 6:
                    System.out.println("Digite o ID da transacão: ");
                    int idExcluir = sc.nextInt();

                    dao.excluir(idExcluir);
                    break;

                case 7:
                    System.out.println("Digite o ID da transação: ");
                    int idAtualizar = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nova descrição: ");
                    String novaDescricao = sc.nextLine();

                    System.out.print("Novo valor: ");
                    double novoValor = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Novo tipo ENTRADA/SAIDA: ");
                    String novoTipo = sc.nextLine();

                    dao.atualizar(idAtualizar, novaDescricao, novoValor, novoTipo);
                    break;

                default:
                    System.out.println("Opcao invalida! ");

            }
        } while (opcao != 0);

    }
}
