package cyber.com.kamus.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cyber.com.kamus.R;
import cyber.com.kamus.model.Kuis;

public class FragmentFinishKuis extends Fragment {
    public static FragmentFinishKuis init() {
        FragmentFinishKuis fragmentFinishKuis = new FragmentFinishKuis();

        return fragmentFinishKuis;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kuis_finish, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
