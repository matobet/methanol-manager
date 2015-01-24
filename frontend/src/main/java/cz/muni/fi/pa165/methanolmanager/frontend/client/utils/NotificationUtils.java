package cz.muni.fi.pa165.methanolmanager.frontend.client.utils;

import org.gwtbootstrap3.extras.growl.client.ui.GrowlHelper;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlOptions;

import static org.gwtbootstrap3.extras.growl.client.ui.Growl.growl;

public class NotificationUtils {
    public static void info(String message) {
        growl(message);
    }

    public static void error(String message) {
        GrowlOptions options = GrowlHelper.getNewOptions();
        options.setDangerType();
        growl(message, options);
    }
}
