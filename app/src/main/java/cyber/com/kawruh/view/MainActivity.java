package cyber.com.kawruh.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import cyber.com.kawruh.R;
import cyber.com.kawruh.databinding.ActivityNavigationBinding;
import cyber.com.kawruh.view.fragment.FragmentCategory;
import cyber.com.kawruh.view.fragment.FragmentDaftarKuis;
import cyber.com.kawruh.view.fragment.FragmentSearch;
import cyber.com.kawruh.view.fragment.FragmentSetting;
import cyber.com.kawruh.util.Helper;
import cyber.com.kawruh.view.viewmodel.ViewModelMainActivity;

public class MainActivity extends AppCompatActivity implements ViewModelMainActivity.Action {
    private ActivityNavigationBinding binding;
    private String classFragment;
    private static final String FRAGMENT = "FRAGMENT";
    private FragmentDaftarKuis fragmentDaftarKuis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Helper.chooseTheme(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation);
        binding.setViewModel(new ViewModelMainActivity(this));

        binding.bottomNavigation.enableAnimation(false);
        binding.bottomNavigation.enableShiftingMode(false);

        if (savedInstanceState != null) {
            if (savedInstanceState.getString(FRAGMENT)
                    .equals(FragmentSearch.class.getSimpleName())) {
                Helper.openFragment(this, FragmentSearch.init(), R.id.fragment);
                classFragment = FragmentSearch.class.getSimpleName();
            }

            if (savedInstanceState.getString(FRAGMENT)
                    .equals(FragmentCategory.class.getSimpleName())) {
                Helper.openFragment(this, FragmentCategory.init(), R.id.fragment);
                classFragment = FragmentCategory.class.getSimpleName();
            }

            if (savedInstanceState.getString(FRAGMENT)
                    .equals(FragmentDaftarKuis.class.getSimpleName())) {
                Helper.openFragment(this, FragmentDaftarKuis.init(), R.id.fragment);
                classFragment = FragmentDaftarKuis.class.getSimpleName();
            }

            if (savedInstanceState.getString(FRAGMENT)
                    .equals(FragmentSetting.class.getSimpleName())) {
                Helper.openFragment(this, FragmentSetting.init(), R.id.fragment);
                classFragment = FragmentSetting.class.getSimpleName();
            }
        } else {
            Helper.openFragment(this, FragmentSearch.init(), R.id.fragment);
            classFragment = FragmentSearch.class.getSimpleName();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_kamus_jawa:
                Helper.openFragment(this, FragmentSearch.init(), R.id.fragment);
                classFragment = FragmentSearch.class.getSimpleName();
                break;
            case R.id.menu_kategori:
                Helper.openFragment(this, FragmentCategory.init(), R.id.fragment);
                classFragment = FragmentCategory.class.getSimpleName();
                break;
            case R.id.menu_kuis_seru:
                Helper.openFragment(this, (fragmentDaftarKuis = FragmentDaftarKuis.init()), R.id.fragment);
                classFragment = FragmentDaftarKuis.class.getSimpleName();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        fragmentDaftarKuis.onActivityResult(requestCode, resultCode, data);
    }
}
