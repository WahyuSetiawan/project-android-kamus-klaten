package cyber.com.kamus.model;

public class Kategori {
    private int id;
    private String name;
    private String drawable;

    public Kategori(String nama, String drawable) {
        this.drawable = drawable;
        this.name = nama;
    }

    public Kategori(int id, String nama, String gambar) {
        this.id = id;
        this.name = nama;
        this.drawable = gambar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
