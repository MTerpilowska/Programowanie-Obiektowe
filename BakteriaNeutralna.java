package Rozwoj;

public class BakteriaNeutralna extends Bakteria{

    BakteriaNeutralna() {
        this.numer_obiektu = 2;
        this.dlugosc_zycia = 20;
    }

    BakteriaNeutralna(BakteriaNeutralna bakteria_n, int x, int y){
        this.dlugosc_zycia = bakteria_n.dlugosc_zycia;
        this.numer_obiektu = bakteria_n.numer_obiektu;
        this.pozycja_x = x;
        this.pozycja_y = y;
    }

    public void rozmieszczanie_bakterii(ObiektyDoswiadczalne[][] szalka, int ilosc_bakterii) {
        while (ilosc_bakterii > 0) {
            GeneratorLiczbLosowych generator = new GeneratorLiczbLosowych();
            int x1 = generator.losowanie(szalka.length);
            int y1 = generator.losowanie(szalka[0].length);
            if (szalka[x1][y1] == null) {
                szalka[x1][y1] = new BakteriaNeutralna();
                szalka[x1][y1].pozycja_x = x1;
                szalka[x1][y1].pozycja_y = y1;
                ilosc_bakterii--;
            }
        }
    }

    @Override
    public int sprawdzanie_pozycji(int numer_obiektu_ruchu) {
        return switch (numer_obiektu_ruchu) {
            case 0 -> 1; //+ zmiana pozycji
            case 4, 5 -> 2; //+ zjedzenie pozywienia
            default -> 0;
        };
    }
}
