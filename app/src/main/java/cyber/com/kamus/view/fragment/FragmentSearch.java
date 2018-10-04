package cyber.com.kamus.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cyber.com.kamus.R;
import cyber.com.kamus.adapter.AdapterSearch;
import cyber.com.kamus.database.Database;
import cyber.com.kamus.databinding.FragmentSearchBinding;
import cyber.com.kamus.util.adapter.Adapter;

public class FragmentSearch extends Fragment {
    public static final String BAHASA_JAWA = "Bahasa Jawa";
    public static final String BAHASA_INDONESIA = "Bahasa Indonesia";

    private FragmentSearchBinding binding;
    private Database database;

    private AdapterSearch adapterSearchIndonesia;
    private AdapterSearch adapterSearchJawa;

    public static FragmentSearch init() {
        FragmentSearch fragmentSearch = new FragmentSearch();
        Bundle bundle = new Bundle();
        fragmentSearch.setArguments(bundle);
        return fragmentSearch;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new Database(this.getContext());

        adapterSearchIndonesia = new AdapterSearch();
        adapterSearchJawa = new AdapterSearch();

        adapterSearchJawa.setSearchFrom(AdapterSearch.SearchFrom.fromJawa);
        adapterSearchIndonesia.setSearchFrom(AdapterSearch.SearchFrom.fromIndonesia);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return (binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search,
                container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        binding.recycler.setAdapter(adapterSearchIndonesia);

        binding.tabCategory.addTab(binding.tabCategory.newTab().setText(BAHASA_JAWA));
        binding.tabCategory.addTab(binding.tabCategory.newTab().setText(BAHASA_INDONESIA));

        binding.tabCategory.addOnTabSelectedListener(onSelected);
        binding.search.addTextChangedListener(textWatcher);

        switch (((AdapterSearch) binding.recycler.getAdapter()).getSearchFrom()) {
            case fromJawa:
                binding.tabCategory.getTabAt(0).select();
                break;
            case fromIndonesia:
                binding.tabCategory.getTabAt(1).select();
                break;
        }

        adapterSearchIndonesia.registerAdapterDataObserver(observer);
        adapterSearchJawa.registerAdapterDataObserver(observer);

        adapterSearchIndonesia.setSearchResults(
                database.getTableKamus().selectAllToHashMapGroup(AdapterSearch.SearchFrom.fromIndonesia)
        );
        adapterSearchJawa.setSearchResults(
                database.getTableKamus().selectAllToHashMapGroup(AdapterSearch.SearchFrom.fromJawa)
        );

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            adapterSearchIndonesia.getFilter().filter(s.toString());
            adapterSearchJawa.getFilter().filter(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            if (adapterSearchIndonesia != null || adapterSearchJawa != null) {
                if (adapterSearchIndonesia.getItemCount() <= 0 || adapterSearchJawa.getItemCount() <= 0) {
                    binding.backgroundrecycler.setVisibility(View.VISIBLE);
                    binding.recycler.setVisibility(View.GONE);
                } else {
                    binding.backgroundrecycler.setVisibility(View.GONE);
                    binding.recycler.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    private TabLayout.BaseOnTabSelectedListener onSelected = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if (tab.getText().equals(BAHASA_JAWA)) {
                binding.recycler.setAdapter(adapterSearchJawa);
            } else {
                binding.recycler.setAdapter(adapterSearchIndonesia);
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}
