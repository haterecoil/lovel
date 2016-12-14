package lorem.lovel.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import lorem.lovel.R;

/**
 * Created by mrgn on 13/12/2016.
 *
 */

public class CustomFontTextView extends TextView {

    public CustomFontTextView(Context context) {
        super(context);

        CustomFontUtils.applyCustomFont(this, context, null);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        CustomFontUtils.applyCustomFont(this, context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        CustomFontUtils.applyCustomFont(this, context, attrs);
    }
}