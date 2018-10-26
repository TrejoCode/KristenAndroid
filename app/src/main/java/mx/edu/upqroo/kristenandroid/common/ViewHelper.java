package mx.edu.upqroo.kristenandroid.common;

import android.view.View;
import android.view.ViewGroup;

/**
 * <h1>ViewHelper</h1>
 * Class with helper functions for the views.
 */
public class ViewHelper {
    /**
     * This method is used in the recycler views ot let a margin in the bottom in order to
     * always let enough space for the fav.
     * @param view view
     * @param bottomMargin margin desired
     */
    public static void SetBottomMargin(View view, int bottomMargin) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params =
                    (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.setMargins(params.leftMargin, params.topMargin,
                    params.rightMargin, bottomMargin);
            view.requestLayout();
        }
    }
}
