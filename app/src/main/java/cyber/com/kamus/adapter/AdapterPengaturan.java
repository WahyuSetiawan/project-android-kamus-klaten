package cyber.com.kamus.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cyber.com.kamus.R;
import cyber.com.kamus.databinding.FragmentPengaturanChangeModeBinding;
import cyber.com.kamus.databinding.FragmentPengaturanColorBinding;
import cyber.com.kamus.databinding.FragmentPengaturanDirectionBinding;
import cyber.com.kamus.model.Setting;
import cyber.com.kamus.util.decoration.GridItemDecoration;
import cyber.com.kamus.view.fragment.FragmentPengaturan;

public class AdapterPengaturan extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Setting> pengaturans = new ArrayList();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflate = LayoutInflater.from(viewGroup.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (FragmentPengaturan.TypePengaturan.values()[i]) {
            case direction:
                viewHolder = new VHDirection((FragmentPengaturanDirectionBinding) DataBindingUtil.inflate(inflate,
                        R.layout.fragment_pengaturan_direction, viewGroup, false));
                break;
            case changemode:
                viewHolder = new VHSwitch((FragmentPengaturanChangeModeBinding) DataBindingUtil.inflate(inflate,
                        R.layout.fragment_pengaturan_change_mode, viewGroup, false));
                break;
            case color:
                viewHolder = new VHChangeColor((FragmentPengaturanColorBinding) DataBindingUtil.inflate(inflate
                        , R.layout.fragment_pengaturan_color, viewGroup, false));
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (this.pengaturans.get(i).getTypePengaturan()) {
            case color:
                VHChangeColor vh = (VHChangeColor) viewHolder;
                vh.binding.title.setText(this.pengaturans.get(i).getName());

                vh.binding.recycler.setLayoutManager(new GridLayoutManager(vh.itemView.getContext(), 5));
                AdapterColor adapterColor = new AdapterColor();
                vh.binding.recycler.setAdapter(adapterColor);
                vh.binding.recycler.addItemDecoration(new GridItemDecoration(15, 15, 5, false));

                adapterColor.add(R.color.kamus_orange);
                adapterColor.add(R.color.kamus_abu_abu);
                adapterColor.add(R.color.kamus_dark);
                adapterColor.add(R.color.kamus_hitam);
                adapterColor.add(R.color.kamus_orange);
                adapterColor.add(R.color.kamus_orange);
                adapterColor.add(R.color.kamus_orange);
                adapterColor.add(R.color.kamus_orange);
                adapterColor.add(R.color.kamus_orange);
                adapterColor.add(R.color.kamus_orange);

                break;
            case changemode:
                VHSwitch vh2 = (VHSwitch) viewHolder;
                vh2.binding.title.setText(this.pengaturans.get(i).getName());
                break;
            case direction:
                VHDirection vh3 = (VHDirection) viewHolder;
                vh3.binding.title.setText(this.pengaturans.get(i).getName());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return this.pengaturans.get(position).getTypePengaturan().ordinal();
    }

    @Override
    public int getItemCount() {
        return this.pengaturans.size();
    }

    public void add(Setting setting) {
        this.pengaturans.add(setting);
        this.notifyItemInserted(this.pengaturans.size() - 1);
    }

    private class VHDirection extends RecyclerView.ViewHolder {
        private final FragmentPengaturanDirectionBinding binding;

        public VHDirection(FragmentPengaturanDirectionBinding view) {
            super(view.getRoot());
            this.binding = view;
        }
    }

    private class VHChangeColor extends RecyclerView.ViewHolder {
        FragmentPengaturanColorBinding binding;

        public VHChangeColor(FragmentPengaturanColorBinding view) {
            super(view.getRoot());
            this.binding = view;
        }


    }

    private class VHSwitch extends RecyclerView.ViewHolder {
        FragmentPengaturanChangeModeBinding binding;

        public VHSwitch(FragmentPengaturanChangeModeBinding view) {
            super(view.getRoot());
            this.binding = view;
        }
    }

}
