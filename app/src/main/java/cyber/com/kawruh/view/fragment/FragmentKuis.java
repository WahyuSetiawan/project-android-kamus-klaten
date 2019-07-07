package cyber.com.kawruh.view.fragment;

import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.airbnb.paris.Paris;

import java.util.ArrayList;
import java.util.Date;

import cyber.com.kawruh.R;
import cyber.com.kawruh.databinding.FragmentKuisSingleBinding;
import cyber.com.kawruh.model.Kuis;
import cyber.com.kawruh.model.KuisAdapter;
import cyber.com.kawruh.preferences.AttrHelper;
import cyber.com.kawruh.util.listener.ConnectionFragmentKuis;

public class FragmentKuis extends Fragment implements View.OnClickListener {
    public static final String KUIS = "KUIS";
    public static final String KUISADAPTER = "KUISADAPTER";
    private FragmentKuisSingleBinding binding;
    private Kuis kuis;
    private KuisAdapter kuisAdapter;
    private ArrayList<Button> buttons = new ArrayList<>();

    private String EVENT_DATE_TIME = "2018-12-31 10:30:00";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private Handler handler = new Handler();
    private Runnable runnable;
    private Date start_date;
    private long diff = 0;

    public static FragmentKuis init(Kuis kuis, KuisAdapter kuisAdapter) {
        FragmentKuis fragmentKuis = new FragmentKuis();

        Bundle bundle = new Bundle();
        bundle.putParcelable(KUIS, kuis);
        bundle.putParcelable(KUISADAPTER, kuisAdapter);

        fragmentKuis.setArguments(bundle);

        return fragmentKuis;
    }

    private void countDownStart() {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
//                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
//                    Date event_date = dateFormat.parse(EVENT_DATE_TIME);
                    Date current_time = new Date();


                    if (current_time.after(start_date)) {
                        diff = current_time.getTime() - start_date.getTime() + kuisAdapter.getTime();

                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;

                        String min = "0" + Minutes;
                        String sec = "0" + Seconds;

                        binding.time.setText("Waktu " + min.substring(min.length() - 2, 2) + ":" + sec.substring(sec.length() - 2, 2));

                    } else {
                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            kuis = getArguments().getParcelable(KUIS);
            kuisAdapter = getArguments().getParcelable(KUISADAPTER);

            start_date = new Date();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return (binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kuis_single, container,
                false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toptitle.setText("Kuis 1");
        binding.skor.setText("Skor : " + kuisAdapter.getScore());
        binding.time.setText("Waktu : " + kuisAdapter.getTime());
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

        countDownStart();
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
                kuisAdapter.setTime(diff);
                ((ConnectionFragmentKuis) getActivity()).nextKuis(kuis, kuisAdapter);
            }
        }, 100);
    }
}
