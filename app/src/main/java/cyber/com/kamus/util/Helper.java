package cyber.com.kamus.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cyber.com.kamus.Preferences.PreferencesSetting;

public class Helper {
    public static final String TAG = Helper.class.getSimpleName();

    public static void openFragment(Context context, Fragment fragment, Integer id) {
        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();

        transaction.replace(id, fragment);
        transaction.commit();
    }

    public static void chooseTheme(Context context) {
        String theme = "";

        PreferencesSetting preferencesSetting = new PreferencesSetting(context);
        String color = Integer.toHexString(preferencesSetting.getColor()).substring(2).toUpperCase();

        if (preferencesSetting.getReadMode()) {
            theme = "AppTheme.noActionBar.readMode.theme_" + color;
        } else {
            theme = "AppTheme.noActionBar.theme_" + color;
        }

        context.setTheme(context.getResources().getIdentifier(theme, "style", context.getPackageName()));
    }

    public static void changeTheme(Activity activity) {
        chooseTheme(activity.getBaseContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            activity.recreate();
        else {
            Intent i = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(i);
        }
    }
}
