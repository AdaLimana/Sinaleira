package Sinaleira;

import java.util.Scanner;

public class testeSinaleira {

    public static void main(String[] args) {
        String opcao = "0";
        Scanner input = new Scanner(System.in);
        Cruzamento c = new Cruzamento();

        while(!opcao.equals("q")){

            c.definePrioridadesSinaleiras();
            c.mostraDadosSinaleiras();
            c.atualizaDadosSinaleiras();
            System.out.println("Digite qualquer valor para continuar, ou Q para sair");
            opcao = input.nextLine();
            System.out.println(opcao);
        }
    }
    
}