package cyber.com.kamus.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesSetting {
    private final String PREVERENCES_KAMUS = "PREFERENCES_KAMUS";
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor sharedPreferencesEditor;

    public static final String PREF_COLOR = "PREF_COLOR";
    public static final String PREF_READMODE = "PREF_READMODE";

    public PreferencesSetting(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREVERENCES_KAMUS, Context.MODE_PRIVATE);
        this.sharedPreferencesEditor = this.sharedPreferences.edit();
    }

    public Integer getColor() {
        return this.sharedPreferences.getInt(PREF_COLOR, 0);
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
