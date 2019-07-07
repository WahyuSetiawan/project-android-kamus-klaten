package cyber.com.kawruh.util.listener;

import cyber.com.kawruh.model.Kuis;
import cyber.com.kawruh.model.KuisAdapter;

public interface ConnectionFragmentKuis {
    void close();

    void repeat();

    void next();

    void nextKuis(Kuis kuis, KuisAdapter kuisAdapter);
}
