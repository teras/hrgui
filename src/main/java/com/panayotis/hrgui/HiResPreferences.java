package com.panayotis.hrgui;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public interface HiResPreferences {
    default float scaleFactor() {
        return -1;
    }

    default boolean storePrefs(float scaleFactor) {
        return false;
    }

    static HiResPreferences getDefault() {
        try (InputStream istream = HiResPreferences.class.getResourceAsStream("hires.resource")) {
            if (istream != null) {
                String line = new BufferedReader(new InputStreamReader(istream)).readLine();
                if (line != null) {
                    return (HiResPreferences) Class.forName(line.trim()).newInstance();
                }
            }
        } catch (Exception exception) {
            System.err.println("Unable to load HiRes preferences: " + exception);
        }
        // Couldn't find any preferences definition
        return new HiResPreferences() {
        };
    }
}
