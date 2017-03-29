/* Copyright (c) 2016 by crossmobile.org
 *
 * CrossMobile is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 2.
 *
 * CrossMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CrossMobile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package com.panayotis.hrgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

public class ScreenUtils {

    static Color topcolor = Color.black;
    static Color bottomcolor = Color.black;
    private final static boolean HiDPI;
    private final static boolean shouldAdjust;
    private final static float dpiScale;
    private final static float graphicsScale;
    private final static float textScale;

    static {
        String OS = System.getProperty("os.name").toLowerCase();
        boolean IS_LINUX = OS.contains("linux");
        boolean IS_WINDOWS = OS.contains("windows");
        boolean IS_MACOSX = OS.contains("mac");

        HiDPI = Toolkit.getDefaultToolkit().getScreenResolution() > 120;
        shouldAdjust = !IS_MACOSX && HiDPI;
        dpiScale = HiDPI ? 2 : 1;

        graphicsScale = shouldAdjust ? dpiScale : 1;
        textScale = shouldAdjust ? (float) Math.pow(dpiScale, 0.15) : 1;

    }

    public static void setTint(Color topColor, Color bottomColor) {
        if (topColor != null)
            ScreenUtils.topcolor = topColor;
        if (bottomColor != null)
            ScreenUtils.bottomcolor = bottomColor;
    }

    public static boolean isHiDPI() {
        return HiDPI;
    }

    public static float getGraphicsScale() {
        return graphicsScale;
    }

    public static float getTextScale() {
        return textScale;
    }

    public static Font font(Font source) {
        return shouldAdjust
                ? source.deriveFont(source.getSize2D() * textScale)
                : source;
    }

    public static Font fontFull(Font source) {
        return source.deriveFont(source.getSize2D() * textScale * textScale * graphicsScale);
    }

    public static Dimension dimension(Dimension d) {
        return shouldAdjust
                ? new Dimension((int) (d.width * graphicsScale), (int) (d.height * graphicsScale))
                : d;
    }

    public static Insets insets(int top, int left, int bottom, int right) {
        return shouldAdjust
                ? new Insets((int) (top * graphicsScale), (int) (left * graphicsScale), (int) (bottom * graphicsScale), (int) (right * graphicsScale))
                : new Insets(top, left, bottom, right);
    }

    public static Insets insets(Insets i) {
        return insets(i.top, i.left, i.bottom, i.right);
    }
}
