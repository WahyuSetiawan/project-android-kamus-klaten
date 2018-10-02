package cyber.com.kamus.model;

public class Kategori {
    String name;
    Integer drawable;

    public Kategori(Integer drawable, String name){
        this.drawable = drawable;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDrawable() {
        return drawable;
    }

    public void setDrawable(Integer drawable) {
        this.drawable = drawable;
    }
}
