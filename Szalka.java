package Rozwoj;
import java.io.*;

public class Szalka {
    public int x, y;
    public ObiektyDoswiadczalne[][] pole;

    Szalka(int x, int y) {
        this.x = x;
        this.y = y;
        this.pole = new ObiektyDoswiadczalne[this.x][this.y];
    }

    public void etap(int dlugosc_symulacji){
        int dlugosc_sym = dlugosc_symulacji;
        FileWriter txt = null;
        try {
            txt = new FileWriter("wynik_symulacji.txt");
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        while(dlugosc_symulacji != 0){
            if(dlugosc_sym == dlugosc_symulacji) {
                try {
                    txt.write("LEGENDA:   1-Bakteria Dobra  2-Bakteria Neutralna  3-Bakteria Agresywna  4-Martwa Materia Organiczna  5-Zywa Materia Organiczna \n");
                    txt.write("\nETAP: 0 \n");
                    for (int i = 0; i < this.x; i++) {
                        txt.write("|");
                        for (int j = 0; j < this.y; j++) {
                            if (pole[i][j] != null) {
                                int nmr = this.pole[i][j].zwroc_numer();
                                txt.write(" " + nmr + " ");
                            } else txt.write(" 0 ");
                            //txt.write(" bla ");
                        }
                        txt.write(" |\n");
                    }
                } catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            System.out.format("Etap - %d\n", dlugosc_sym - dlugosc_symulacji + 1);
            int czy_pusto = 0;
            for(int i=0; i<this.x; i++)
            {
                for(int j=0; j<this.y; j++)
                {
                    if(this.pole[i][j] != null) {
                        int numer = this.pole[i][j].zwroc_numer();
                        System.out.println("numer na polu " + i +" "+ j + " : " + numer);
                        if (numer == 1 || numer == 2 || numer == 3 ) {
                            this.pole[i][j].ruch_bakterii(this.pole);
                            czy_pusto = 1;
                        }
                    }
                }
            }
            dlugosc_symulacji--;
            if(czy_pusto == 0) {
                System.out.println("Brak żyjących bakterii.");
                break;
            }
            else {
                try {
                    int numer_etapu = dlugosc_sym - dlugosc_symulacji;
                    txt.write("ETAP: " + numer_etapu + "\n");
                    for (int i = 0; i < this.x; i++) {
                        txt.write("|");
                        for (int j = 0; j < this.y; j++) {
                            if(pole[i][j] != null) {
                                int nmr = this.pole[i][j].zwroc_numer();
                                txt.write(" " + nmr + " ");
                            }
                            else txt.write(" 0 ");
                            //txt.write(" bla ");
                        }
                        txt.write(" |\n");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }
        try{txt.close();} catch(IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Koniec symulacji.");
    }

    public void rozmieszczanie(int ilosc_martwej_materii, int ilosc_zywej_materii, int liczba_bakt_dobrej, int liczba_bakt_neutralnej, int liczba_bakt_agresywnej) {

        Pozywienie pozywienie = new MartwaMateriaOrganiczna();
        pozywienie.rozmieszczanie_pozywienia(this.pole, ilosc_martwej_materii);

        pozywienie = new ZywaMateriaOrganiczna();
        pozywienie.rozmieszczanie_pozywienia(this.pole, ilosc_zywej_materii);

        Bakteria bakteria = new BakteriaDobra();
        bakteria.rozmieszczanie_bakterii(this.pole, liczba_bakt_dobrej);

        bakteria = new BakteriaNeutralna();
        bakteria.rozmieszczanie_bakterii(this.pole, liczba_bakt_neutralnej);

        bakteria = new BakteriaAgresywna();
        bakteria.rozmieszczanie_bakterii(this.pole, liczba_bakt_agresywnej);

    }
}
