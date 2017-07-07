package Sinaleira;

import java.util.Random;

public class Sinaleira{
    
    private static int qntCriadas = 0;//variavel static, conta quantos objetos foram instanciados
    private int id;//identificador da Sinaleira
    private ParametroVeiculos veiculos;
    private ParametroTempo tempo;
    private double prioridadeAberturaBaixa;
    private double prioridadeAberturaMedia;
    private double prioridadeAberturaAlta;
    private double defuzzy;//recebe o valor da defazzyficacao
    private String estado;//O estado da Sinaleira(Aberta/Fechada)
    private int qntVeiculos;/*quantidade de veiculos na via */
    private int tempoAcumulado;/*tempo que a sinaleira esta fechada*/
    
    Sinaleira(int tempoA){
        Random r = new Random();
        id = qntCriadas;
        qntCriadas++;
        veiculos = new ParametroVeiculos();
        tempo = new ParametroTempo();
        tempoAcumulado = tempoA;
        
        qntVeiculos = qntVeiculos + 3+r.nextInt(4);//sortea de 3 a 6
        
        setTempo(tempoAcumulado);
        setQntVeiculos(qntVeiculos);
    }
    
    /*
        Passa a quantidade de viculos para
        o atributo do tipo ParametroVeiculos
    */
    public void setQntVeiculos(double valor){
        veiculos.determinaPertinecias(valor);
    }
    
    /*
        Passa o tempo que a sinaleira estah
        fechada para o atributo do tipo
        ParametroTempo
    */
    public void setTempo(double valor){
        tempo.determinaPertinecias(valor);
    }
    
    public double getDefuzzy(){return defuzzy;}
    
    public void setEstado(String est){
        estado = est;        
    }
    
    public String getEstado(){return estado;}
    
    /*
        Zera o tempo que a sinaleira esta fechada, e
        diminui a quantidade de carros na via, pois alguns 
        passam pela sinaleira
    */
    public void abreSinaleira(){
        Random r = new Random();
        tempoAcumulado = 0;
        qntVeiculos = qntVeiculos - 3+r.nextInt(4);//Sortea um numro entre 3 e 6, o numero sorteado eh subtraido da quantidade
                                                  //veihculos, ou seja, os veihculos que passaram pela sinaleira
        
        //Trata se caso passe mais veiculos do que tinha, o que nao pode acontecer
        if(qntVeiculos<0){
            qntVeiculos = 0;
        }
    
        setTempo(tempoAcumulado);
        setQntVeiculos(qntVeiculos);
    }
    
     
    /*
        quando a sinaleira permanece fechada
        eh incrementado um minuto ao seu tempo,
        pois esse eh o tempo que outra ficarah
        aberta, tambehm eh incrementado um valor
        aleatorio de veiculos, que chegam ateh a
        sinaleira fechada
    */
    public void ficaFechadaSinaleira(){
        Random r = new Random();
        tempoAcumulado = tempoAcumulado + 1;
        qntVeiculos = qntVeiculos + r.nextInt(5);

        setTempo(tempoAcumulado);
        setQntVeiculos(qntVeiculos);
    }

    
    
    
    public void determinaPrioridade(){
        prioridadeAberturaBaixa = regraPrioridadeAberturaBaixa();
        prioridadeAberturaMedia = regraPrioridadeAberturaMedia();
        prioridadeAberturaAlta =  regraPrioridadeAberturaAlta();
        defuzzyficacao();
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
    
    
    public void defuzzyficacao(){
           
        double baixa = 100.0; /*(10+20+30+40) faixa determinada para prioridade baixa*/
        double media = 180.0; /*(50+60+70) faixa determinada para prioridade media*/
        double alta = 270.0;  /*(80+90+100) faixa determinada para prioridade alta*/       
        
        defuzzy = ((baixa*prioridadeAberturaBaixa)+(media*prioridadeAberturaMedia)+(alta*prioridadeAberturaAlta))/((4.0*prioridadeAberturaBaixa)+(3.0*prioridadeAberturaMedia)+(3.0*prioridadeAberturaAlta));
        
    }
    
    public String toString(){

        return  String.format("\n#######################################################\n"
                        +       "#                       Sinaleira %d                  #\n"
                        +       "#######################################################\n"
                        +       "#                  Quantidade de Veiculos  %d         #\n"
                        +       "#######################################################\n"
                        +       "%s\n"
                        +       "#######################################################\n"
                        +       "#                          Tempo   %d                 #\n"
                        +       "#######################################################\n"
                        +       "%s\n"
                        +       "#######################################################\n"
                        +       "#             Prioridade Abertura Sinaleira           #\n"
                        +       "#######################################################\n"
                        +       "#       Baixa = %.2f       Media = %.2f  Alta = %.2f  #\n"
                        +       "#######################################################\n"
                        +       "#      Defuzzy = %.2f  Sinaleira %s             #\n"
                        +       "#######################################################\n",
                                id, qntVeiculos, veiculos, tempoAcumulado, tempo, prioridadeAberturaBaixa, prioridadeAberturaMedia, prioridadeAberturaAlta, defuzzy, estado);
    }
    
}