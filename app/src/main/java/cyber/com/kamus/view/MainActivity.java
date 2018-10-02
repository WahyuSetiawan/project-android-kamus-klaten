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
    private ActivityNavigationBinding binding;

    private String classFragment;

    public static final String FRAGMENT = "FRAGMENT";

    private FragmentSearch fragmentSearch;
    private FragmentCategory fragmentCategory;
    private FragmentKuis fragmentKuis;
    private FragmentSetting fragmentSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Helper.chooseTheme(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation);
        binding.setViewModel(new ViewModelMainActivity(this));

        binding.bottomNavigation.enableAnimation(false);
        binding.bottomNavigation.enableShiftingMode(false);

        if (savedInstanceState != null) {
            if (savedInstanceState.getString(FRAGMENT)
                    .equals(FragmentSearch.class.getSimpleName())) {
                if (fragmentSearch == null) {
                    fragmentSearch = new FragmentSearch();
                }
                Helper.openFragment(this, fragmentSearch, R.id.fragment);
                classFragment = FragmentSearch.class.getSimpleName();
            }

            if (savedInstanceState.getString(FRAGMENT)
                    .equals(FragmentCategory.class.getSimpleName())) {
                if (fragmentCategory == null) {
                    fragmentCategory = new FragmentCategory();
                }
                Helper.openFragment(this, fragmentCategory, R.id.fragment);
                classFragment = FragmentCategory.class.getSimpleName();
            }

            if (savedInstanceState.getString(FRAGMENT)
                    .equals(FragmentKuis.class.getSimpleName())) {
                if (fragmentKuis == null) {
                    fragmentKuis = new FragmentKuis();
                }
                Helper.openFragment(this, fragmentKuis, R.id.fragment);
                classFragment = FragmentKuis.class.getSimpleName();
            }

            if (savedInstanceState.getString(FRAGMENT)
                    .equals(FragmentSetting.class.getSimpleName())) {
                Helper.openFragment(this,  FragmentSetting.init(), R.id.fragment);
                classFragment = FragmentSetting.class.getSimpleName();
            }
        } else {
            fragmentSearch = new FragmentSearch();
            Helper.openFragment(this, fragmentSearch, R.id.fragment);
            classFragment = FragmentSearch.class.getSimpleName();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_kamus_jawa:
                if (fragmentSearch == null) {
                    fragmentSearch = new FragmentSearch();
                }
                Helper.openFragment(this, fragmentSearch, R.id.fragment);
                classFragment = FragmentSearch.class.getSimpleName();
                break;
            case R.id.menu_kategori:
                if (fragmentCategory == null) {
                    fragmentCategory = new FragmentCategory();
                }
                Helper.openFragment(this, fragmentCategory, R.id.fragment);
                classFragment = FragmentCategory.class.getSimpleName();
                break;
            case R.id.menu_kuis_seru:
                if (fragmentKuis == null) {
                    fragmentKuis = new FragmentKuis();
                }
                Helper.openFragment(this, fragmentKuis, R.id.fragment);
                classFragment = FragmentKuis.class.getSimpleName();
                break;
            case R.id.menu_pengaturan:
                Helper.openFragment(this, FragmentSetting.init(), R.id.fragment);
                classFragment = FragmentSetting.class.getSimpleName();
                break;

        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(FRAGMENT, classFragment);

        super.onSaveInstanceState(savedInstanceState);
    }
}
