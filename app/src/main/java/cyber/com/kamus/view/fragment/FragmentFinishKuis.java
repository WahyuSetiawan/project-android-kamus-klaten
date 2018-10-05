package cyber.com.kamus.view.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cyber.com.kamus.R;
import cyber.com.kamus.databinding.FragmentKuisFinishBinding;
import cyber.com.kamus.model.Kuis;
import cyber.com.kamus.model.KuisAdapter;
import cyber.com.kamus.util.listener.ConnectionFragmentKuis;

public class FragmentFinishKuis extends Fragment {
    public static final String SCOREARGS = "SCOREARGS";
    public static final String TIMEARGS = "TIMEARGS";

    private int score = 0;
    private int time = 0;
    private FragmentKuisFinishBinding binding;


    public static FragmentFinishKuis init(KuisAdapter kuisAdapter) {
        FragmentFinishKuis fragmentFinishKuis = new FragmentFinishKuis();

        Bundle bundle = new Bundle();

        bundle.putInt(SCOREARGS, kuisAdapter.getScore());
        bundle.putInt(TIMEARGS, kuisAdapter.getTime());

        fragmentFinishKuis.setArguments(bundle);

        return fragmentFinishKuis;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.score = getArguments().getInt(SCOREARGS);
            this.time = getArguments().getInt(TIMEARGS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kuis_finish, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.binding.result.setText(String.valueOf(score));
        this.binding.time.setText("Waktu Selesai : " + time);

        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ConnectionFragmentKuis) getActivity()).close();
            }
        });

        this.binding.repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ConnectionFragmentKuis) getActivity()).repeat();
            }
        });

        this.binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ConnectionFragmentKuis) getActivity()).next();
            }
        });
    }
}
