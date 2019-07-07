package cyber.com.kawruh.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;

import cyber.com.kawruh.preferences.PreferencesSetting;
import cyber.com.kawruh.R;
import cyber.com.kawruh.databinding.FragmentPengaturanChangeModeBinding;
import cyber.com.kawruh.databinding.FragmentPengaturanColorBinding;
import cyber.com.kawruh.databinding.FragmentPengaturanDirectionBinding;
import cyber.com.kawruh.model.Setting;
import cyber.com.kawruh.util.decoration.GridItemDecoration;
import cyber.com.kawruh.util.listener.ListenerView;
import cyber.com.kawruh.view.fragment.FragmentPengaturan;

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

                vh.binding.recycler.setLayoutManager(
                        new GridLayoutManager(vh.itemView.getContext(), 5)
                );
                vh.binding.recycler.setAdapter(pengaturans.get(i).getAdapterColor());
                vh.binding.recycler.addItemDecoration(
                        new GridItemDecoration(15, 15, 5, false)
                );

                break;
            case changemode:
                VHSwitch vh2 = (VHSwitch) viewHolder;
                vh2.binding.title.setText(this.pengaturans.get(i).getName());

                vh2.binding.changeMode.setChecked(
                        new PreferencesSetting(vh2.itemView.getContext()).getReadMode()
                );

                vh2.onClickViewHolder = pengaturans.get(i).getOnCheckedViewHolder();


                break;
            case direction:
                VHDirection vh3 = (VHDirection) viewHolder;
                vh3.binding.title.setText(this.pengaturans.get(i).getName());
                vh3.listenerViewHolder = pengaturans.get(i).getOnClickViewHolder();
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
        public ListenerView.onClickViewHolder listenerViewHolder;

        public VHDirection(FragmentPengaturanDirectionBinding view) {
            super(view.getRoot());
            this.binding = view;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerViewHolder.onClickListener(v, getAdapterPosition());
                }
            });
        }
    }

    private class VHChangeColor extends RecyclerView.ViewHolder {
        FragmentPengaturanColorBinding binding;
        ListenerView.onClickViewHolder listenerViewHolder;

        public VHChangeColor(FragmentPengaturanColorBinding view) {
            super(view.getRoot());
            this.binding = view;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerViewHolder.onClickListener(v, getAdapterPosition());
                }
            });

        }
    }

    private class VHSwitch extends RecyclerView.ViewHolder {
        public FragmentPengaturanChangeModeBinding binding;
        public ListenerView.onCheckedViewHolder onClickViewHolder;

        public VHSwitch(FragmentPengaturanChangeModeBinding view) {
            super(view.getRoot());
            this.binding = view;

            binding.changeMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (onClickViewHolder != null)
                        onClickViewHolder.onChangeViewHolder(buttonView, isChecked, getAdapterPosition());
                }
            });
        }
    }

}
