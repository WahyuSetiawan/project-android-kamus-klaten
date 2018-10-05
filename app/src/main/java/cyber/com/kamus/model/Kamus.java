package cyber.com.kamus.model;

import android.os.Parcelable;

public class Kamus implements Parcelable {
    private int id;
    private String indonesia;
    private String jawa;
    private String category;

    public Kamus(String indonesia, String jawa, String category) {
        this.indonesia = indonesia;
        this.jawa = jawa;
        this.category = category;
    }

    public Kamus(int id, String indonesia, String jawa, String category) {
        this.id = id;
        this.indonesia = indonesia;
        this.jawa = jawa;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndonesia() {
        return indonesia;
    }

    public void setIndonesia(String indonesia) {
        this.indonesia = indonesia;
    }

    public String getJawa() {
        return jawa;
    }

    public void setJawa(String jawa) {
        this.jawa = jawa;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
