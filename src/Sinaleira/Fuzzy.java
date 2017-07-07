/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinaleira;

/**
 *
 * @author imortal77
 */
public abstract class Fuzzy {
    
    private double pertinenciaBaixa;
    private double pertinenciaMedia;
    private double pertinenciaAlta;
   
    
    public void determinaPertinecias(double valor){
        pertinenciaBaixa = determinaPertinenciaBaixa(valor);
        pertinenciaMedia = determinaPertinenciaMedia(valor);
        pertinenciaAlta = determinaPertinenciaAlta(valor);
    
    }
    
    public abstract double determinaPertinenciaBaixa(double valor);
    public abstract double determinaPertinenciaMedia(double valor);
    public abstract double determinaPertinenciaAlta(double valor);
    
    public double getPertinenciaBaixa(){return pertinenciaBaixa;}
    public double getPertinenciaMedia(){return pertinenciaMedia;}
    public double getPertinenciaAlta(){return pertinenciaAlta;}
    
    public String toString(){
        return String.format("#Pertinencia Baixa = %.2f                             #\n"
                +            "#Pertinencia media = %.2f                             #\n"
                +            "#Pertinencia Alta = %.2f                              #",
                             pertinenciaBaixa, pertinenciaMedia, pertinenciaAlta);
    }

    
}
