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

import cyber.com.kamus.R;
import cyber.com.kamus.adapter.AdapterKuis;
import cyber.com.kamus.databinding.FragmentKuisBinding;
import cyber.com.kamus.model.Kuis;
import cyber.com.kamus.util.decoration.GridItemDecoration;

public class FragmentDaftarKuis extends Fragment {
    FragmentKuisBinding binding;

    public static FragmentDaftarKuis init() {
        FragmentDaftarKuis fragmentKuis = new FragmentDaftarKuis();
        Bundle bundle = new Bundle();
        fragmentKuis.setArguments(bundle);
        return fragmentKuis;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kuis, container, false))
                .getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toolbar.setText("Kuis Seru");
        binding.title.setText("Cara bermain kuis");
        binding.content.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque lobortis, eros a varius volutpat, dolor felis tincidunt diam, ac aliquet libero justo ut est. Quisque et ligula dapibus sem finibus convallis id a lorem. Integer consectetur sem ac libero aliquet, at commodo odio pellentesque. In hac habitasse platea dictumst. Vestibulum molestie turpis sit amet magna pharetra aliquet. Nam aliquet, mauris sed scelerisque auctor, sem tortor ornare nisl, quis vulputate odio erat sit amet augue. Duis eget fermentum dui. Proin non sapien in dolor hendrerit facilisis nec eu lacus. Duis elit dui, lacinia in vestibulum at, scelerisque non massa. Etiam consectetur velit in felis luctus congue. Suspendisse potenti. Aliquam ligula risus, mollis at augue in, mattis dapibus lectus. Nam hendrerit sapien id dui semper venenatis.");

        AdapterKuis adapter = new AdapterKuis();
        binding.recycler.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        binding.recycler.setAdapter(adapter);
        binding.recycler.addItemDecoration(new GridItemDecoration(20, 15, 3, false));

        adapter.add(new Kuis(true));
        adapter.add(new Kuis(false));
        adapter.add(new Kuis(false));
        adapter.add(new Kuis(false));
        adapter.add(new Kuis(false));
        adapter.add(new Kuis(false));
        adapter.add(new Kuis(false));
        adapter.add(new Kuis(false));
        adapter.add(new Kuis(false));
    }
}
