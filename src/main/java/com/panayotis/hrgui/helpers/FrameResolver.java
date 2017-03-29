/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panayotis.hrgui.helpers;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;

/**
 *
 * @author teras
 */
public class FrameResolver {

    private static final int YOFFSET = 24;
    private static final int XOFFSET = 12;

    public static void position(Window w, boolean toTheLeft, boolean ignoreSelf) {
        Point p = new Point();
        Dimension d = new Dimension();
        getBoundingBox(p, d, ignoreSelf ? w : null);
        if (d.width == 0 && d.height == 0)
            w.setLocationRelativeTo(null);
        else {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int fx;
            if (toTheLeft) {
                fx = p.x - w.getWidth() - YOFFSET;
                if (p.x < 0)
                    p.x = 0;
            } else {
                fx = p.x + d.width;
                if ((fx + w.getWidth() + YOFFSET) > screenSize.width)
                    fx = screenSize.width - w.getWidth();
                else
                    fx = fx + YOFFSET;
            }
            w.setLocation(fx, (p.y + XOFFSET) < 0 ? 0 : (p.y + XOFFSET));
        }
    }

    public static void getBoundingBox(Point p, Dimension d, Window self) {
        int minx = 0, miny = 0, maxx = 0, maxy = 0;
        boolean first = true;
        for (Frame f : Frame.getFrames())
            if (f != self)
                if (first) {
                    minx = f.getX();
                    miny = f.getY();
                    maxx = f.getX() + f.getWidth();
                    maxy = f.getY() + f.getHeight();
                } else {
                    minx = Math.min(minx, f.getX());
                    miny = Math.min(miny, f.getY());
                    maxx = Math.max(maxx, f.getX() + f.getWidth());
                    maxy = Math.max(maxy, f.getY() + f.getHeight());
                }
        p.x = minx;
        p.y = miny;
        d.width = maxx - minx;
        d.height = maxy - miny;
    }

}
