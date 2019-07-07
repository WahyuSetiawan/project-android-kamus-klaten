package cyber.com.kawruh.util.listener;

import android.view.View;
import android.widget.CompoundButton;

public class ListenerView {
    public interface onClickViewHolder {
        void onClickListener(View view, Integer position);
    }

    public interface onLongClickViewHolder {
        boolean onLongClickViewHolder(View view, Integer position);
    }

    public interface onCheckedViewHolder {
        public void onChangeViewHolder(CompoundButton compoundButton, boolean isChecked, Integer position);
    }
}
