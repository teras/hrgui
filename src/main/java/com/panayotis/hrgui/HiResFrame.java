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

import java.awt.*;
import javax.swing.JFrame;

public class HiResFrame extends JFrame implements HiResComponent {

    public HiResFrame() throws HeadlessException {
    }

    public HiResFrame(GraphicsConfiguration gc) {
        super(gc);
    }

    public HiResFrame(String title) throws HeadlessException {
        super(title);
    }

    public HiResFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
    }

    @Override
    public void setSize(Dimension d) {
        setSize(d.width, d.height);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize((int) (ScreenUtils.getScaleFactor() * width), (int) (ScreenUtils.getScaleFactor() * height));
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
    public void setFont(Font f) {
        HiResFontManager.setFont(this, f);
    }

    @Override
    public Font getFont() {
        return HiResFontManager.getFont(this);
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
