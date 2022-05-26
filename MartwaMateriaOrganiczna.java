package Rozwoj;

public class MartwaMateriaOrganiczna extends Pozywienie{

    MartwaMateriaOrganiczna(){
        this.numer_obiektu = 4;
    }

    public void rozmieszczanie_pozywienia(ObiektyDoswiadczalne[][] szalka, int ilosc_materii) { // na poczatku programu - losowo
        while (ilosc_materii > 0) {
            GeneratorLiczbLosowych generator = new GeneratorLiczbLosowych();
            int x1 = generator.losowanie(szalka.length);
            int y1 = generator.losowanie(szalka[0].length);
            if (szalka[x1][y1] == null) {
                szalka[x1][y1] = new MartwaMateriaOrganiczna();
                ilosc_materii--;
            }
        }
    }
}