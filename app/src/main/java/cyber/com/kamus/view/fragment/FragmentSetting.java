package cyber.com.kamus.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cyber.com.kamus.R;
import cyber.com.kamus.adapter.AdapterPengaturan;
import cyber.com.kamus.databinding.FragmentPengaturanBinding;
import cyber.com.kamus.model.Setting;

public class FragmentSetting extends Fragment {
    FragmentPengaturanBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pengaturan, container, false))
                .getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AdapterPengaturan adapter = new AdapterPengaturan();
        binding.recycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recycler.setAdapter(adapter);

        adapter.add(new Setting("Warna Tema", FragmentPengaturan.TypePengaturan.color));
        adapter.add(new Setting("Mode Malam", FragmentPengaturan.TypePengaturan.changemode));
        adapter.add(new Setting("Bantuan", FragmentPengaturan.TypePengaturan.direction));
        adapter.add(new Setting("Tentang", FragmentPengaturan.TypePengaturan.direction));
    }
}
