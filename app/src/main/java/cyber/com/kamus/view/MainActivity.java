package cyber.com.kamus.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import cyber.com.kamus.R;
import cyber.com.kamus.databinding.ActivityNavigationBinding;
import cyber.com.kamus.view.fragment.FragmentPencarian;
import cyber.com.kamus.view.util.Helper;

public class MainActivity extends AppCompatActivity {
    BottomNavigationViewEx bottomNavigationViewEx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNavigationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation);

        binding.bottomNavigation.enableAnimation(false);
        binding.bottomNavigation.enableShiftingMode(false);

        Helper.openFragment(this, new FragmentPencarian(), R.id.fragment);
    }
}
