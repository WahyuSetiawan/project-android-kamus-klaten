package cyber.com.kawruh.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cyber.com.kawruh.model.KuisAdapter;
import cyber.com.kawruh.preferences.AttrHelper;
import cyber.com.kawruh.R;
import cyber.com.kawruh.databinding.ViewHolderKuisBinding;
import cyber.com.kawruh.util.listener.ListenerView;

public class AdapterKuis extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<KuisAdapter> kuis = new ArrayList<>();
    ListenerView.onClickViewHolder onClickViewHolder;

    public ListenerView.onClickViewHolder getOnClickViewHolder() {
        return onClickViewHolder;
    }

    public void setOnClickViewHolder(ListenerView.onClickViewHolder onClickViewHolder) {
        this.onClickViewHolder = onClickViewHolder;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        return new VHKuis((ViewHolderKuisBinding) DataBindingUtil.inflate(inflater, R.layout.view_holder_kuis, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        VHKuis vh = (VHKuis) viewHolder;

        if (this.kuis.get(i).getStatus()) {
            vh.binding.background.setBackgroundColor(new AttrHelper(vh.itemView.getContext()).getColorAccent());
            Glide.with(viewHolder.itemView).load(R.drawable.ic_lock_open_black_24dp).into(vh.binding.icon);
        } else {
            vh.binding.background.setBackgroundColor(viewHolder.itemView.getResources().getColor(R.color.kamus_dark));
            Glide.with(viewHolder.itemView).load(R.drawable.ic_lock_outline_black_24dp).into(vh.binding.icon);
        }

        vh.onClickViewHolder = this.onClickViewHolder;
        vh.binding.title.setText("Kuis " + (i + 1));
    }

    @Override
    public int getItemCount() {
        return this.kuis.size();
    }

    public void add(KuisAdapter kuis) {
        this.kuis.add(kuis);
        this.notifyItemInserted(this.kuis.size() - 1);
    }

    public KuisAdapter getItem(int position) {
        return this.kuis.get(position);
    }

    public void setItem(int position, KuisAdapter kuisAdapter){
        this.kuis.set(position, kuisAdapter);
        notifyItemChanged(position);
    }

    private class VHKuis extends RecyclerView.ViewHolder {
        private final ViewHolderKuisBinding binding;
        ListenerView.onClickViewHolder onClickViewHolder;

        public VHKuis(ViewHolderKuisBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickViewHolder.onClickListener(v, getAdapterPosition());
                }
            });
        }
    }
}
