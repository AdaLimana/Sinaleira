package Sinaleira;

public class ParametroVeiculos extends Fuzzy {
    

    ParametroVeiculos(){
        super();
    }
    /*
        Metodo que determina a pertinencia, (de 0,0 a 1,0),
        do valor repassado a ele. O valor repassado refere-se
        a quantidade de veiculos.
        O caculo de pertinencia dar-se-a atraves de uma funcao
        do 1 grau, uma reta, esta por sua vez, foi determinada
        pelas coordenadas dos pontos extremos, sendo eles:
        P1(8,0); P2(0,1), em que o eh X: a quantidade de 
        veiculos; o Y: a pertinencia.
    */
    public double determinaPertinenciaBaixa(double valor){
        
        double pertinencia;
        pertinencia = (((-1.0)/8.0)*valor)+1;/*a funcao foi determinada pelos pontos P1 e P2*/ 
        
        /*
            caso o valor repassado seja acima do determinado, no caso (0 a 8),
            a funcao retorna negativo, este deve ser considerado = 0;
        */
       
        if(pertinencia < 0){
            return 0;
        }
        return pertinencia;
    }
    
    /*
        Metodo que determina a pertinencia, (de 0,0 a 1,0),
        do valor repassado a ele. O valor repassado refere-se
        a quantidade de veiculos.
        O caculo de pertinencia dar-se-a atraves de duas funcoes
        do 1 grau, duas reta, a primeira foi determinada
        pelas coordenadas dos pontos extremos, sendo eles:
        Pa1(2,0); Pa2(8,1), a segunda foi determinada
        pelas coordenadas dos pontos extremos, sendo eles:
        Pb1(8,1); Pb2(14,0),em que o eh X: a quantidade de 
        veiculos; o Y: a pertinencia.
    */
    public double determinaPertinenciaMedia(double valor){
    
        double pertinencia;
        
        /*
            usa a primeira funcao, se o valor for maior que
            1, deve-se usar a segunda funcao
        */
        pertinencia = (valor/6.0)-(1.0/3.0);/*a funcao foi determinada pelos pontos Pa1 e Pa2*/
        /*
            caso o valor repassado seja abaixo do determinado, no caso (2 a 8),
            a funcao retorna negativo, este deve ser considerado = 0;
        */
       
        if(pertinencia < 0){
            return 0;
        }
        /*
            se for maior que um usa a segunda funcao
        */
        if(pertinencia <= 1){
            return pertinencia;
        }
        
        /*
            calcula a pertinencia usandoa funcao 2
        */
       
        pertinencia = (-1.0/6.0)*valor+(7.0/3.0);/*a funcao foi determinada pelos pontos Pb1 e Pb2*/
        /*
            caso o valor repassado seja acima do determinado, no caso (8 a 14),
            a funcao retorna negativo, este deve ser considerado = 0;
        */
        if(pertinencia < 0){
            return 0;
        }
        return pertinencia;
    }
    
    /*
        Metodo que determina a pertinencia, (de 0,0 a 1,0),
        do valor repassado a ele. O valor repassado refere-se
        a quantidade de veiculos.
        O caculo de pertinencia dar-se-a atraves de uma funcao
        do 1 grau, uma reta, esta por sua vez, foi determinada
        pelas coordenadas dos pontos extremos, sendo eles:
        P1(8,0); P2(16,1), em que o eh X: a quantidade de 
        veiculos; o Y: a pertinencia.
    */
    public double determinaPertinenciaAlta(double valor){
    
        double pertinencia;
        
        pertinencia = ((1.0/8.0)*valor)-1;/*a funcao foi determinada pelos pontos P1 e P2*/
        
        /*
            Se o valor estiver abaixo da faixa determinada (8 a 16)
            a funcao retornara negativo, este valor deve ser considerado (0)
        */
       
        if(pertinencia < 0){
            return 0;
        }
        
        /*
            Se o valor estiver acima da faixa determinada (8 a 16)
            a funcao retornara valor maior que 1, este valor deve ser considerado (1)
        */
        if(pertinencia > 1){
            return 1;
        }
        return pertinencia;
    }
    
    public String toString(){
    
        return String.format("\n\t##Parametro Quantidade veiculos##%s",super.toString());
    }
    
}
