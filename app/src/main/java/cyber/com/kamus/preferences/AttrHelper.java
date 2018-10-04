package cyber.com.kamus.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import cyber.com.kamus.R;

public class AttrHelper {
    private final Context context;


    public AttrHelper(Context context) {
        this.context = context;
    }

    @SuppressLint("ResourceType")
    public int getColorBackground() {
        @SuppressLint("Recycle") TypedArray attrs = context.obtainStyledAttributes(new int[]{R.attr.colorHomeBackground});

        return attrs.getColor(0, Color.WHITE);
    }

    @SuppressLint("ResourceType")
    public int getColorAccent() {
        @SuppressLint("Recycle") TypedArray attrs = context.obtainStyledAttributes(new int[]{R.attr.colorAccentMode});

        return attrs.getColor(0, Color.WHITE);
    }

}
