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
import javax.swing.JRadioButton;

public class HiResRadioButton extends JRadioButton implements HiResComponent {

    public HiResRadioButton() {
    }

    public HiResRadioButton(Icon icon) {
        super(icon);
    }

    public HiResRadioButton(Action a) {
        super(a);
    }

    public HiResRadioButton(Icon icon, boolean selected) {
        super(icon, selected);
    }

    public HiResRadioButton(String text) {
        super(text);
    }

    public HiResRadioButton(String text, boolean selected) {
        super(text, selected);
    }

    public HiResRadioButton(String text, Icon icon) {
        super(text, icon);
    }

    public HiResRadioButton(String text, Icon icon, boolean selected) {
        super(text, icon, selected);
    }

    @Override
    public void setFont(Font font) {
        super.setFont(ScreenUtils.font(font));
    }

    @Override
    public Component comp() {
        return this;
    }
    
}
