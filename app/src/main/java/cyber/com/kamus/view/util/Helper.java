package cyber.com.kamus.view.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class Helper {
    public static void openFragment(Context context, Fragment fragment, Integer id) {
        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();

        transaction.replace(id, fragment);

        transaction.commit();
    }
}
