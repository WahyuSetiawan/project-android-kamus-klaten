package cyber.com.kamus.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cyber.com.kamus.R;
import cyber.com.kamus.databinding.ActivityNavigationBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNavigationBinding binding =  DataBindingUtil.setContentView(this, R.layout.activity_navigation);

binding.bottomNavigation.enableShiftingMode(false);
    }
}
