package cyber.com.kawruh.view.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.view.MenuItem;

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
