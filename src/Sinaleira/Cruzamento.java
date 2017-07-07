package Sinaleira;

public class Cruzamento {
    
    private Sinaleira[] sinaleiras;

    Cruzamento (){

        sinaleiras = new Sinaleira[4];
        int i=0;
        while(i < 4){
            sinaleiras[i] = new Sinaleira(i);
            i++;
        }
    }

    /*
        Define a prioridade de abertura de cada uma das sinaleiras
    */
    public void definePrioridadesSinaleiras(){
        int i = 0;

        while(i < 4){

            sinaleiras[i].determinaPrioridade();
            i++;
        }

        int idSinaleiraAbre = 0;/*determina que a sinaleira 0 tem a melhor prioridade (antes do teste*/
        i = 1;/*parte de 1, pois compara a sinaleira 1(idSinaleiraAbre) com a sinaleira i 1 */
        
        /*
            Determina qual das sinaleiras tem a maior prioridade para
           ficar aberta
        */
        while(i<4){

            if(sinaleiras[idSinaleiraAbre].getDefuzzy() < sinaleiras[i].getDefuzzy()){

                sinaleiras[idSinaleiraAbre].setEstado("fechada");
                idSinaleiraAbre = i;
            }
            else{
               sinaleiras[i].setEstado("fechada");
            }
            i++;
        }
        sinaleiras[idSinaleiraAbre].setEstado("aberta");

    }
    
    public void mostraDadosSinaleiras(){
        int i = 0;
        while(i < 4){
            System.out.printf("\n%s",sinaleiras[i] );
            i++;
        }
    }

    /*
        Redefine os dados das sinaleiras apos
        o tempo de abertura, as sinaleiras que
        ficaram fechadas recebem um acrescimo
        de 1 minuto ao seu tempo e aumenta a
        quantidade de veiculos, jah a que ficou
        aberta tem seu tempo zerado, e a quantidade
        de veiculos tambem diminui.
    */

    public void atualizaDadosSinaleiras(){

        int i = 0;
        while(i < 4){
            String c = sinaleiras[i].getEstado();
            if(c.equals("fechada")){
                   sinaleiras[i].ficaFechadaSinaleira();
            }
            else{
                sinaleiras[i].abreSinaleira();
            }
                i++;
        }
    }

}
