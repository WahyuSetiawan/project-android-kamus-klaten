package cyber.com.kawruh.util.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    String TAG = GridItemDecoration.class.getSimpleName();
    private int mGridSize = 4;
    private int mSizeGridSpacingPx = 24;

    private int heightSpacing = 0;
    boolean mNeedLeftSpacing = false;

    private Context context;
    private int spanCount;
    private boolean includeEdge;
    private int spacing;

    public GridItemDecoration(Context context, int heightSpacing, int leftSpacing, int spanCount, boolean includeEdge) {
        this.context = context;
        this.heightSpacing = heightSpacing;
        this.spacing = leftSpacing;
        this.spanCount = spanCount;
        this.includeEdge = includeEdge;
    }

    public GridItemDecoration(int heightSpacing, int leftSpacing, int spanCount, boolean includeEdge) {
        this.heightSpacing = heightSpacing;
        this.spacing = leftSpacing;
        this.spanCount = spanCount;
        this.includeEdge = includeEdge;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount; // item column

        if (position / spanCount > 0) {
            outRect.top = heightSpacing;
        }
        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount;
            outRect.right = (column + 1) * spacing / spanCount;

            if (position < spanCount) {
                outRect.top = spacing;
            }
            outRect.bottom = spacing;
        } else {
            outRect.left = column * spacing / spanCount;
            outRect.right = spacing - (column + 1) * spacing / spanCount;
            if (position >= spanCount) {
                outRect.top = spacing;
            }
        }
    }
}
