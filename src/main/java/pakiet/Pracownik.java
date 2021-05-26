package pakiet;

public class Pracownik {
    private String imie;
    private double wyplata;

    public Pracownik(String imie, double wyplata) {
        this.imie = imie;
        this.wyplata = wyplata;
    }

    public Pracownik(String imie) {
        this.imie = imie;
        this.wyplata = 1000;
    }

    public String getImie() {
        return imie;
    }

    public double getWyplata() {
        return wyplata;
    }

    public void zwiekszWyplate(double wartosc) {
        this.wyplata += wartosc;
    }

    public boolean jestBogaczem() {
        return wyplata > 1500;
    }

    public String duzeImie() {
        return imie.toUpperCase();
    }
}
