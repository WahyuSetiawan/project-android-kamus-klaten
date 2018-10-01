package cyber.com.kamus.view.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.view.MenuItem;

import cyber.com.kamus.R;
import cyber.com.kamus.databinding.ActivityNavigationBinding;
import cyber.com.kamus.view.MainActivity;

import android.view.View;

public class ViewModelMainActivity extends ViewModel {

    Action mainActivity;

    public ViewModelMainActivity(Action mainActivity) {
        this.mainActivity = mainActivity;
    }


    public boolean onNavigationItemSelected(MenuItem menuItem) {
       return this.mainActivity.onNavigationItemSelected(menuItem);
    }

    public interface Action{
        boolean onNavigationItemSelected(MenuItem menuItem);
    }
}
