package de.androiduebung.reduce;

public class reduceExeption extends Exception{
    private int iZaehler;
    private int iNenner;

    public reduceExeption(String message ,int iZaehler, int iNenner) {
        super(message);
        this.iZaehler = iZaehler;
        this.iNenner = iNenner;
    }

    public int getiZaehler() {
        return iZaehler;
    }

    public void setiZaehler(int iZaehler) {
        this.iZaehler = iZaehler;
    }

    public int getiNenner() {
        return iNenner;
    }

    public void setiNenner(int iNenner) {
        this.iNenner = iNenner;
    }
}
