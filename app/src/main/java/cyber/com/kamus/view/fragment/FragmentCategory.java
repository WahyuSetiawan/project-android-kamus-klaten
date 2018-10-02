package cyber.com.kamus.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cyber.com.kamus.R;
import cyber.com.kamus.adapter.AdapterKategori;
import cyber.com.kamus.databinding.FragmentCategoryBinding;
import cyber.com.kamus.model.Kategori;
import cyber.com.kamus.util.decoration.GridItemDecoration;

public class FragmentCategory extends Fragment {
    FragmentCategoryBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false))
                .getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.title.setText("Category");

        AdapterKategori adapterKategori = new AdapterKategori();
        binding.recycler.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        binding.recycler.setAdapter(adapterKategori);
        binding.recycler.addItemDecoration(new GridItemDecoration(20, 15, 3, false));

        adapterKategori.add(
                new Kategori(
                        R.drawable.ic_bug_report_black_24dp,
                        "Hewan"
                )
        );

        adapterKategori.add(
                new Kategori(
                        R.drawable.ic_volume_off_black_24dp,
                        "Kata Kasar"
                )
        );

        adapterKategori.add(
                new Kategori(
                        R.drawable.ic_supervisor_account_black_24dp,
                        "Keluarga"
                )
        );

        adapterKategori.add(
                new Kategori(
                        R.drawable.ic_color_lens_black_24dp,
                        "Mainan"
                )
        );

        adapterKategori.add(
                new Kategori(
                        R.drawable.ic_face_black_24dp,
                        "Orang"
                )
        );

        adapterKategori.add(
                new Kategori(
                        R.drawable.ic_local_florist_black_24dp,
                        "Tanaman"
                )
        );
    }
}
