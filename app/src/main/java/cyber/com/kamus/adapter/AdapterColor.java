package cyber.com.kamus.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cyber.com.kamus.R;
import cyber.com.kamus.databinding.ViewHolderColorBinding;

public class AdapterColor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Integer> color = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new VHColor((ViewHolderColorBinding) DataBindingUtil.inflate(inflater, R.layout.view_holder_color, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        VHColor vh = (VHColor) viewHolder;

        vh.binding.background.setBackgroundColor(vh.itemView.getResources().getColor(this.color.get(i)));

        if (i == 1) {
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

    private class VHColor extends RecyclerView.ViewHolder {
        private final ViewHolderColorBinding binding;

        public VHColor(ViewHolderColorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
