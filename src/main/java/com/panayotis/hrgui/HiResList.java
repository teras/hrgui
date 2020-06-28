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
import java.util.Vector;
import javax.swing.JList;
import javax.swing.ListModel;

public class HiResList<A> extends JList<A> implements HiResComponent {

    public HiResList(ListModel<A> dataModel) {
        super(dataModel);
    }

    public HiResList(A[] listData) {
        super(listData);
    }

    public HiResList(Vector<? extends A> listData) {
        super(listData);
    }

    public HiResList() {
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
