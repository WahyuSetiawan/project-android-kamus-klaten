package cyber.com.kamus.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cyber.com.kamus.R;
import cyber.com.kamus.adapter.AdapterSearch;
import cyber.com.kamus.databinding.FragmentSearchBinding;

public class FragmentSearch extends Fragment {
    private FragmentSearchBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tabCategory.addTab(binding.tabCategory.newTab().setText("Bahasa Jawa"));
        binding.tabCategory.addTab(binding.tabCategory.newTab().setText("Bahasa Indonesia"));

        final AdapterSearch adapterSearch = new AdapterSearch();
        adapterSearch.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();

                if (adapterSearch != null) {
                    if (adapterSearch.getItemCount() <= 0) {
                        binding.backgroundrecycler.setVisibility(View.VISIBLE);
                        binding.recycler.setVisibility(View.GONE);
                    } else {
                        binding.backgroundrecycler.setVisibility(View.GONE);
                        binding.recycler.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}
