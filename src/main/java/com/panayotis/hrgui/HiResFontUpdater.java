package com.panayotis.hrgui;

import javax.swing.*;
import java.awt.*;

public class HiResFontUpdater {
    public static void apply(Container container) {
        applyImpl(container, true);
    }

    private static void applyImpl(Container container, boolean alsoSize) {
        if (!ScreenUtils.shouldScale())
            return;
        for (Component c : container.getComponents()) {
            if (c instanceof JComponent) {
                JComponent i = (JComponent) c;
                i.setFont(i.getFont().deriveFont(i.getFont().getSize() * ScreenUtils.getScaleFactor()));
            }
            if (c instanceof Container)
                applyImpl((Container) c, false);
        }
        if (alsoSize) {
            container.setSize(ScreenUtils.dimension(container.getSize()));
            container.setPreferredSize(ScreenUtils.dimension(container.getPreferredSize()));
        }
    }
}
