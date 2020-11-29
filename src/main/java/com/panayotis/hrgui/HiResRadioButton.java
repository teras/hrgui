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

public class HiResRadioButton extends JRadioButton implements HiResIconManager, HiResComponent {

    public HiResRadioButton() {
        this(null, null, false);
    }

    public HiResRadioButton(HiResIcon icon) {
        this(null, icon, false);
    }

    public HiResRadioButton(Action a) {
        this(null, null, false);
        setAction(a);
    }

    public HiResRadioButton(HiResIcon icon, boolean selected) {
        this(null, icon, selected);
    }

    public HiResRadioButton(String text) {
        this(text, null, false);
    }

    public HiResRadioButton(String text, boolean selected) {
        this(text, null, selected);
    }

    public HiResRadioButton(String text, HiResIcon icon) {
        this(text, icon, false);
    }

    public HiResRadioButton(String text, HiResIcon icon, boolean selected) {
        super(text, selected);
        setIcons(icon);
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

    @Override
    public void setIconSuper(Icon icon) {
        super.setIcon(icon);
    }

    @Override
    public void setPressedIconSuper(Icon icon) {
        super.setPressedIcon(icon);
    }

    @Override
    public void setSelectedIconSuper(Icon icon) {
        super.setSelectedIcon(icon);
    }

    @Override
    public void setRolloverIconSuper(Icon icon) {
        super.setRolloverIcon(icon);
    }

    @Override
    public void setDisabledIconSuper(Icon disabled) {
        super.setDisabledIcon(disabled);
    }

    @Override
    public void setDisabledSelectedIconSuper(Icon disabled) {
        super.setDisabledSelectedIcon(disabled);
    }
}
