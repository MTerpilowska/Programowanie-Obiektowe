package Rozwoj;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Podaj wymiary szalki Petriego ( x y ): ");
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();

        System.out.println("Lista dostepnych bakterii: "); // wypisanie listy bakterii
        System.out.println("numer    nazwa             dlugosc zycia     pozywienie");
        System.out.println("  1.   Bakteria Dobra           26         Martwa materia organiczna");
        System.out.println("  2.   Bakteria Neutralna       20         Martwa materia organiczna, Zywa materia organiczna");
        System.out.println("  3.   Bakteria Agresywna       15         Martwa materia organiczna, Zywa materia organiczna, Bakterie");

        int liczba_bakt_dobrej = 0;
        int liczba_bakt_neutralnej = 0;
        int liczba_bakt_agresywnej = 0;
        do {
            System.out.println("Podaj ilosc kolejnych bakterii, jakie mają zostać umieszczone na szalce: (w sumie maksymalnie " + (x * y) / 2 + " sztuk)");
            System.out.println("Podaj liczbę sztuk Bakterii Dobrej: ");
            liczba_bakt_dobrej = in.nextInt();
            System.out.println("Podaj liczbę sztuk Bakterii Neutralnej: ");
            liczba_bakt_neutralnej = in.nextInt();
            System.out.println("Podaj liczbę sztuk Bakterii Agresywnej: ");
            liczba_bakt_agresywnej = in.nextInt();
            if ((x * y) / 2 < liczba_bakt_dobrej + liczba_bakt_neutralnej + liczba_bakt_agresywnej)
                System.out.println("Podana liczba bakterii prekracza dopuszczalny limit");
        } while ((x * y) / 2 < liczba_bakt_dobrej + liczba_bakt_neutralnej + liczba_bakt_agresywnej);


        int ilosc_martwej_materii = 0;
        int ilosc_zywej_materii = 0;
        do {
            System.out.println("Podaj ilość pożywienia (jego ilość może wynosić maksymalnie " + (x * y) / 2 + ")");
            System.out.println("Podaj ilość martwej materii organicznej: ");
            ilosc_martwej_materii = in.nextInt();
            System.out.println("Podaj ilość żywej materii organicznej: ");
            ilosc_zywej_materii = in.nextInt();
            if ((x * y) / 2 < ilosc_martwej_materii + ilosc_zywej_materii)
                System.out.println("Podana ilość pożywienia prekracza dopuszczalny limit");
        } while ((x * y) / 2 < ilosc_martwej_materii + ilosc_zywej_materii);

        System.out.println("Podaj długość symulacji (ilość etapów): ");
        int dlugosc_symulacji = in.nextInt();


        Szalka szalka = new Szalka(x, y);
        szalka.rozmieszczanie(ilosc_martwej_materii, ilosc_zywej_materii, liczba_bakt_dobrej, liczba_bakt_neutralnej, liczba_bakt_agresywnej);
        szalka.etap(dlugosc_symulacji);


    }

}
