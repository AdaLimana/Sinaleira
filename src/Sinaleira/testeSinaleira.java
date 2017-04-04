/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinaleira;

import java.util.Scanner;
/**
 *
 * @author imortal77
 */
public class testeSinaleira {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        Sinaleira s = new Sinaleira();
        Scanner input = new Scanner(System.in);
        double valor;
        int i = 0;
        while(i<10){
            
            System.out.println("Informe a quantidade de veiculos");
            valor = input.nextDouble();
            s.setQntVeiculos(valor);
            System.out.println("Informe a quantidade de tempo");
            valor = input.nextDouble();
            s.setTempo(valor);
            s.determinaPrioridade();
            
            System.out.printf("%s",s);
            i++;
        }
    }
    
}