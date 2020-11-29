package com.panayotis.hrgui;

import java.awt.*;

class HiResFontManager {
    static Font getFont(HiResComponent comp) {
        Font font = comp.getFontSuper();
        if (!ScreenUtils.shouldScale() || font == null)
            return font;
        return font instanceof HiResFont ? font : new HiResFont(font.getName(), font.getStyle(), font instanceof HiResFont ? font.getSize() : font.getSize() / ScreenUtils.getTextScale());
    }

    static void setFont(HiResComponent comp, Font font) {
        if (!ScreenUtils.shouldScale())
            comp.setFontSuper(font);
        comp.setFontSuper(ScreenUtils.font(font));
    }
}
