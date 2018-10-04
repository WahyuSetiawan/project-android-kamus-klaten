package cyber.com.kamus.view.fragment;

import android.content.Intent;
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
import cyber.com.kamus.util.listener.ListenerView;
import cyber.com.kamus.view.CategoryActivity;

public class FragmentCategory extends Fragment {
    FragmentCategoryBinding binding;

    Database database;

    public static FragmentCategory init() {
        FragmentCategory fragmentCategory = new FragmentCategory();
        Bundle bundle = new Bundle();
        fragmentCategory.setArguments(bundle);
        return fragmentCategory;
    }

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

        final AdapterKategori adapterKategori = new AdapterKategori();

        binding.recycler.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        binding.recycler.setAdapter(adapterKategori);
        binding.recycler.addItemDecoration(new GridItemDecoration(20, 15, 3, false));

        ArrayList<Kategori> kategoris = database.getTableCategory().getCategories();

        adapterKategori.setListenerViewHolder(new ListenerView.onClickViewHolder() {
            @Override
            public void onClickListener(View view, Integer position) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra(CategoryActivity.CATEGORY, adapterKategori.getItem(position).getName());
                getActivity().startActivity(intent);
            }
        });

        for (Kategori ka : kategoris) {
            adapterKategori.add(ka);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        database.close();
    }
}
