package com.panayotis.hrgui;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class HiResFont extends Font {
    private final float size;

    public HiResFont(String name, int style, float size) {
        this(name, style, size, false);
    }

    public HiResFont(Font font) {
        this(font.getName(), font.getStyle(), font.getSize(), font instanceof HiResFont);
    }

    private HiResFont(String name, int style, float size, boolean derivedFromHiRes) {
        super(name, style, (int) Math.ceil(derivedFromHiRes ? size : size * ScreenUtils.getTextScale()));
        this.size = size;
    }

    @Override
    public Font deriveFont(float size) {
        return new HiResFont(getName(), getStyle(), size);
    }

    @Override
    public Font deriveFont(int style, float size) {
        return new HiResFont(getName(), style, size);
    }

    @Override
    public Font deriveFont(int style) {
        return new HiResFont(getName(), style, size);
    }

    @Override
    public int getSize() {
        return (int) Math.ceil(size);
    }

    @Override
    public Font deriveFont(Map<? extends AttributedCharacterIterator.Attribute, ?> attributes) {
        throw new UnsupportedOperationException("Unable to create HiResFont from attributes");
    }

    @Override
    public Font deriveFont(AffineTransform trans) {
        throw new UnsupportedOperationException("Unable to create HiResFont with AffineTransform");
    }

    @Override
    public Font deriveFont(int style, AffineTransform trans) {
        throw new UnsupportedOperationException("Unable to create HiResFont with AffineTransform");
    }
}
