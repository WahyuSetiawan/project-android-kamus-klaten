package cyber.com.kamus.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import cyber.com.kamus.R;
import cyber.com.kamus.database.database.Database;
import cyber.com.kamus.model.Kamus;
import cyber.com.kamus.model.Kuis;
import cyber.com.kamus.model.KuisAdapter;
import cyber.com.kamus.util.Helper;
import cyber.com.kamus.util.listener.ConnectionFragmentKuis;
import cyber.com.kamus.view.fragment.FragmentFinishKuis;
import cyber.com.kamus.view.fragment.FragmentKuis;

public class KuisActivity extends AppCompatActivity implements ConnectionFragmentKuis {
    public static final String KUISDATA = "KUISDATA";
    public static final String ID = "ID";
    private Database database;

    private ArrayList<Kamus> tempDataKamus = new ArrayList<>();
    private ArrayList<Kuis> dataPermainanKuis = new ArrayList<>();
    private ArrayList<Kuis> dataPermainanKuisHasil = new ArrayList<>();

    private int jumlahSoal = 3;

    private KuisAdapter kuisAdapter;
    private int idData;
    private KuisAdapter kuisAdapterStart;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Helper.chooseTheme(this);
        setContentView(R.layout.activity_kuis);

        Intent intent = getIntent();

        if (intent != null) {
            kuisAdapter = intent.getParcelableExtra(KUISDATA);
            kuisAdapter.setTime(0);
            kuisAdapterStart = kuisAdapter;
            idData = intent.getIntExtra(ID, -1);
        }

        database = new Database(this);
        tempDataKamus = database.getTableKamus().getKuis();

        for (Kamus kamus : tempDataKamus) {
            Kuis kuis = new Kuis();

            kuis.setKamus(kamus);
            kuis.setJawaBenar(new Random().nextInt(jumlahSoal - 1));

            ArrayList<Integer> tempIndex = new ArrayList<>();

            for (int i = 0; i < jumlahSoal; i++) {
                int index = new Random().nextInt(tempDataKamus.size());

                while (true) {
                    if (!tempIndex.contains(index)
                            && index != tempDataKamus.indexOf(kamus)
                            && tempIndex.size() < tempDataKamus.size() - 1) {
                        break;
                    } else {
                        index = new Random().nextInt(tempDataKamus.size());
                    }
                }
                tempIndex.add(index);
                kuis.getDataJawabKamusLain().add(tempDataKamus.get(index));
            }

            dataPermainanKuis.add(kuis);
        }

        Helper.openFragment(this, FragmentKuis.init(dataPermainanKuis.get(0), kuisAdapter),
                R.id.fragment_layout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }

    @Override
    public void nextKuis(Kuis kuis, KuisAdapter kuisAdapter) {
        this.dataPermainanKuisHasil.add(kuis);

        int score = 0;

        for (Kuis kuisItem : dataPermainanKuisHasil) {
            score += (kuisItem.getJawaBenar() == kuisItem.getJawab()) ? 10 : 0;
        }

        kuisAdapter.setScore(score);
        this.kuisAdapter = kuisAdapter;

        if (dataPermainanKuisHasil.size() < tempDataKamus.size())
            Helper.openFragment(this,
                    FragmentKuis.init(dataPermainanKuis.get(dataPermainanKuisHasil.size()), kuisAdapter),
                    R.id.fragment_layout);
        else
            finishKuis();
    }

    private void finishKuis() {
        int score = 0;

        for (Kuis kuisItem : dataPermainanKuisHasil) {
            score += (kuisItem.getJawaBenar() == kuisItem.getJawab()) ? 10 : 0;
        }

        kuisAdapter.setScore(score);
        kuisAdapter.setStatus(true);

        database.getTableKuis().update(this.kuisAdapter);

        Helper.openFragment(this, FragmentFinishKuis.init(kuisAdapter), R.id.fragment_layout);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void finish() {
        database.close();

        Intent intent = new Intent();

        intent.putExtra(KUISDATA, kuisAdapter);
        intent.putExtra(ID, idData);

        setResult(Activity.RESULT_OK, intent);

        super.finish();
    }

    @Override
    public void repeat() {
        this.dataPermainanKuisHasil = new ArrayList<>();
        this.kuisAdapter = kuisAdapterStart;

        Helper.openFragment(this, FragmentKuis.init(dataPermainanKuis.get(0), kuisAdapter),
                R.id.fragment_layout);
    }

    @Override
    public void next() {

    }
}
