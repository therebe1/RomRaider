/*
 *
 * Enginuity Open-Source Tuning, Logging and Reflashing
 * Copyright (C) 2006 Enginuity.org
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */

package enginuity.swing;

import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;

public final class LookAndFeelManager {
    private static final String OS_NAME = "os.name";
    private static final String OS_VERSION = "os.version";
    private static final String MAC_OS_X = "Mac OS X";
    private static final String LINUX = "Linux";

    private LookAndFeelManager() {
        throw new UnsupportedOperationException();
    }

    public static void initLookAndFeel() {
        try {
            if (isPlatform(MAC_OS_X)) {
                // Mac OSX default look and feel looks crap, so use windows instead.
                setRestrictedPlatformLookAndFeel("Windows", "5.1");
            } else {
                // Linux has issues with the gtk look and feel themes. If linux is detected, ignore UIManager detail.
                if (!isPlatform(LINUX)) {
                    setLookAndFeel(getSystemLookAndFeelClassName());
                }
            }

            // make sure we have nice window decorations.
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);

        } catch (Exception ex) {
            System.err.println("Error loading system look and feel.\n" + ex);
        }
    }

    private static boolean isPlatform(final String platform) {
        return System.getProperties().getProperty(OS_NAME).toLowerCase().contains(platform.toLowerCase());
    }

    private static void setRestrictedPlatformLookAndFeel(final String osName, final String osVersion) throws Exception {
        String originalOsName = System.getProperties().getProperty(OS_NAME);
        String originalOsVersion = System.getProperties().getProperty(OS_VERSION);
        System.setProperty(OS_NAME, osName);
        System.setProperty(OS_VERSION, osVersion);
        setLookAndFeel(getSystemLookAndFeelClassName());
        System.setProperty(OS_NAME, originalOsName);
        System.setProperty(OS_VERSION, originalOsVersion);
    }
}