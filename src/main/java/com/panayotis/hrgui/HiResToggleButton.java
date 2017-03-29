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
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JToggleButton;

public class HiResToggleButton extends JToggleButton implements HiResComponent, HiResIconManager {

    public HiResToggleButton() {
    }

    public HiResToggleButton(HiResIcon icon) {
        setIcons(icon);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public HiResToggleButton(HiResIcon icon, boolean selected) {
        setSelected(selected);
        setIcons(icon);
    }

    public HiResToggleButton(String text) {
        super(text);
    }

    public HiResToggleButton(String text, boolean selected) {
        super(text, selected);
    }

    public HiResToggleButton(Action a) {
        super(a);
    }

    public HiResToggleButton(String text, HiResIcon icon) {
        super(text);
        setIcons(icon);
    }

    public HiResToggleButton(String text, HiResIcon icon, boolean selected) {
        super(text, selected);
        setIcons(icon);
    }

    @Override
    public void setFont(Font font) {
        super.setFont(ScreenUtils.font(font));
    }

    public void setIcon(String iconResource, boolean tinted) {
        setIcons(new HiResIcon(iconResource, tinted));
    }

    public void setIcon(Icon defaultIcon) {
        HiResIcon icon = defaultIcon instanceof HiResIcon ? (HiResIcon) defaultIcon : new HiResIcon(defaultIcon);
        setIcons(icon);
    }

    public void setSelectedIcon(String iconResource, boolean tinted) {
        HiResIcon icn = new HiResIcon(iconResource, tinted);
        setSelectedIconSuper(new HiResIcon(iconResource, tinted));
        setDisabledIconSuper(icn.getDisabledIcon());
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
