/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sinaleira;

import java.util.Random;
/**
 *
 * @author imortal77
 */
public class Sinaleira{
    
    private ParametroVeiculos veiculos;
    private ParametroTempo tempo;
    private double prioridadeAberturaBaixa;
    private double prioridadeAberturaMedia;
    private double prioridadeAberturaAlta;
   // private int qntVeiculos;/*quantidade de veiculos na via */
   // private int tempoAcumulado;/*tempo que a sinaleira esta fechada*/
    
    Sinaleira(/*int tempoA*/){
        veiculos = new ParametroVeiculos();
        tempo = new ParametroTempo();
      //  tempoAcumulado = tempoA;
        
    }
    
    
    public void setQntVeiculos(double valor){
        veiculos.determinaPertinecias(valor);
    }
    
    public void setTempo(double valor){
        tempo.determinaPertinecias(valor);
    }
    
    /*
        Zera o tempo que a sinaleira esta fechada, e
        diminui a quantidade de carros na via, pois alguns 
        passam pela sinaleira
    */
    /*public void abreSinaleira(){
        Random r = new Random();
        tempoAcumulado = 0;
        qntVeiculos = qntVeiculos - 3+r.nextInt(3-qntVeiculos)
        
    
    }*/ 
    
    public void determinaPrioridade(){
        prioridadeAberturaBaixa = regraPrioridadeAberturaBaixa();
        prioridadeAberturaMedia = regraPrioridadeAberturaMedia();
        prioridadeAberturaAlta =  regraPrioridadeAberturaAlta();           
    }
    
    /*
        T = tempo;
        Q = veiculos;
        B,M,A = baixo, media, alto;
        Eh definida pela regra 
        {T(B) ou [Q(B) e T(M)]} = PrioridadeAbertaBaixa
    */
    private double regraPrioridadeAberturaBaixa(){
        
        double prioridade;
        
        /*
            implementa a parte [Q(B) e T(M)],
            em que o menor valor eh considerado
        */
        if(veiculos.getPertinenciaBaixa() <= tempo.getPertinenciaMedia()){
            
            prioridade = veiculos.getPertinenciaBaixa();
        }
        else{
            prioridade = tempo.getPertinenciaMedia();
        }
        
        /*
            implementa a parte [T(B) ou o_resultado_acima]
            em que o maior valor eh considerado
        */
        if(tempo.getPertinenciaBaixa() > prioridade){
            prioridade = tempo.getPertinenciaBaixa();
        }
        return prioridade;
    }
    
    /*
        T = tempo;
        Q = veiculos;
        B,M,A = baixo, media, alto;
        Eh definida pela regra 
        {[(Q(B) ou Q(M)) e T(A)] ou [Q(M) e T(M)]} = PrioridadeAberturaMedia
    */
    private double regraPrioridadeAberturaMedia(){
        
        double prioridade;
        double aux;
        /*
            implementa a parte [Q(B) ou Q(M)]
            em que o maior valor eh considerado
        */
        if(veiculos.getPertinenciaBaixa() >= veiculos.getPertinenciaMedia()){
            prioridade = veiculos.getPertinenciaBaixa();
        }
        else{
            prioridade = veiculos.getPertinenciaMedia();
        }
        
        /*
            implementa a parte
            [valor_obtido_acima e T(A)] em
            que o menor valor eh considerado
        */
        if(tempo.getPertinenciaAlta() < prioridade){
            prioridade = tempo.getPertinenciaAlta();
        
        }
        
        /*
            implementa a parte [Q(M) e T(M)]
            em que o menor valor obtido eh considerado
        */
        if(veiculos.getPertinenciaMedia() <= tempo.getPertinenciaMedia()){
            aux = veiculos.getPertinenciaMedia();

        }
        else{
            aux = tempo.getPertinenciaMedia();
        }
        
        /*
            implementa a parte [(prioridade) ou (aux) ]
            em que o maior valor eh considerado
        */
        if(prioridade < aux){
            prioridade = aux;
            
        }
        
        return prioridade;
 
    }
    
    
    /*
        T = tempo;
        Q = veiculos;
        B,M,A = baixo, media, alto;
        Eh definida pela regra 
        {Q(A) e [T(M) ou T(A)]} = PrioridadeAberturaAlta
    */
    private double regraPrioridadeAberturaAlta(){
        double prioridade;
        
        /*
            implementa a parte [T(M) ou T(A)]
            em que o maior valor eh considerado
        */
        if(tempo.getPertinenciaMedia() >= tempo.getPertinenciaAlta()){
            prioridade = tempo.getPertinenciaMedia();
        
        }
        else{
            prioridade = tempo.getPertinenciaAlta();
        }
        
        /*
            implementa a parte
            [valor_obtido_acima e Q(a)] em
            que o menor valor eh considerado
        */
        if(veiculos.getPertinenciaAlta() < prioridade ){
            prioridade = veiculos.getPertinenciaAlta();
        }
        return prioridade;
    }
    
    
    public double defuzzyficacao(){
    
        double defuzzy;
        
        double baixa = 100.0; /*(10+20+30+40) faixa determinada para prioridade baixa*/
        double media = 180.0; /*(50+60+70) faixa determinada para prioridade media*/
        double alta = 270.0;  /*(80+90+100) faixa determinada para prioridade alta*/       
        
        defuzzy = ((baixa*prioridadeAberturaBaixa)+(media*prioridadeAberturaMedia)+(alta*prioridadeAberturaAlta))/((4.0*prioridadeAberturaBaixa)+(3.0*prioridadeAberturaMedia)+(3.0*prioridadeAberturaAlta));
        return defuzzy;
    }
    
    public String toString(){
    
        return String.format("%s %s\t##Prioridade de Abertura Sinaleira##\n"
                +            "Baixa = %f\n Media = %f\n Alta = %f\n Defuzzy = %f\n",veiculos, tempo, prioridadeAberturaBaixa,prioridadeAberturaMedia,prioridadeAberturaAlta, defuzzyficacao());
    }
    
}
