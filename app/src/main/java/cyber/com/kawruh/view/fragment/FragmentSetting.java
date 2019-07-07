package cyber.com.kamus.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import cyber.com.kamus.preferences.PreferencesSetting;
import cyber.com.kamus.R;
import cyber.com.kamus.adapter.AdapterColor;
import cyber.com.kamus.adapter.AdapterPengaturan;
import cyber.com.kamus.databinding.FragmentPengaturanBinding;
import cyber.com.kamus.model.Setting;
import cyber.com.kamus.util.Helper;
import cyber.com.kamus.util.listener.ListenerView;
import cyber.com.kamus.view.BantuanActivity;
import cyber.com.kamus.view.TentangActivity;

public class FragmentSetting extends Fragment {
    public static final String TAG = FragmentSetting.class.getSimpleName();

    private FragmentPengaturanBinding binding;
    private PreferencesSetting preferencesKamusSetting;

    public static FragmentSetting fragmentSetting;


    public static FragmentSetting init() {
        if (fragmentSetting == null) {
            fragmentSetting = new FragmentSetting();
        }
        return fragmentSetting;
    }

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

        if (preferencesKamusSetting.getReadMode()) {
            adapterColor.add(R.color.kamus_theme_8697A4);
            adapterColor.add(R.color.kamus_theme_8697A4);
            adapterColor.add(R.color.kamus_theme_8697A4);
            adapterColor.add(R.color.kamus_theme_8697A4);
            adapterColor.add(R.color.kamus_theme_8697A4);
            adapterColor.add(R.color.kamus_theme_8697A4);
            adapterColor.add(R.color.kamus_theme_8697A4);
            adapterColor.add(R.color.kamus_theme_8697A4);
            adapterColor.add(R.color.kamus_theme_8697A4);
            adapterColor.add(R.color.kamus_theme_8697A4);
            adapterColor.setListenerViewHolder(new ListenerView.onClickViewHolder() {
                @Override
                public void onClickListener(View view, Integer position) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Warna di non aktifkan disaat mode malam");
                    builder.setTitle("Pemberitahuan");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }
            });
        } else {
            adapterColor.add(R.color.kamus_theme_FF6F00);
            adapterColor.add(R.color.kamus_theme_D32F2F);
            adapterColor.add(R.color.kamus_theme_1976D2);
            adapterColor.add(R.color.kamus_theme_00796B);
            adapterColor.add(R.color.kamus_theme_388E3C);
            adapterColor.add(R.color.kamus_theme_512DA8);
            adapterColor.add(R.color.kamus_theme_7B1FA2);
            adapterColor.add(R.color.kamus_theme_C2185B);
            adapterColor.add(R.color.kamus_theme_FBC02D);
            adapterColor.add(R.color.kamus_theme_0097A7);

            adapterColor.setListenerViewHolder(new ListenerView.onClickViewHolder() {
                @Override
                public void onClickListener(View view, Integer position) {
                    preferencesKamusSetting.setColor(getContext().getResources().getColor(adapterColor.getItem(position)));
                    adapterColor.notifyDataSetChanged();
                    Helper.changeTheme(getActivity());
                }
            });
        }

        final Setting warnaThema = new Setting("Warna Tema", FragmentPengaturan.TypePengaturan.color);
        warnaThema.setAdapter(adapterColor);
        adapter.add(warnaThema);


        Setting changeReadMode = new Setting("Mode Malam", FragmentPengaturan.TypePengaturan.changemode);
        adapter.add(changeReadMode);
        changeReadMode.setOnCheckedViewHolder(new ListenerView.onCheckedViewHolder() {
            @Override
            public void onChangeViewHolder(CompoundButton compoundButton, boolean isChecked, Integer position) {
                preferencesKamusSetting.setReadMode(isChecked);
                Helper.changeTheme(getActivity());
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

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("a", "asdfasdfasd");
        super.onSaveInstanceState(outState);
    }
}
