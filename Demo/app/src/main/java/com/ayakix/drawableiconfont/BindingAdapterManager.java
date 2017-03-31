package com.ayakix.drawableiconfont;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.Button;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

/**
 * Created by r_ayaki on 2017/03/31.
 */

public class BindingAdapterManager {
    @BindingAdapter("drawable_icon_font_left")
    public static void setDrawableIconFontLeft(final Button button, final String icon) {
        final Context context = button.getContext();
        final IconicsDrawable iconicsDrawable = new IconicsDrawable(context)
                .icon(FontAwesome.Icon.valueOf(icon))
                .color(button.getCurrentTextColor())
                .sizePx((int)(button.getTextSize() * 1.25));
        button.setCompoundDrawables(iconicsDrawable, null, null, null);
    }
}
