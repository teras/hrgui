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

import com.panayotis.appenh.AFileChooser;
import com.panayotis.appenh.EnhancerManager;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

public class ScreenUtils {

    static Color topcolor = Color.black;
    static Color bottomcolor = Color.black;

    private static final boolean HiDPI;
    private static final boolean shouldScale;
    private static final float scaleFactor;

    private static final HiResPreferences prefs = HiResPreferences.getDefault();

    static {
        int dpi = EnhancerManager.getDefault().getDPI();
        HiDPI = dpi > 110;
        float cScale = prefs.scaleFactor();
        if (cScale <= 0) {    // not set yet, should use defaults
            cScale = EnhancerManager.getDefault().shouldScaleUI()
                    ? dpi / 96f
                    : 1;
            prefs.storePrefs(cScale);
        }
        scaleFactor = cScale;
        shouldScale = scaleFactor > 1.01;

        AFileChooser.injectCustomVisuals(fc -> {
            if (fc instanceof JFileChooser)
                HiResFontUpdater.apply((JFileChooser) fc);
        });
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

    public static float getScaleFactor() {
        return scaleFactor;
    }

    public static boolean shouldScale() {
        return shouldScale;
    }

    public static Font font(Font source) {
        if (source == null || source instanceof HiResFont || !shouldScale)
            return source;
        return new HiResFont(source);
    }

    public static Dimension dimension(Dimension d) {
        if (d == null)
            return null;
        return shouldScale
                ? new Dimension((int) (d.width * scaleFactor), (int) (d.height * scaleFactor))
                : d;
    }

    public static Insets insets(int top, int left, int bottom, int right) {
        return shouldScale
                ? new Insets((int) (top * scaleFactor), (int) (left * scaleFactor), (int) (bottom * scaleFactor), (int) (right * scaleFactor))
                : new Insets(top, left, bottom, right);
    }

    public static Insets insets(Insets i) {
        if (i == null)
            return null;
        return insets(i.top, i.left, i.bottom, i.right);
    }

    private ScreenUtils() {
    }
}
