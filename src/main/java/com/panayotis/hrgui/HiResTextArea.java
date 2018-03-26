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
import javax.swing.JTextArea;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class HiResTextArea extends JTextArea implements HiResTextComponent {

    public HiResTextArea() {
    }

    public HiResTextArea(String text) {
        super(text);
    }

    public HiResTextArea(int rows, int columns) {
        super(rows, columns);
    }

    public HiResTextArea(String text, int rows, int columns) {
        super(text, rows, columns);
    }

    public HiResTextArea(Document doc) {
        super(doc);
    }

    public HiResTextArea(Document doc, String text, int rows, int columns) {
        super(doc, text, rows, columns);
    }

    @Override
    public void setFont(Font f) {
        super.setFont(ScreenUtils.fontFull(f));
    }

    @Override
    public JTextComponent comp() {
        return this;
    }

}
