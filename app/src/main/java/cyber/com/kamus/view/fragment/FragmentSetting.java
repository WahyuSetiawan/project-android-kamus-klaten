package cyber.com.kamus.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import cyber.com.kamus.Preferences.PreferencesSetting;
import cyber.com.kamus.R;
import cyber.com.kamus.adapter.AdapterColor;
import cyber.com.kamus.adapter.AdapterPengaturan;
import cyber.com.kamus.databinding.FragmentPengaturanBinding;
import cyber.com.kamus.model.Setting;
import cyber.com.kamus.util.listener.ListenerView;
import cyber.com.kamus.util.listener.ListenerViewHolder;
import cyber.com.kamus.view.BantuanActivity;
import cyber.com.kamus.view.TentangActivity;

public class FragmentSetting extends Fragment {
    private FragmentPengaturanBinding binding;
    private PreferencesSetting preferencesKamusSetting;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferencesKamusSetting = new PreferencesSetting(this.getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pengaturan, container, false))
                .getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final AdapterPengaturan adapter = new AdapterPengaturan();
        binding.recycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recycler.setAdapter(adapter);

        final AdapterColor adapterColor = new AdapterColor();

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

        final Setting warnaThema = new Setting("Warna Tema", FragmentPengaturan.TypePengaturan.color);
        warnaThema.setAdapter(adapterColor);
        adapter.add(warnaThema);
        adapterColor.setListenerViewHolder(new ListenerView.onClickViewHolder() {
            @Override
            public void onClickListener(View view, Integer position) {
                preferencesKamusSetting.setColor(getContext().getResources().getColor(adapterColor.getItem(position)));
                adapterColor.notifyDataSetChanged();
            }
        });

        Setting changeReadMode = new Setting("Mode Malam", FragmentPengaturan.TypePengaturan.changemode);
        adapter.add(changeReadMode);
        changeReadMode.setOnCheckedViewHolder(new ListenerView.onCheckedViewHolder() {
            @Override
            public void onChangeViewHolder(CompoundButton compoundButton, boolean isChecked, Integer position) {
                preferencesKamusSetting.setReadMode(isChecked);
                Log.d(FragmentSetting.class.getSimpleName(), "" + isChecked);
                Log.d(FragmentSetting.class.getSimpleName(), "onClickListener: " + preferencesKamusSetting.getReadMode());
            }
        });

        Setting bantuan = new Setting("Bantuan", FragmentPengaturan.TypePengaturan.direction);
        adapter.add(bantuan);
        bantuan.setOnClickViewHolder(new ListenerView.onClickViewHolder() {
            @Override
            public void onClickListener(View view, Integer position) {
                Intent intent = new Intent(getContext(), BantuanActivity.class);
                startActivity(intent);
            }
        });

        Setting tentang = new Setting("Tentang", FragmentPengaturan.TypePengaturan.direction);
        adapter.add(tentang);
        tentang.setOnClickViewHolder(new ListenerView.onClickViewHolder() {
            @Override
            public void onClickListener(View view, Integer position) {
                Intent intent = new Intent(FragmentSetting.this.getContext(), TentangActivity.class);
                startActivity(intent);
            }
        });

    }


}
