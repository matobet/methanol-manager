package cz.muni.fi.pa165.methanolmanager.frontend.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.gwtplatform.dispatch.rest.client.RestApplicationPath;

public class RestModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bindConstant().annotatedWith(RestApplicationPath.class).to("/api");
    }
}
