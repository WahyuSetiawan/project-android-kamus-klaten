package cyber.com.kawruh.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cyber.com.kawruh.R;
import cyber.com.kawruh.databinding.FragmentKuisFinishBinding;
import cyber.com.kawruh.model.KuisAdapter;
import cyber.com.kawruh.util.listener.ConnectionFragmentKuis;

public class FragmentFinishKuis extends Fragment {
    public static final String SCOREARGS = "SCOREARGS";
    public static final String TIMEARGS = "TIMEARGS";

    private int score = 0;
    private long time = 0;
    private FragmentKuisFinishBinding binding;


    public static FragmentFinishKuis init(KuisAdapter kuisAdapter) {
        FragmentFinishKuis fragmentFinishKuis = new FragmentFinishKuis();

        Bundle bundle = new Bundle();

        bundle.putInt(SCOREARGS, kuisAdapter.getScore());
        bundle.putLong(TIMEARGS, kuisAdapter.getTime());

        fragmentFinishKuis.setArguments(bundle);

        return fragmentFinishKuis;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.score = getArguments().getInt(SCOREARGS);
            this.time = getArguments().getLong(TIMEARGS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return (this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kuis_finish, container,
                false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        long diff = time;

        long Days = diff / (24 * 60 * 60 * 1000);
        long Hours = diff / (60 * 60 * 1000) % 24;
        long Minutes = diff / (60 * 1000) % 60;
        long Seconds = diff / 1000 % 60;

        this.binding.result.setText(String.valueOf(score));
        this.binding.time.setText("Waktu Selesai : " + Minutes + ":" + Seconds);

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
