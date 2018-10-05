package cyber.com.kamus.view.fragment;

import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.airbnb.paris.Paris;

import java.util.ArrayList;

import cyber.com.kamus.R;
import cyber.com.kamus.databinding.FragmentKuisSingleBinding;
import cyber.com.kamus.model.Kuis;
import cyber.com.kamus.preferences.AttrHelper;
import cyber.com.kamus.util.Helper;
import cyber.com.kamus.util.listener.ConnectionFragmentKuis;

public class FragmentKuis extends Fragment implements View.OnClickListener {
    public static final String KUIS = "KUIS";
    private FragmentKuisSingleBinding binding;
    private Kuis kuis;
    private ArrayList<Button> buttons = new ArrayList<>();

    public static FragmentKuis init(Kuis kuis) {
        FragmentKuis fragmentKuis = new FragmentKuis();

        Bundle bundle = new Bundle();
        bundle.putParcelable(KUIS, kuis);

        fragmentKuis.setArguments(bundle);

        return fragmentKuis;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.kuis = getArguments().getParcelable(KUIS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kuis_single, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toptitle.setText("Kuis 1");
        binding.skor.setText("Skor : 000");
        binding.time.setText("Waktu : 00:00");
        binding.question.setText(kuis.getKamus().getJawa());

        int index = 0;

        for (int i = 0; i < 4; i++) {
            Button button = new Button(this.getContext());
            button.setId(i);
            button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            Paris.style(button).apply(R.style.kamus_kuis_button_answer_default);

            if (i == kuis.getJawaBenar()) {
                button.setText(kuis.getKamus().getIndonesia());
                index = 1;
            } else {
                button.setText(kuis.getDataJawabKamusLain().get(i - index).getIndonesia());
            }

            binding.layoutButton.addView(button);
            button.setOnClickListener(this);

            buttons.add(button);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        this.kuis.setJawab(v.getId());

        for (Button bu : this.buttons) {
            bu.setBackgroundTintList(
                    ColorStateList.valueOf(new AttrHelper(getContext()).getColorButtonDefault())
            );
            bu.setTextColor(new AttrHelper(getContext()).getColorButtonTextDefault());
        }

        if (v.getId() != kuis.getJawaBenar()) {
            Button buttonfalse = this.buttons.get(v.getId());
            buttonfalse.setBackgroundTintList(
                    ColorStateList.valueOf(new AttrHelper(getContext()).getColorButtonFalse())
            );
            buttonfalse.setTextColor(new AttrHelper(getContext()).getColorButtonTextFalse());
        }

        Button button = this.buttons.get(kuis.getJawaBenar());
        button.setBackgroundTintList(
                ColorStateList.valueOf(new AttrHelper(getContext()).getColorButtonTrue())
        );
        button.setTextColor(new AttrHelper(getContext()).getColorButtonTextTrue());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((ConnectionFragmentKuis) getActivity()).nextKuis(kuis);
            }
        }, 100);
    }
}
