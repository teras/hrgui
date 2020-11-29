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
import java.util.Vector;

public class HiResList<A> extends JList<A> implements HiResComponent {
    public HiResList() {
        this(new DefaultListModel<>());
    }

    public HiResList(A[] listData) {
        this(new AbstractListModel<A>() {
            @Override
            public int getSize() {
                return listData.length;
            }

            @Override
            public A getElementAt(int index) {
                return listData[index];
            }
        });
    }

    public HiResList(Vector<? extends A> listData) {
        this(new AbstractListModel<A>() {
            @Override
            public int getSize() {
                return listData.size();
            }

            @Override
            public A getElementAt(int index) {
                return listData.get(index);
            }
        });
    }

    public HiResList(ListModel<A> dataModel) {
        super(dataModel);
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
}
