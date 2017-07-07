package Sinaleira;

public class ParametroTempo extends Fuzzy{
    
    ParametroTempo(){
        super();
    }
    /*
        Metodo que determina a pertinencia, (de 0,0 a 1,0),
        do valor repassado a ele. O valor repassado refere-se
        ao tempo que a sinaleira esta fechada(em minutos).
        O caculo de pertinencia dar-se-a atraves de uma funcao
        do 1 grau (uma reta), esta por sua vez foi determinada
        pelas coordenadas dos pontos extremos, sendo eles:
        P1(4,0); P2(0,1), em que o  X eh: tempo que a sinaleira
        esta fechada; o Y: a pertinencia.
    */
    public double determinaPertinenciaBaixa(double valor){
        
        double pertinencia;
        pertinencia = ((-valor)/4.0)+1;/*a funcao foi determinada pelos pontos P1 e P2*/ 
        
        /*
            caso o valor repassado seja acima do determinado, no caso (1 a 3),
            a funcao retorna negativo, este deve ser considerado = 0;
        */
       
        if(pertinencia < 0){
            return 0;
        }
        
        /*
            caso o valor repassado seja abaixo do determinado, no caso (1 a 3),
            a funcao retorna um valor maior que 1, este deve ser considerado = 1;
        */
        
        if(pertinencia > 1){
            return 1;
        }
        
        return pertinencia;
    }
    
    /*
        Metodo que determina a pertinencia, (de 0,0 a 1,0),
        do valor repassado a ele. O valor repassado refere-se
        ao tempo que a sinaleira esta fechada(em minutos).
        O caculo de pertinencia dar-se-a atraves de duas funcoes
        do 1 grau (duas reta), a primeira foi determinada
        pelas coordenadas dos pontos extremos, sendo eles:
        Pa1(0,0); Pa2(3,1), a segunda foi determinada
        pelas coordenadas dos pontos extremos, sendo eles:
        Pb1(3,1); Pb2(6,0),em que o X eh:tempo que a sinaleira
        esta fechada; o Y: a pertinencia.
    */
    public double determinaPertinenciaMedia(double valor){
    
        double pertinencia;
        
        /*
            usa a primeira funcao, se o valor for maior que
            1, deve-se usar a segunda funcao
        */
        pertinencia = (valor/3.0);/*a funcao foi determinada pelos pontos Pa1 e Pa2*/
        /*
            caso o valor repassado seja abaixo do determinado, no caso (1.5 a 3),
            a funcao retorna negativo, este deve ser considerado = 0;
        */
       
        if(pertinencia < 0){
            return 0;
        }
        /*
            se for maior que um, usa a segunda funcao
        */
        if(pertinencia <= 1){
            return pertinencia;
        }
        
        /*
            calcula a pertinencia usandoa funcao 2
        */
       
        pertinencia = (valor/3.0)-2.0;/*a funcao foi determinada pelos pontos Pb1 e Pb2*/
        /*
            caso o valor repassado seja acima do determinado, no caso (3 a 4.5),
            a funcao retorna negativo, este deve ser considerado = 0;
        */
        if(pertinencia < 0){
            return 0;
        }
        return pertinencia;
    }
    
    /*
        Metodo que determina a pertinencia, (de 0,0 a 1,0),
        do valor repassado a ele.O valor repassado refere-se
        ao tempo que a sinaleira esta fechada(em minutos).
        O caculo de pertinencia dar-se-a atraves de uma funcao
        do 1 grau, uma reta, esta por sua vez, foi determinada
        pelas coordenadas dos pontos extremos, sendo eles:
        P1(2,0); P2(6,1), em que o X eh: tempo que a sinaleira
        esta fechada; o Y: a pertinencia.
    */
    public double determinaPertinenciaAlta(double valor){
    
        double pertinencia;
        
        pertinencia = (valor/4.0)-0.5;/*a funcao foi determinada pelos pontos P1 e P2*/
        
        /*
            Se o valor estiver abaixo da faixa determinada (3 a 5)
            a funcao retornara negativo, este valor deve ser considerado (0)
        */
       
        if(pertinencia < 0){
            return 0;
        }
        
        /*
            Se o valor estiver acima da faixa determinada (3 a 5)
            a funcao retornara valor maior que 1, este valor deve ser considerado (1)
        */
        if(pertinencia > 1){
            return 1;
        }
        return pertinencia;
    }
    
    public String toString(){
    
        return String.format("\n\t##Parametro Tempo sinaleira fechada##%s",super.toString());
    }
    
}
