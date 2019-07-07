package cyber.com.kawruh.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import cyber.com.kawruh.R;

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

    public int getColorButtonTrue() {
        @SuppressLint("Recycle") TypedArray attrs = context.obtainStyledAttributes(new int[]{R.attr.colorKuisButtonAnswerTrue});

        return attrs.getColor(0, Color.WHITE);
    }

    public int getColorButtonDefault() {
        @SuppressLint("Recycle") TypedArray attrs = context.obtainStyledAttributes(new int[]{R.attr.colorKuisButtonAnswer});

        return attrs.getColor(0, Color.WHITE);
    }

    public int getColorButtonFalse() {
        @SuppressLint("Recycle") TypedArray attrs = context.obtainStyledAttributes(new int[]{R.attr.colorKuisButtonAnswerFalse});

        return attrs.getColor(0, Color.WHITE);
    }

    public int getColorButtonTextTrue() {
        @SuppressLint("Recycle") TypedArray attrs = context.obtainStyledAttributes(new int[]{R.attr.colorKuisButtonTextAnswerTrue});

        return attrs.getColor(0, Color.WHITE);
    }


    public int getColorButtonTextFalse() {
        @SuppressLint("Recycle") TypedArray attrs = context.obtainStyledAttributes(new int[]{R.attr.colorKuisButtonTextAnswerFalse});

        return attrs.getColor(0, Color.WHITE);
    }

    public int getColorButtonTextDefault() {
        @SuppressLint("Recycle") TypedArray attrs = context.obtainStyledAttributes(new int[]{R.attr.colorKuisButtonTextAnswer});

        return attrs.getColor(0, Color.WHITE);
    }

}
