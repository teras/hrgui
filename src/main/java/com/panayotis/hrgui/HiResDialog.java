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
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import javax.swing.JDialog;

public class HiResDialog extends JDialog implements HiResComponent {

    public HiResDialog() {
    }

    public HiResDialog(Frame owner) {
        super(owner);
    }

    public HiResDialog(Frame owner, boolean modal) {
        super(owner, modal);
    }

    public HiResDialog(Frame owner, String title) {
        super(owner, title);
    }

    public HiResDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    public HiResDialog(Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    public HiResDialog(Dialog owner) {
        super(owner);
    }

    public HiResDialog(Dialog owner, boolean modal) {
        super(owner, modal);
    }

    public HiResDialog(Dialog owner, String title) {
        super(owner, title);
    }

    public HiResDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    public HiResDialog(Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    public HiResDialog(Window owner) {
        super(owner);
    }

    public HiResDialog(Window owner, ModalityType modalityType) {
        super(owner, modalityType);
    }

    public HiResDialog(Window owner, String title) {
        super(owner, title);
    }

    public HiResDialog(Window owner, String title, ModalityType modalityType) {
        super(owner, title, modalityType);
    }

    public HiResDialog(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
        super(owner, title, modalityType, gc);
    }

    @Override
    public void setSize(Dimension d) {
        setSize(d.width, d.height);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize((int) (ScreenUtils.getGraphicsScale() * width), (int) (ScreenUtils.getGraphicsScale() * height));
    }

    @Override
    public void setMinimumSize(Dimension minimumSize) {
        super.setMinimumSize(ScreenUtils.dimension(minimumSize));
    }

    @Override
    public void setMaximumSize(Dimension maximumSize) {
        super.setMaximumSize(ScreenUtils.dimension(maximumSize));
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(ScreenUtils.dimension(preferredSize));
    }

    @Override
    public Component comp() {
        return this;
    }

}
