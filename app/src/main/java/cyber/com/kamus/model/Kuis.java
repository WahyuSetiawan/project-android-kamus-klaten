package cyber.com.kamus.model;

public class Kuis {
    Integer urut;
    boolean status;

    public Kuis(boolean status) {
        this.status = status;
    }

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
}
