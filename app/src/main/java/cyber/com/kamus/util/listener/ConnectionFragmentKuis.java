package cyber.com.kamus.util.listener;

import cyber.com.kamus.model.Kuis;

public interface ConnectionFragmentKuis {
    void nextKuis(Kuis kuis);
    void close();
    void repeat();
void next();
}
