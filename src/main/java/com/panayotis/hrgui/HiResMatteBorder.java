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
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.border.MatteBorder;

public class HiResMatteBorder extends MatteBorder {

    public HiResMatteBorder(int top, int left, int bottom, int right, Color matteColor) {
        super(ScreenUtils.insets(top, left, bottom, right), matteColor);
    }

    public HiResMatteBorder(Insets borderInsets, Color matteColor) {
        super(ScreenUtils.insets(borderInsets), matteColor);
    }

    public HiResMatteBorder(int top, int left, int bottom, int right, Icon tileIcon) {
        super(ScreenUtils.insets(top, left, bottom, right), tileIcon);
    }

    public HiResMatteBorder(Insets borderInsets, Icon tileIcon) {
        super(ScreenUtils.insets(borderInsets), tileIcon);
    }

    public HiResMatteBorder(Icon tileIcon) {
        super(tileIcon);
    }
}
