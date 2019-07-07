package cyber.com.kawruh.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cyber.com.kawruh.R;

public class FragmentPengaturan extends Fragment {
    private static final String TYPE_PENGATURAN = "type_pengaturan";

    public static FragmentPengaturan init(TypePengaturan typePengaturan) {
        FragmentPengaturan fragmentPengaturan = new FragmentPengaturan();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TYPE_PENGATURAN, typePengaturan);

        return fragmentPengaturan;
    }

    TypePengaturan typePengaturan;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            typePengaturan = (TypePengaturan) getArguments().getSerializable(TYPE_PENGATURAN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;

        switch (typePengaturan) {
            case color:
                view = inflater.inflate(R.layout.fragment_pengaturan_color, container, false);
                break;
            case direction:
                view = inflater.inflate(R.layout.fragment_pengaturan_direction, container, false);
                break;
            case changemode:
                view = inflater.inflate(R.layout.fragment_pengaturan_change_mode, container, false);
                break;
        }

        return view;
    }

    public enum TypePengaturan {
        color, changemode, direction;

        public static TypePengaturan valueOf(Integer integer) {
            return values()[integer];
        }
    }
}
