package cyber.com.kamus.util.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Object> objects = new ArrayList<>();
}
