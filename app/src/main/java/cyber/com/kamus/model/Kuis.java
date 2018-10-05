package cyber.com.kamus.model;

import java.util.ArrayList;

public class Kuis {
    private Integer urut;
    private boolean status;
    private Kamus kamus;
    private ArrayList<Kamus> kamuses = new ArrayList<>();

    private int jawaBenar;
    private int jawab;

    public Integer getUrut() {
        return urut;
    }

    public void setUrut(Integer urut) {
        this.urut = urut;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Kamus getKamus() {
        return kamus;
    }

    public void setKamus(Kamus kamus) {
        this.kamus = kamus;
    }

    public ArrayList<Kamus> getKamuses() {
        return kamuses;
    }

    public void setKamuses(ArrayList<Kamus> kamuses) {
        this.kamuses = kamuses;
    }

    public int getJawaBenar() {
        return jawaBenar;
    }

    public void setJawaBenar(int jawaBenar) {
        this.jawaBenar = jawaBenar;
    }

    public int getJawab() {
        return jawab;
    }

    public void setJawab(int jawab) {
        this.jawab = jawab;
    }
}
