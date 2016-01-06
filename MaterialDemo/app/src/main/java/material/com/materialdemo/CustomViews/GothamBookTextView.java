
package material.com.materialdemo.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import material.com.materialdemo.Utils.FontUtils;


public class GothamBookTextView extends TextView {
    Typeface mGothamBoldTypeface;

    public GothamBookTextView(Context context,
            AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public GothamBookTextView(Context context,
            AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GothamBookTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            mGothamBoldTypeface = FontUtils.loadTypeFace(getContext(), FontUtils.Gotham_Book);
            setTypeface(mGothamBoldTypeface);
        }
    }
}
