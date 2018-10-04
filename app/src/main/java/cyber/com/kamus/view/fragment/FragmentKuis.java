package cyber.com.kamus.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cyber.com.kamus.R;
import cyber.com.kamus.databinding.FragmentKuisSingleBinding;
import cyber.com.kamus.util.listener.ConnectionFragmentKuis;

public class FragmentKuis extends Fragment implements ConnectionFragmentKuis {
    FragmentKuisSingleBinding binding;

    public static FragmentKuis init() {
        FragmentKuis fragmentKuis = new FragmentKuis();

        return fragmentKuis;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        binding.question.setText("Abang");

        for (int i = 0; i < 4; i++) {
            Button button = new Button(new ContextThemeWrapper(getContext(), R.style.kamus_kuis_button_answer_default), null, R.style.kamus_kuis_button_answer_default);

            button.setText("Jawaban 1");

            binding.layoutButton.addView(button);
        }
    }

    @Override
    public void nextKuis() {

    }

    @Override
    public void finish() {

    }
}
