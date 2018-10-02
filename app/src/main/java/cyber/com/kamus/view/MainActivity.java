package cyber.com.kamus.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import cyber.com.kamus.R;
import cyber.com.kamus.databinding.ActivityNavigationBinding;
import cyber.com.kamus.view.fragment.FragmentCategory;
import cyber.com.kamus.view.fragment.FragmentKuis;
import cyber.com.kamus.view.fragment.FragmentSearch;
import cyber.com.kamus.view.fragment.FragmentSetting;
import cyber.com.kamus.util.Helper;
import cyber.com.kamus.view.viewmodel.ViewModelMainActivity;

public class MainActivity extends AppCompatActivity implements ViewModelMainActivity.Action {
    public ActivityNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_noActionBar_readMode);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation);
        binding.setViewModel(new ViewModelMainActivity(this));

        binding.bottomNavigation.enableAnimation(false);
        binding.bottomNavigation.enableShiftingMode(false);

        Helper.openFragment(this, new FragmentSearch(), R.id.fragment);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_kamus_jawa:
                Helper.openFragment(this, new FragmentSearch(), R.id.fragment);
                break;
            case R.id.menu_kategori:
                Helper.openFragment(this, new FragmentCategory(), R.id.fragment);
                break;
            case R.id.menu_kuis_seru:
                Helper.openFragment(this, new FragmentKuis(), R.id.fragment);
                break;
            case R.id.menu_pengaturan:
                Helper.openFragment(this, new FragmentSetting(), R.id.fragment);
                break;

        }
        return true;
    }
}
