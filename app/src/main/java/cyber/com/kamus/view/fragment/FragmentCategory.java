package cyber.com.kamus.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cyber.com.kamus.R;
import cyber.com.kamus.adapter.AdapterKategori;
import cyber.com.kamus.database.Database;
import cyber.com.kamus.databinding.FragmentCategoryBinding;
import cyber.com.kamus.model.Kategori;
import cyber.com.kamus.util.decoration.GridItemDecoration;

public class FragmentCategory extends Fragment {
    FragmentCategoryBinding binding;

    Database database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = new Database(this.getContext());

    }

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

        ArrayList<Kategori> kategoris = database.getTableCategory().getCategories();

        for (Kategori ka : kategoris) {
            adapterKategori.add(ka);
        }
    }
}
