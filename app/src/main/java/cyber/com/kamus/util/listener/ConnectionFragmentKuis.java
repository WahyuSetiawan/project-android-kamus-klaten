package cyber.com.kamus.util.listener;

import cyber.com.kamus.model.Kuis;
import cyber.com.kamus.model.KuisAdapter;

public interface ConnectionFragmentKuis {
    void close();

    void repeat();

    void next();

    void nextKuis(Kuis kuis, KuisAdapter kuisAdapter);
}
