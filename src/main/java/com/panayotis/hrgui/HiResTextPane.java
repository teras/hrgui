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
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;

public class HiResTextPane extends JTextPane implements HiResTextComponent {

    private boolean shouldMoveToBottom = true;

    public HiResTextPane() {
    }

    public HiResTextPane(StyledDocument doc) {
        super(doc);
    }

    @Override
    public void setFont(Font font) {
        super.setFont(ScreenUtils.font(font));
    }

    @Override
    public JTextComponent comp() {
        return this;
    }

    public void appendText(CharSequence text, AttributeSet attr) {
        if (text == null)
            return;
        StyledDocument doc = getStyledDocument();
        try {
            doc.insertString(doc.getLength(), text.toString(), attr);
        } catch (BadLocationException ex) {
        }
        if (shouldMoveToBottom)
            setCaretPosition(doc.getLength());
    }

    public void removeChars(int howMany) {
        if (howMany <= 0)
            return;

        StyledDocument doc = getStyledDocument();
        int length = doc.getLength();
        if (howMany > length)
            howMany = length;
        if (howMany < 1)
            return;
        try {
            doc.remove(length - howMany, howMany);
        } catch (BadLocationException ex) {
        }
        setCaretPosition(doc.getLength());
    }

    public void setShouldMoveToBottom(boolean shouldMoveToBottom) {
        this.shouldMoveToBottom = shouldMoveToBottom;
    }

}
