package cyber.com.kawruh.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cyber.com.kawruh.R;
import cyber.com.kawruh.databinding.ActivityBantuanBinding;
import cyber.com.kawruh.util.Helper;

public class BantuanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Helper.chooseTheme(this);

        ActivityBantuanBinding activityBantuanBinding = DataBindingUtil.setContentView(this, R.layout.activity_bantuan);

        setSupportActionBar(activityBantuanBinding.toolbar);

        setTitle("Bantuan");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
