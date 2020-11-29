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
import java.util.Vector;
import javax.swing.*;

public class HiResComboBox<T> extends JComboBox<T> implements HiResComponent {
    public HiResComboBox() {
        this(new DefaultComboBoxModel<>());
    }

    public HiResComboBox(T[] items) {
        this(new DefaultComboBoxModel<>(items));
    }

    public HiResComboBox(Vector<T> items) {
        this(new DefaultComboBoxModel<>(items));
    }

    public HiResComboBox(ComboBoxModel<T> aModel) {
        super(aModel);
        setFont(getFont());
    }

    @Override
    public Component comp() {
        return this;
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
}
