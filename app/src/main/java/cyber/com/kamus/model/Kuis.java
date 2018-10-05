package cyber.com.kamus.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Kuis implements Parcelable {
    private Integer urut;
    private boolean status;
    private Kamus kamus;
    private ArrayList<Kamus> dataJawabKamusLain = new ArrayList<>();

    private int jawaBenar;
    private int jawab;

    public Kuis() {
    }

    protected Kuis(Parcel in) {
        if (in.readByte() == 0) {
            urut = null;
        } else {
            urut = in.readInt();
        }
        status = in.readByte() != 0;
        kamus = in.readParcelable(Kamus.class.getClassLoader());
        dataJawabKamusLain = in.createTypedArrayList(Kamus.CREATOR);
        jawaBenar = in.readInt();
        jawab = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (urut == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(urut);
        }
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeParcelable(kamus, flags);
        dest.writeTypedList(dataJawabKamusLain);
        dest.writeInt(jawaBenar);
        dest.writeInt(jawab);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Kuis> CREATOR = new Creator<Kuis>() {
        @Override
        public Kuis createFromParcel(Parcel in) {
            return new Kuis(in);
        }

        @Override
        public Kuis[] newArray(int size) {
            return new Kuis[size];
        }
    };

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

    public ArrayList<Kamus> getDataJawabKamusLain() {
        return dataJawabKamusLain;
    }

    public void setDataJawabKamusLain(ArrayList<Kamus> dataJawabKamusLain) {
        this.dataJawabKamusLain = dataJawabKamusLain;
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
