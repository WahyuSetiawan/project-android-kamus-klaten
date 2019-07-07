package cyber.com.kawruh.model;

import android.os.Parcel;
import android.os.Parcelable;

public class KuisAdapter implements Parcelable {
    private String label;
    private Boolean status;
    private int score;
    private long time;

    public KuisAdapter(String label, Boolean status, int score, long time) {
        this.label = label;
        this.status = status;
        this.score = score;
        this.time = time;
    }

    public KuisAdapter(String label, Boolean status) {
        this.label = label;
        this.status = status;
    }

    public KuisAdapter(String label) {

    }

    protected KuisAdapter(Parcel in) {
        label = in.readString();
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
        score = in.readInt();
        time = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(label);
        dest.writeByte((byte) (status == null ? 0 : status ? 1 : 2));
        dest.writeInt(score);
        dest.writeLong(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<KuisAdapter> CREATOR = new Creator<KuisAdapter>() {
        @Override
        public KuisAdapter createFromParcel(Parcel in) {
            return new KuisAdapter(in);
        }

        @Override
        public KuisAdapter[] newArray(int size) {
            return new KuisAdapter[size];
        }
    };

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
