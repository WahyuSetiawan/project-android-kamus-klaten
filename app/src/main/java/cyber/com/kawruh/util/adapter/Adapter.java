package cyber.com.kawruh.util.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public abstract class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Object> objects = new ArrayList<>();
}
