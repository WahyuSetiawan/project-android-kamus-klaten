package cyber.com.kamus.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cyber.com.kamus.Preferences.PreferencesSetting;
import cyber.com.kamus.R;
import cyber.com.kamus.databinding.ViewHolderColorBinding;
import cyber.com.kamus.util.listener.ListenerView;

public class AdapterColor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Integer> color = new ArrayList<>();

    private ListenerView.onClickViewHolder listenerViewHolder;

    public ListenerView.onClickViewHolder getListenerViewHolder() {
        return listenerViewHolder;
    }

    public void setListenerViewHolder(ListenerView.onClickViewHolder listenerViewHolder) {
        this.listenerViewHolder = listenerViewHolder;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new VHColor((ViewHolderColorBinding) DataBindingUtil.inflate(inflater, R.layout.view_holder_color, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        VHColor vh = (VHColor) viewHolder;

        vh.binding.background.setBackgroundColor(
                vh.itemView.getResources().getColor(this.color.get(i))
        );
        vh.setListenerViewHolder(this.listenerViewHolder);

        Integer color = new PreferencesSetting(vh.itemView.getContext()).getColor();
        if (vh.itemView.getResources().getColor(this.color.get(i)) == color) {
            vh.binding.check.setVisibility(View.VISIBLE);
        } else {
            vh.binding.check.setVisibility(View.GONE);
        }
    }

    public void add(Integer color) {
        this.color.add(color);
        notifyItemInserted(this.color.size() - 1);
    }

    @Override
    public int getItemCount() {
        return color.size();
    }

    public Integer getItem(Integer position) {
        return this.color.get(position);
    }


    private class VHColor extends RecyclerView.ViewHolder {
        private final ViewHolderColorBinding binding;
        ListenerView.onClickViewHolder listenerViewHolder;

        public VHColor(ViewHolderColorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerViewHolder.onClickListener(v, getAdapterPosition());
                }
            });
        }

        public void setListenerViewHolder(ListenerView.onClickViewHolder listenerViewHolder) {
            this.listenerViewHolder = listenerViewHolder;
        }
    }
}
