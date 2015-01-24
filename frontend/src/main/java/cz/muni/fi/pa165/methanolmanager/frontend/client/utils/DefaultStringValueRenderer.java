package cz.muni.fi.pa165.methanolmanager.frontend.client.utils;

import com.google.gwt.text.shared.AbstractRenderer;

public class DefaultStringValueRenderer extends AbstractRenderer<String> {

    private final String defaultValue;

    public DefaultStringValueRenderer(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String render(String object) {
        if (object == null) {
            return defaultValue;
        }
        return object;
    }
}
