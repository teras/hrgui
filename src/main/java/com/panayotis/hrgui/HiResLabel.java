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

import java.awt.Component;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JLabel;

public class HiResLabel extends JLabel implements HiResComponent {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public HiResLabel(String text, HiResIcon icon, int horizontalAlignment) {
        this(text, horizontalAlignment);
        setIcon(icon);
    }

    public HiResLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    public HiResLabel(String text) {
        super(text);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public HiResLabel(HiResIcon icon, int horizontalAlignment) {
        this(icon);
        setHorizontalAlignment(horizontalAlignment);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public HiResLabel(HiResIcon icon) {
        setIcon(icon);
    }

    public HiResLabel() {
    }

    @Override
    public void setFont(Font font) {
        super.setFont(ScreenUtils.font(font));
    }

    @Override
    public Component comp() {
        return this;
    }

    public void setIcon(String iconResource, boolean tinted) {
        setIcon(new HiResIcon(iconResource, tinted));
    }

    public void setIcon(Icon icon) {
        super.setIcon(icon == null ? null : (icon instanceof HiResIcon ? icon : new HiResIcon(icon)));
    }
}
