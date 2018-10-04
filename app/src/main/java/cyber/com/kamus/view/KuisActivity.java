package cyber.com.kamus.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cyber.com.kamus.R;
import cyber.com.kamus.util.Helper;
import cyber.com.kamus.util.listener.ConnectionFragmentKuis;
import cyber.com.kamus.view.fragment.FragmentDaftarKuis;
import cyber.com.kamus.view.fragment.FragmentKuis;

public class KuisActivity extends AppCompatActivity implements ConnectionFragmentKuis {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Helper.chooseTheme(this);
        setContentView(R.layout.activity_kuis);

        Helper.openFragment(this, FragmentKuis.init(), R.id.fragment_layout);
    }

    @Override
    public void nextKuis() {

    }

    @Override
    public void finish() {
        super.finish();
    }
}
