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

import javax.swing.*;
import java.awt.*;

public class HiResMenu extends JMenu implements HiResIconManager, HiResComponent {

    public HiResMenu() {
        this((Action) null);
    }

    public HiResMenu(Action a) {
        this((String) null);
        setAction(a);
    }

    public HiResMenu(String s) {
        super(s);
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

    public void setIcon(String iconResource, boolean tinted) {
        setIcons(iconResource == null ? null : new HiResIcon(iconResource, tinted));
    }

    public void setIcon(Icon defaultIcon) {
        HiResIcon icon = defaultIcon == null ? null : defaultIcon instanceof HiResIcon ? (HiResIcon) defaultIcon : new HiResIcon(defaultIcon);
        setIcons(icon);
    }

    public void setSelectedIcon(String iconResource, boolean tinted) {
        HiResIcon icn = iconResource == null ? null : new HiResIcon(iconResource, tinted);
        setSelectedIconSuper(iconResource == null ? null : new HiResIcon(iconResource, tinted));
        setDisabledIconSuper(icn == null ? null : icn.getDisabledIcon());
    }

    @Override
    public void setIconSuper(Icon defaultIcon) {
        super.setIcon(defaultIcon);
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

    @Override
    public Component comp() {
        return this;
    }
}
