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

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class HiResTextField extends JTextField implements HiResTextComponent {

    public HiResTextField() {
    }

    public HiResTextField(String text) {
        super(text);
    }

    public HiResTextField(int columns) {
        super(columns);
    }

    public HiResTextField(String text, int columns) {
        super(text, columns);
    }

    public HiResTextField(Document doc, String text, int columns) {
        super(doc, text, columns);
    }

    @Override
    public void setFont(Font f) {
        super.setFont(ScreenUtils.font(f));
    }

    @Override
    public JTextComponent comp() {
        return this;
    }

}
