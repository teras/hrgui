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

import javax.swing.Icon;

interface HiResIconManager {

    default void setIcons(HiResIcon icon) {
        setIconSuper(icon);
        setPressedIconSuper(icon);
        setSelectedIconSuper(icon);
        setRolloverIconSuper(icon);
        HiResIcon disabled = icon == null ? null : icon.getDisabledIcon();
        setDisabledIconSuper(disabled);
        setDisabledSelectedIconSuper(disabled);
    }

    void setIconSuper(Icon icon);

    void setPressedIconSuper(Icon icon);

    void setSelectedIconSuper(Icon icon);

    void setRolloverIconSuper(Icon icon);

    void setDisabledIconSuper(Icon disabled);

    void setDisabledSelectedIconSuper(Icon disabled);
}
