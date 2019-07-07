package cyber.com.kawruh.model;

import android.support.v7.widget.RecyclerView;

import cyber.com.kawruh.util.listener.ListenerView;
import cyber.com.kawruh.view.fragment.FragmentPengaturan;

public class Setting {
    String name;
    FragmentPengaturan.TypePengaturan typePengaturan;

    private RecyclerView.Adapter<RecyclerView.ViewHolder> adapter;

    private ListenerView.onCheckedViewHolder onCheckedViewHolder;
    private ListenerView.onClickViewHolder onClickViewHolder;
    private ListenerView.onLongClickViewHolder onLongClickViewHolder;

    public Setting(String name, FragmentPengaturan.TypePengaturan typePengaturan) {
        this.name = name;
        this.typePengaturan = typePengaturan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FragmentPengaturan.TypePengaturan getTypePengaturan() {
        return typePengaturan;
    }


    public ListenerView.onCheckedViewHolder getOnCheckedViewHolder() {
        return onCheckedViewHolder;
    }

    public void setOnCheckedViewHolder(ListenerView.onCheckedViewHolder onCheckedViewHolder) {
        this.onCheckedViewHolder = onCheckedViewHolder;
    }

    public ListenerView.onClickViewHolder getOnClickViewHolder() {
        return onClickViewHolder;
    }

    public void setOnClickViewHolder(ListenerView.onClickViewHolder onClickViewHolder) {
        this.onClickViewHolder = onClickViewHolder;
    }

    public ListenerView.onLongClickViewHolder getOnLongClickViewHolder() {
        return onLongClickViewHolder;
    }

    public void setAdapter(RecyclerView.Adapter adapterColor) {
        this.adapter = adapterColor;
    }

    public RecyclerView.Adapter<RecyclerView.ViewHolder> getAdapterColor(){
        return this.adapter;
    }
}
