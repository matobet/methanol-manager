package cz.muni.fi.pa165.methanolmanager.frontend.client.gin;

import com.google.gwt.inject.client.Ginjector;
import cz.muni.fi.pa165.methanolmanager.frontend.client.widget.bottle.BottleListWidget;

public interface MyGinjector extends Ginjector {
    BottleListWidget getBottleListWidget();
}
