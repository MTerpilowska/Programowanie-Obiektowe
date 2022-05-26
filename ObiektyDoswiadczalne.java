package Rozwoj;

public abstract class ObiektyDoswiadczalne{
    public int numer_obiektu;
    public int pozycja_x;
    public int pozycja_y;

    public int podjecie_decyzji() {
        return 1;
    }

    public int zwroc_numer() {
        return this.numer_obiektu;
    }

    public void ruch_bakterii( ObiektyDoswiadczalne[][] szalka ) {}
}