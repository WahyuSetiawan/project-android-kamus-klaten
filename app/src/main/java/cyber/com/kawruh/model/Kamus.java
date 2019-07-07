package cyber.com.kawruh.model;

import android.os.Parcel;
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

    protected Kamus(Parcel in) {
        id = in.readInt();
        indonesia = in.readString();
        jawa = in.readString();
        category = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(indonesia);
        dest.writeString(jawa);
        dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Kamus> CREATOR = new Creator<Kamus>() {
        @Override
        public Kamus createFromParcel(Parcel in) {
            return new Kamus(in);
        }

        @Override
        public Kamus[] newArray(int size) {
            return new Kamus[size];
        }
    };

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
