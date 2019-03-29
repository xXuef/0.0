package net.m56.ckkj.mobile.tourism.custom;

import android.content.Context;
import android.graphics.Rect;
import android.widget.TextView;

/**
 * 2017/10/19 10:21
 */

public class FocusTextView extends TextView {
    public FocusTextView(Context context) {
        super(context);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        if (focused) {//当有焦点的时候 开启动画  没有的时候 什么都不做保持状态
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }
}
