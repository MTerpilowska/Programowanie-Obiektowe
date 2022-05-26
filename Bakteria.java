package Rozwoj;

public abstract class Bakteria extends ObiektyDoswiadczalne{
    public int dlugosc_zycia;

    public abstract void rozmieszczanie_bakterii(ObiektyDoswiadczalne[][] szalka, int ilosc_bakterii);

    @Override
    public int podjecie_decyzji() {
        GeneratorLiczbLosowych generator = new GeneratorLiczbLosowych();
        int decyzja = generator.losowanie(2);
        if (decyzja == 1) {
            int decyzja_rozmnazanie = generator.losowanie(2);
            if (decyzja_rozmnazanie == 1) {
                return 0;
            }
            else return 1;
        }
        else return 2;
    }

    public boolean sprawdzanie_czasu_zycia() {
        this.dlugosc_zycia--;
        return this.dlugosc_zycia > 0;
    }

    public int sprawdzanie_pozycji(int numer_obiektu_ruchu) {
        return 1;
    }

    private int wybieranie_pozycji(ObiektyDoswiadczalne[][] szalka) {
        int[] mozliwe_pozycje = {1, 2, 3, 4}; //wybieranie pozycji
        int x2 = 0, y2 = 0;
        int mozliwosc = 0;

        while (mozliwosc == 0) {
            int pozycja = 0;
            int losowy_index = 0;
            while (pozycja == 0) {
                GeneratorLiczbLosowych generator = new GeneratorLiczbLosowych();
                losowy_index = generator.losowanie(mozliwe_pozycje.length);
                pozycja = mozliwe_pozycje[losowy_index];
            }

            x2 = this.pozycja_x;
            y2 = this.pozycja_y;

            switch (pozycja) {
                case 1 -> x2 = this.pozycja_x - 1;
                case 2 -> x2 = this.pozycja_x + 1;
                case 3 -> y2 = this.pozycja_y - 1;
                case 4 -> y2 = this.pozycja_y + 1;
            }

            mozliwe_pozycje[losowy_index] = 0;
            if (x2 >= 0 && x2 < szalka.length && y2 >= 0 && y2 < szalka[0].length) // szalka[0].length - dlugosc pierwszego i w rezultacie kazdego wiersza
                mozliwosc = szalka[x2][y2] != null ? this.sprawdzanie_pozycji(szalka[x2][y2].zwroc_numer()) : 1;
            else mozliwosc = 0;

            for (int i = 0; i < 4; i++) {
                if (mozliwe_pozycje[i] != 0)
                    break;
                else if (i == 3) { // nie ma wiecej mozliwych pozycji
                    if (mozliwosc != 0) {
                        this.pozycja_x = x2; // zmiana pozycji
                        this.pozycja_y = y2;
                    }
                    return mozliwosc;
                }
            }
        }
        this.pozycja_x = x2;
        this.pozycja_y = y2;
        return mozliwosc;
    }

    public void ruch_bakterii( ObiektyDoswiadczalne[][] szalka) {
        if (this.sprawdzanie_czasu_zycia()) {
            int x1 = this.pozycja_x; // zapamietywanie poczatkowych pozycji
            int y1 = this.pozycja_y;
            int decyzja = this.podjecie_decyzji(); //decyzja bakterii, co chce robić
            if (decyzja == 1) //pozostanie w miejcu
                System.out.println("Pozostanie bakterii " + zwroc_numer()+ " w miejscu (" + this.pozycja_x +","+ this.pozycja_y+")") ;
            else if (decyzja == 0) { // rozmnazanie
                int mozliwosc = wybieranie_pozycji(szalka);

                if (mozliwosc == 0) {
                    System.out.println("Bakteria " + this.zwroc_numer() + " na pozycji (" + x1 +","+ y1 + ") zmarła");
                    szalka[x1][y1] = null; //jesli bakteria nie ma mozliwosci dzialania, umiera
                } else {
                    System.out.println("Rozmnazanie: w pozycji (" + x1 +","+ y1 + ") pojawia się nowa bakteria " + this.zwroc_numer());
                    System.out.println("Zmiana pozycji starej bakterii " +szalka[x1][y1].zwroc_numer()+ " z ("  + x1 +","+ y1 + ") na ("+this.pozycja_x+","+this.pozycja_y+")");
                    switch (szalka[x1][y1].zwroc_numer()) {
                        case 1 -> {
                            BakteriaDobra b_d = (BakteriaDobra) szalka[x1][y1];
                            szalka[this.pozycja_x][this.pozycja_y] = new BakteriaDobra(b_d, this.pozycja_x, this.pozycja_y);
                            szalka[x1][y1] = new BakteriaDobra();
                            szalka[x1][y1].pozycja_x = x1;
                            szalka[x1][y1].pozycja_y = y1;
                        }
                        case 2 -> {
                            BakteriaNeutralna b_d = (BakteriaNeutralna) szalka[x1][y1];
                            szalka[this.pozycja_x][this.pozycja_y] = new BakteriaNeutralna(b_d, this.pozycja_x, this.pozycja_y);
                            szalka[x1][y1] = new BakteriaNeutralna();
                            szalka[x1][y1].pozycja_x = x1;
                            szalka[x1][y1].pozycja_y = y1;
                        }
                        case 3 -> {
                            BakteriaAgresywna b_d = (BakteriaAgresywna) szalka[x1][y1];
                            szalka[this.pozycja_x][this.pozycja_y] = new BakteriaAgresywna(b_d, this.pozycja_x, this.pozycja_y);
                            szalka[x1][y1] = new BakteriaAgresywna();
                            szalka[x1][y1].pozycja_x = x1;
                            szalka[x1][y1].pozycja_y = y1;
                        }
                    }
                }
            }
            else { // zmiana pozycji
                int mozliwosc = wybieranie_pozycji(szalka);

                switch (mozliwosc) {
                    case 0 -> System.out.println("Bakteria " + this.zwroc_numer() + " na pozycji (" + x1 +","+ y1 + ") zmarła"); //jesli bakteria nie ma mozliwosci dzialania, umiera
                    case 1 -> System.out.println("Zmiana pozycji bakterii " + this.zwroc_numer() + " z (" + x1 +","+ y1 + ") na (" + this.pozycja_x +","+ this.pozycja_y+")");
                    case 2 -> System.out.println("Zjedzenie pożywienia " + szalka[this.pozycja_x][this.pozycja_y].zwroc_numer() + " przez bakterię " + this.zwroc_numer() + " na pozycji (" + this.pozycja_x +","+ this.pozycja_y+")");
                }
                if (mozliwosc != 0) {
                    switch (szalka[x1][y1].zwroc_numer()) {
                        case 1 -> {
                            BakteriaDobra b_d = (BakteriaDobra) szalka[x1][y1];
                            szalka[this.pozycja_x][this.pozycja_y] = new BakteriaDobra(b_d, this.pozycja_x, this.pozycja_y);
                        }
                        case 2 -> {
                            BakteriaNeutralna b_d = (BakteriaNeutralna) szalka[x1][y1];
                            szalka[this.pozycja_x][this.pozycja_y] = new BakteriaNeutralna(b_d, this.pozycja_x, this.pozycja_y);
                        }
                        case 3 -> {
                            BakteriaAgresywna b_d = (BakteriaAgresywna) szalka[x1][y1];
                            szalka[this.pozycja_x][this.pozycja_y] = new BakteriaAgresywna(b_d, this.pozycja_x, this.pozycja_y);
                        }
                    }
                }
                szalka[x1][y1] = null;
            }

        }
        else {
            szalka[this.pozycja_x][this.pozycja_y] = null;
            System.out.println("Bakteria " + this.zwroc_numer() + " na pozycji (" + this.pozycja_x+","+ this.pozycja_x + ") zmarła");}
    }
}
