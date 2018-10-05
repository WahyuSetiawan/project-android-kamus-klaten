package cyber.com.kamus.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import cyber.com.kamus.R;
import cyber.com.kamus.database.Database;
import cyber.com.kamus.model.Kamus;
import cyber.com.kamus.model.Kuis;
import cyber.com.kamus.util.Helper;
import cyber.com.kamus.util.listener.ConnectionFragmentKuis;
import cyber.com.kamus.view.fragment.FragmentDaftarKuis;
import cyber.com.kamus.view.fragment.FragmentFinishKuis;
import cyber.com.kamus.view.fragment.FragmentKuis;

public class KuisActivity extends AppCompatActivity implements ConnectionFragmentKuis {
    private Database database;
    private ArrayList<Kamus> kamuses = new ArrayList<>();
    private ArrayList<Kuis> kuiss = new ArrayList<>();
    private Integer position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Helper.chooseTheme(this);
        setContentView(R.layout.activity_kuis);
        database = new Database(this);
        kamuses = database.getTableKamus().getKuis();

        for (Kamus kamus : kamuses) {
            Kuis kuis = new Kuis();

            kuis.setKamus(kamus);

            for (int i = 0; i < 3; i++) {
                kuis.getKamuses().add(
                        kamuses.get(new Random().nextInt(kamuses.size()))
                );
            }

            kuiss.add(kuis);
        }

        Helper.openFragment(this, FragmentKuis.init(kuiss.get(0)), R.id.fragment_layout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }

    @Override
    public void nextKuis() {
        position++;
        if (position <= kamuses.size())
            Helper.openFragment(this, FragmentKuis.init(this.kuiss.get(position)), R.id.fragment_layout);
        else
            close();
    }

    @Override
    public void close() {
        Helper.openFragment(this, FragmentFinishKuis.init(), R.id.fragment_layout);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void repeat() {

    }
}
