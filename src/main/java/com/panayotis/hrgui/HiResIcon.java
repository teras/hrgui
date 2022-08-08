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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;

public class HiResIcon extends ImageIcon {
    private static final float SCALE_FACTOR = ScreenUtils.getScaleFactor();

    private final int cWidth;
    private final int cHeight;
    private final int scale;

    public HiResIcon(Image image, int scale) {
        super(image);
        this.scale = scale;
        this.cWidth = (int) (image.getWidth(null) * SCALE_FACTOR / scale + 0.5f);
        this.cHeight = (int) (image.getHeight(null) * SCALE_FACTOR / scale + 0.5f);
    }

    private HiResIcon(ScalableImage scalableImage) {
        this(scalableImage == null ? null : scalableImage.image, scalableImage == null ? 1 : scalableImage.scale);
    }

    public HiResIcon(String resourceName, boolean tinted) {
        this(resourceToImage(resourceName, tinted, null, null));
    }

    public HiResIcon(String resourceName, Color topColor, Color bottomColor) {
        this(resourceToImage(resourceName, topColor != null || bottomColor != null, topColor, bottomColor));
    }

    public static HiResIcon fromIcon(Icon icon) {
        if (icon == null || icon instanceof HiResIcon)
            return (HiResIcon) icon;
        throw new IllegalArgumentException("Unable to retrieve icon scale");
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        ImageObserver observer = getImageObserver();
        if (observer == null) observer = c;
        upgradeQuality(g).drawImage(getImage(), x, y, cWidth, cHeight, observer);
    }

    @Override
    public int getIconHeight() {
        return cHeight;
    }

    @Override
    public int getIconWidth() {
        return cWidth;
    }

    public HiResIcon getDisabledIcon() {
        return new HiResIcon(GrayFilter.createDisabledImage(getImage()), scale);
    }

    private static ScalableImage resourceToImage(String resource, boolean tinted, Color topColor, Color bottomColor) {
        ScalableImage in = findIcon(resource);
        if (in == null || !tinted) return in;
        if (topColor == null) topColor = ScreenUtils.topcolor;
        if (bottomColor == null) bottomColor = ScreenUtils.bottomcolor;
        BufferedImage out = new BufferedImage(in.image.getWidth(null), in.image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = upgradeQuality(out.createGraphics());
        g2.drawImage(in.image, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1));
        g2.setPaint(new GradientPaint(0, 0, topColor, 0, in.image.getHeight(null), bottomColor));
        g2.fillRect(0, 0, in.image.getWidth(null), in.image.getHeight(null));
        g2.dispose();
        return new ScalableImage(out, in.scale);
    }

    private static BufferedImage loadIcon(String resourcePath) {
        try {
            URL resource = HiResIcon.class.getClassLoader().getResource(resourcePath);
            return resource != null ? ImageIO.read(resource) : null;
        } catch (Exception error) {
            return null;
        }
    }

    private static ScalableImage findIcon(String resourcePath) {
        BufferedImage result;
        // First return based on real scale
        float scaled = ScreenUtils.getScaleFactor();
        if (scaled > 2.2) {
            result = loadIcon(resourcePath + "@3x.png");
            if (result != null) return new ScalableImage(result, 3);
        }
        if (scaled > 1.1) {
            result = loadIcon(resourcePath + "@2x.png");
            if (result != null) return new ScalableImage(result, 2);
        }
        result = loadIcon(resourcePath + ".png");
        if (result != null) return new ScalableImage(result, 1);

        // Not found based on real scale, return using any scale then
        result = loadIcon(resourcePath + "@2x.png");
        if (result != null) return new ScalableImage(result, 2);
        result = loadIcon(resourcePath + "@3x.png");
        if (result != null) return new ScalableImage(result, 3);

        // Not found
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

    private static class ScalableImage {
        private final Image image;
        private final int scale;

        public ScalableImage(Image image, int scale) {
            this.image = image;
            this.scale = scale;
        }
    }
}
