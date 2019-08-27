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
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class HiResIcon extends ImageIcon {

    private static int scale = ScreenUtils.isHiDPI() ? 2 : 1;

    public HiResIcon(String resourceName, boolean tinted) {
        super(resourceToImage(resourceName, tinted, null, null));
    }

    public HiResIcon(String resourceName, Color topColor, Color bottomColor) {
        super(resourceToImage(resourceName, topColor != null || bottomColor != null, topColor, bottomColor));
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
        Graphics2D g = upgradeQuality(g.create(x, y, image.getWidth(observer), image.getHeight(observer)));
        g.scale(ScreenUtils.getGraphicsScale() / scale, ScreenUtils.getGraphicsScale() / scale);
        g.drawImage(image, 0, 0, observer);
        g.scale(1, 1);
        g.dispose();
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

    private static Image resourceToImage(String resource, boolean tinted, Color topcolor, Color bottomcolor) {
        BufferedImage in = findIcon(resource);
        if (in == null || !tinted)
            return in;

        if (topcolor == null)
            topcolor = ScreenUtils.topcolor;
        if (bottomcolor == null)
            bottomcolor = ScreenUtils.bottomcolor;

        BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = upgradeQuality(out.createGraphics());
        g2.drawImage(in, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1));
        g2.setPaint(new GradientPaint(0, 0, topcolor, 0, in.getHeight(), bottomcolor));
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
            Graphics2D g = upgradeQuality(image.createGraphics());
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }

    private static BufferedImage halfSize(Image input) {
        BufferedImage result = new BufferedImage(input.getWidth(null) / 2, input.getHeight(null) / 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = upgradeQuality(result.createGraphics());
        g.drawImage(input, 0, 0, result.getWidth(), result.getHeight(), null);
        g.dispose();
        return result;
    }

    private static BufferedImage findIcon(String resourcePath) {
        URL resource;
        try {
            resource = HiResIcon.class.getClassLoader().getResource(resourcePath + (scale > 1 ? "@2x" : "") + ".png");
            if (resource != null)
                return ImageIO.read(resource);
        } catch (Exception ignored) {
        }
        if (scale <= 1)
            try {
                resource = HiResIcon.class.getClassLoader().getResource(resourcePath + "@2x.png");
                if (resource != null)
                    return halfSize(ImageIO.read(resource));
            } catch (Exception ignored) {
            }
        System.err.println("Unable to locate resource " + resourcePath);
        return null;
    }

    private static Graphics2D upgradeQuality(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return g2;
    }
}
