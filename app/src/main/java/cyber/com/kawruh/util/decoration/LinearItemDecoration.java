package cyber.com.kawruh.util.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private int spacingVertical = 0;
    private int spacingHorizontal = 0;
    private TypeDirection typeDirection = TypeDirection.horizontal;

    public LinearItemDecoration(int spacingHorizontal, int spacingVertical, TypeDirection typeDirectiion) {
        this.spacingHorizontal = spacingHorizontal;
        this.spacingVertical = spacingVertical;
        this.typeDirection = typeDirectiion;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        switch (typeDirection){
            case vertical:
                if (position > 0) {
                    outRect.top = spacingVertical;
                }

                outRect.left = spacingHorizontal;
                outRect.right = spacingHorizontal;
                break;
            case horizontal:
                if (position > 0) {
                    outRect.left = spacingHorizontal;
                }

                outRect.top = spacingVertical;
                outRect.bottom = spacingVertical;
                break;
        }
    }

    public enum TypeDirection {
        vertical, horizontal
    }
}
