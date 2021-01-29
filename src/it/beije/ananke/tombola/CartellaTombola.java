package it.beije.ananke.tombola;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CartellaTombola {

    private int[] numeriCartella = new int[15];
    private int[][] cartella = new int[3][9];
    private int id;

    //i contatori delle decine li metto in un array.
    //così è più facile lavorarci e prenderne il valore

    int[] decine = new int[9];

    //i contatori delle righe li metto in un array

    int righe[] = new int[3];

    public CartellaTombola(int id){
        this.id = id;
    }

    public ArrayList<ArrayList<Integer>> creaCartella(ArrayList<ArrayList<Integer>> disponibili){

        Random random = new Random();

        int cartelleMancanti = 0;
        for (ArrayList<Integer> colonna: disponibili) {
            cartelleMancanti += colonna.size();
        }
        cartelleMancanti /= 15;

        int colonneDaUnNumero = 0;
        int countInserimento = 0;

        for (int i = 0; i < disponibili.size(); i++) {

            ArrayList<Integer> colonna = disponibili.get(i);
            int numeriDaEstrarre = 0;
            int numeriDisponibili = colonna.size();

            if(numeriDisponibili == cartelleMancanti*2){
                //devo per forza pescarne due se no non riesco a finire i novanta numeri
                numeriDaEstrarre = 2;
            }
            else{
                if(numeriDisponibili > cartelleMancanti){
                    //posso randomicamente pescarne due o uno

                    //se però sono alla fine che non ho ancora avuto almeno una colonna da un solo numero
                    //devo per forza mettere solo un numero

                    //TODO:no non va bene così
                    if((colonneDaUnNumero < 3) && (disponibili.size() - i > colonneDaUnNumero) ){
                        numeriDaEstrarre = 1;
                        colonneDaUnNumero++;
                    }
                    else{
                        if(colonneDaUnNumero < 3) {
                            //posso pescare a random
                            numeriDaEstrarre = random.nextInt(2) + 1;
                            if (numeriDaEstrarre == 1)
                                colonneDaUnNumero++;
                        }
                        else{
                            numeriDaEstrarre = 2;
                        }
                    }
                }
                else{
                        if(numeriDisponibili == cartelleMancanti){
                            //devo per forza pescarne uno altrimenti non ho numeri per le altre cartelle
                            numeriDaEstrarre = 1;
                            colonneDaUnNumero++;
                        }
                        //non potrò (perché non devo fare in modo) avere numDisp < cartelleMancanti

                    }
                }

            while (numeriDaEstrarre > 0){

                //pesco un indice a caso dell'arrayList di colonna
                //e prendo il numero a quell'indice
                int num = colonna.get(random.nextInt(numeriDisponibili-1));

                //avendolo pescato dai disponibili, sono sicuro che
                //non l'ho già preso in un'altra cartella o che l'abbia già pescato

                //solo se non sono arrivato già a 15 inserisco
                if(countInserimento < 15) {
                    //inserisco nell'array
                    numeriCartella[countInserimento] = num;
                    //aumento il contatore delle decine
                    decine[i]++;

                    countInserimento++;
                    //rimuovo il numero dalla lista
                    disponibili.get(i).remove((Integer) num);

                    numeriDaEstrarre--;
                }

            }

        }


        Arrays.sort(numeriCartella);

        inserisciNumeriInCartella();

        return disponibili;

    }

    private int decina(int numero){

        if(numero == 90)
            return 8;
        else
            return numero/10;
    }

    private void inserisciNumeriInCartella(){

        for (int i=0; i<numeriCartella.length; i++) {

            //se ho un solo numero per decina, chiamo la funzione che in colonna ne inserisce uno solo
            //altrimenti, prendo anche il prossimo numero della decina e chiamo la funzione che ne inserisce due
            if(decine[decina(numeriCartella[i])]<2){
                inserisciUnNum(numeriCartella[i]);
            }
            else{
                //ho due numeri della stessa decina
                //prendo anche il secondo e chiamo l'altra funzione
                inserisciDueNum(numeriCartella[i], numeriCartella[i+1]);

                //avendo però inserito due numeri e non uno solo
                //incremento ulteriormente il contatore del ciclo
                //per saltare il prossimo numero in numeriCartella
                //perché già trattato
                i++;
            }
        }

    }

    private void inserisciUnNum(int numero){

        //ho un solo numero da aggungere in colonna. posso metterlo in qualsiasi riga a caso,
        //controllando però che la riga non sia già piena

        Random random = new Random();
        boolean inserito = false;

        while(!inserito) {

            //trovo la riga he ha più elementi, e la riga con meno elementi
            int maxRiga = 0;
            int minRiga = 6;    //inizializzo a un valore più grande del max raggiungibile dalle righe
            int indexMax = 0;
            int indexMin = 0;

            for (int i = 0; i < righe.length; i++) {

                if(righe[i] > maxRiga) {
                    maxRiga = righe[i];
                    indexMax = i;
                }
                if(righe[i] < minRiga) {
                    minRiga = righe[i];
                    indexMin = i;
                }

            }

            int riga;
            //se ho una riga con uno scarto troppo alto, preferisco quella riga
            if(maxRiga - minRiga >= 2){
                riga = indexMin;
            }
            else{
                riga = random.nextInt(3);           //0, 1, 2. gli indici corretti delle tre righe
            }

            //se ho uno scarto di due tra la riga max e la riga min, allora scelgo la riga min come riga da riempire
            //if(max)
            //se la riga è già piena, allora assolutamente bisogna evitarla
            if(righe[riga] >= 5)
                continue;
            //ma se ho una riga piena, una da quattro e una da tre, devo preferire quella da tre

            //la riga non è piena, la riga va sicuro bene
            //inserisco il numero nella riga scelta e nella colonna della sua decina
            cartella[riga][decina(numero)] = numero;
            //AGGIORNO IL CONTATORE DELLA RIGA PER DIRE CHE HO AGGIUNTO UN NUMERO
            righe[riga]++;

            inserito = true;

        }

    }

    private void inserisciDueNum(int primo, int secondo){

        //ho due numeri da inserire. posso inserirli in tre modi:
        //primo, secondo, \b
        //primo, \b, secondo
        //\b, primo, secondo
        //però se una delle righe è già completa, ho solo una possibilità
        //su come inserirle, a seconda della riga piena.

        Random random = new Random();
        int rigaPrimo;
        int rigaSecondo;

        //il primo, può essere messo solamente in prima o seconda posizione.
        //quindi tra queste due righe devo vedere se una delle due ha tanto scarto
        //e scegliere quella nel caso. altrimenti random.

        //poi anche il secondo può andare solo nella seconda o nella terza riga
        //se alla fine il primo lo abbiamo messo alla seconda riga, sicuro
        //devo metterlo nella terza. altrimenti ancora guardo se una delle due
        //ha tanto scarto e scelgo quella, altrimenti random.

        //mettamo il primo
        //prima però: se la riga0 ha 5 elementi, metto in riga1
        //            se la riga1 ha 5 elementi, metto in riga0
        if(righe[0] >= 5){
            rigaPrimo = 1;
        }
        else{
            if (righe[1] >= 5) {
                rigaPrimo = 0;
            }
            else{
                //scelgo la riga[0] se riga[1]-riga[0]>=2 o se riga[2]-riga[0]>=2
                if((righe[1] - righe[0] >= 2) || (righe[2] - righe[0] >= 2)){
                    rigaPrimo = 0;
                }
                else{
                    //scelgo la riga[1] se riga[0]-riga[1]>=2 o se riga[2]-riga[1]>=2
                    if((righe[0] - righe[1] >= 2) || (righe[2] - righe[1] >= 2)){
                        rigaPrimo = 1;
                    }
                    else{
                        //se non ho troppo scarto allora scelgo random
                        rigaPrimo = random.nextInt(2);
                    }
                }
            }
        }

        ///////////////////////////////////////////////////////////////////

        //ora abbiamo capito che riga occupare con primo.
        //capiamo secondo
        if(rigaPrimo == 1){
            //allora per forza di cose secondo deve stare alla riga 2
            rigaSecondo = 2;
        }
        else {
            //allora posso scegliere tra riga 1 o riga 2
            //prima però: se la riga1 ha 5 elementi, metto in riga2
            //            se la riga2 ha 5 elementi, metto in riga1
            if (righe[1] >= 5) {
                rigaSecondo = 2;
            } else {
                if (righe[2] >= 5) {
                    rigaSecondo = 1;
                } else {
                    if (righe[1] - righe[2] >= 2) {
                        //ho tanto scarto tra le due righe, uso la 2 che ha meno roba
                        rigaSecondo = 2;
                    } else {
                        if (righe[2] - righe[1] >= 2) {
                            //ho tanto scarto tra le due righe, uso la 1 che ha meno roba
                            rigaSecondo = 1;
                        } else {
                            //altrimenti, sono simili e potendo scegliere tra le due, scelgo a caso
                            rigaSecondo = random.nextInt(2) + 1;
                        }
                    }
                }
            }
        }

        //ora ho scelto le due righe. passo a inserire
        cartella[rigaPrimo][decina(primo)] = primo;
        cartella[rigaSecondo][decina(secondo)] = secondo;

        //AGGIORNO I CONTATORI DELLA RIGA PER DIRE CHE HO AGGIUNTO UN NUMERO
        righe[rigaPrimo]++;
        righe[rigaSecondo]++;

    }

    public void stampaCartella(){

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if(cartella[i][j] != 0)
                    System.out.print(cartella[i][j] + "\t");
                else
                    System.out.print(" " + "\t");
            }
            System.out.print("\n");
        }

        /*
        for (int i: numeriCartella) {
            System.out.print(i + " ");
        }

         */

    }

    public int getId() {
        return id;
    }
}
