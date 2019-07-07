package cyber.com.kamus.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cyber.com.kamus.R;
import cyber.com.kamus.adapter.AdapterKuis;
import cyber.com.kamus.database.database.Database;
import cyber.com.kamus.databinding.FragmentKuisBinding;
import cyber.com.kamus.model.KuisAdapter;
import cyber.com.kamus.util.decoration.GridItemDecoration;
import cyber.com.kamus.util.listener.ListenerView;
import cyber.com.kamus.view.KuisActivity;

public class FragmentDaftarKuis extends Fragment {
    public static final int KODE_RESULT = 10;
    FragmentKuisBinding binding;
    private Database database;
    private AdapterKuis adapter;

    public static FragmentDaftarKuis init() {
        FragmentDaftarKuis fragmentKuis = new FragmentDaftarKuis();
        Bundle bundle = new Bundle();
        fragmentKuis.setArguments(bundle);
        return fragmentKuis;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.database = new Database(this.getContext());
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

        adapter = new AdapterKuis();
        binding.recycler.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        binding.recycler.setAdapter(adapter);
        binding.recycler.addItemDecoration(new GridItemDecoration(20, 15, 3, false));

        adapter.setOnClickViewHolder(new ListenerView.onClickViewHolder() {
            @Override
            public void onClickListener(View view, Integer position) {
                if (position > 0 && !adapter.getItem(position - 1).getStatus()) {
                    new AlertDialog.Builder(getContext())
                            .setMessage("Bukalah dari level terkecil hingga terbesar")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    return;
                                }
                            })
                            .create().show();
                    return;
                }

                Intent intent = new Intent(getActivity(), KuisActivity.class);
                intent.putExtra(KuisActivity.KUISDATA, adapter.getItem(position));
                intent.putExtra(KuisActivity.ID, position);
                startActivityForResult(intent, KODE_RESULT);
            }
        });

        for (KuisAdapter kuisAdapter : database.getTableKuis().selectAll()) {
            adapter.add(kuisAdapter);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.database.close();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(getClass().getSimpleName(), "onActivityResult: " + requestCode + " " + resultCode);

        if (requestCode == KODE_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                int pos = data.getIntExtra(KuisActivity.ID, -1);
                KuisAdapter kuisAdapter = data.getParcelableExtra(KuisActivity.KUISDATA);

                if (pos >= 0 && kuisAdapter != null) {
                    adapter.setItem(pos, kuisAdapter);
                }
            }

        }

    }
}
