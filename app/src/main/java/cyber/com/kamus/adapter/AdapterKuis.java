package cyber.com.kamus.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cyber.com.kamus.preferences.AttrHelper;
import cyber.com.kamus.R;
import cyber.com.kamus.databinding.ViewHolderKuisBinding;
import cyber.com.kamus.model.Kuis;

public class AdapterKuis extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Kuis> kuis = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        return new VHKuis((ViewHolderKuisBinding) DataBindingUtil.inflate(inflater, R.layout.view_holder_kuis, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        VHKuis vh = (VHKuis) viewHolder;

        if (this.kuis.get(i).isStatus()) {
            vh.binding.background.setBackgroundColor(new AttrHelper(vh.itemView.getContext()).getColorAccent());
            Glide.with(viewHolder.itemView).load(R.drawable.ic_lock_open_black_24dp).into(vh.binding.icon);
        } else {
            vh.binding.background.setBackgroundColor(viewHolder.itemView.getResources().getColor(R.color.kamus_dark));
            Glide.with(viewHolder.itemView).load(R.drawable.ic_lock_outline_black_24dp).into(vh.binding.icon);
        }

        vh.binding.title.setText("Kuis " + (i + 1));
    }

    @Override
    public int getItemCount() {
        return this.kuis.size();
    }

    public void add(Kuis kuis) {
        this.kuis.add(kuis);
        this.notifyItemInserted(this.kuis.size() - 1);
    }

    private class VHKuis extends RecyclerView.ViewHolder {
        private final ViewHolderKuisBinding binding;

        public VHKuis(ViewHolderKuisBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
