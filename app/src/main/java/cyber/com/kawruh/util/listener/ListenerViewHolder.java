package cyber.com.kamus.util.listener;

import android.view.View;
import android.widget.CompoundButton;

public interface ListenerViewHolder {
    public void onViewHolderClick(View view, Integer position);
    public void onChangeViewHolder(CompoundButton compoundButton, boolean isChecked, Integer position);
    public boolean onViewHolderOnLongClick(View view, Integer position);
}
