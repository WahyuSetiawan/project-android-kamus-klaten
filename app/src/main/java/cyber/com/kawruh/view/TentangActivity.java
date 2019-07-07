package cyber.com.kawruh.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cyber.com.kawruh.R;
import cyber.com.kawruh.databinding.ActivityTentangBinding;
import cyber.com.kawruh.util.Helper;

public class TentangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Helper.chooseTheme(this);
        ActivityTentangBinding activityTentangBinding = DataBindingUtil.setContentView(this, R.layout.activity_tentang);

        setSupportActionBar(activityTentangBinding.toolbar);

        setTitle("Tentang");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
