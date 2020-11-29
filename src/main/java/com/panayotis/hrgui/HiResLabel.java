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

import java.awt.*;
import javax.swing.*;

public class HiResLabel extends JLabel implements HiResComponent {

    public HiResLabel() {
        this(null, null, LEADING);
    }

    public HiResLabel(String text) {
        this(text, null, LEADING);
    }

    public HiResLabel(HiResIcon icon) {
        this(null, icon, LEADING);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public HiResLabel(String text, HiResIcon icon) {
        this(text, icon, LEADING);
    }

    public HiResLabel(String text, int horizontalAlignment) {
        this(text, null, horizontalAlignment);
    }

    public HiResLabel(HiResIcon icon, int horizontalAlignment) {
        this(null, icon, horizontalAlignment);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public HiResLabel(String text, HiResIcon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        setFont(getFont());
    }

    @Override
    public void setFont(Font f) {
        HiResFontManager.setFont(this, f);
    }

    @Override
    public Font getFont() {
        return HiResFontManager.getFont(this);
    }

    @Override
    public JToolTip createToolTip() {
        return new HiResTooltip(this);
    }

    @Override
    public void setFontSuper(Font font) {
        super.setFont(font);
    }

    @Override
    public Font getFontSuper() {
        return super.getFont();
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
