package cyber.com.kawruh.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import cyber.com.kawruh.R;

public class PreferencesSetting {
    private final String PREVERENCES_KAMUS = "PREFERENCES_KAMUS";
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor sharedPreferencesEditor;

    public static final String PREF_COLOR = "PREF_COLOR";
    public static final String PREF_READMODE = "PREF_READMODE";
    private final Context context;

    public PreferencesSetting(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(PREVERENCES_KAMUS, Context.MODE_PRIVATE);
        this.sharedPreferencesEditor = this.sharedPreferences.edit();
    }

    public Integer getColor() {
        return this.sharedPreferences.getInt(PREF_COLOR, context.getResources().getColor(R.color.kamus_theme_FF6F00));
    }

    public void setColor(Integer color) {
        this.sharedPreferencesEditor.putInt(PREF_COLOR, color);
        this.sharedPreferencesEditor.commit();
    }

    public boolean getReadMode() {
        return this.sharedPreferences.getBoolean(PREF_READMODE, false);
    }

    public void setReadMode(boolean readMode) {
        this.sharedPreferencesEditor.putBoolean(PREF_READMODE, readMode);
        this.sharedPreferencesEditor.commit();
    }

}
