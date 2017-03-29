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

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class HiResIcon extends ImageIcon {

    private static int scale = ScreenUtils.isHiDPI() ? 2 : 1;

    public HiResIcon(String resourceName, boolean tinted) {
        super(resourceToImage(resourceName, tinted));
    }

    public HiResIcon(Image image) {
        super(image);
    }

    public HiResIcon(Icon defaultIcon) {
        super(iconToImage(defaultIcon));
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        if (scale < 2) {
            super.paintIcon(c, g, x, y);
            return;
        }
        ImageObserver observer = getImageObserver();
        if (observer == null)
            observer = c;
        Image image = getImage();
        final Graphics2D g2 = (Graphics2D) g.create(x, y, image.getWidth(observer), image.getHeight(observer));
        g2.scale(ScreenUtils.getGraphicsScale() / scale, ScreenUtils.getGraphicsScale() / scale);
        g2.drawImage(image, 0, 0, observer);
        g2.scale(1, 1);
        g2.dispose();
    }

    @Override
    public int getIconHeight() {
        return scale > 1
                ? (int) (ScreenUtils.getGraphicsScale() * super.getIconHeight() / scale)
                : super.getIconHeight();
    }

    @Override
    public int getIconWidth() {
        return scale > 1
                ? (int) (ScreenUtils.getGraphicsScale() * super.getIconWidth() / scale)
                : super.getIconWidth();
    }

    public HiResIcon getDisabledIcon() {
        return new HiResIcon(GrayFilter.createDisabledImage(getImage()));
    }

    private static Image resourceToImage(String resource, boolean tinted) {
        BufferedImage in;
        try {
            in = ImageIO.read(HiResIcon.class.getClassLoader().getResource(resource + (scale > 1 ? "@2x" : "") + ".png"));
            if (!tinted)
                return in;
        } catch (IOException ex) {
            return null;
        }
        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = out.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.drawImage(in, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1));
        g2.setPaint(new GradientPaint(0, 0, ScreenUtils.topcolor, 0, in.getHeight(), ScreenUtils.bottomcolor));
        g2.fillRect(0, 0, in.getWidth(), in.getHeight());
        g2.dispose();
        return out;
    }

    static Image iconToImage(Icon icon) {
        if (icon instanceof ImageIcon)
            return ((ImageIcon) icon).getImage();
        else {
            int width = icon.getIconWidth();
            int height = icon.getIconHeight();
            BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(width, height);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }

}
