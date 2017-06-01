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
import javax.swing.JPasswordField;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class HiResPasswordField extends JPasswordField implements HiResTextComponent {

    public HiResPasswordField() {
    }

    public HiResPasswordField(String text) {
        super(text);
    }

    public HiResPasswordField(int columns) {
        super(columns);
    }

    public HiResPasswordField(String text, int columns) {
        super(text, columns);
    }

    public HiResPasswordField(Document doc, String text, int columns) {
        super(doc, text, columns);
    }

    @Override
    public void setFont(Font f) {
        super.setFont(ScreenUtils.font(f));
    }

    @Override
    public Component comp() {
        return this;
    }

    @Override
    public JTextComponent tcomp() {
        return this;
    }

}
