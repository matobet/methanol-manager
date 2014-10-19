package cz.muni.fi.pa165.methanolmanager.frontend.client.gin;

import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;
import cz.muni.fi.pa165.methanolmanager.frontend.client.ApplicationPlaces;
import cz.muni.fi.pa165.methanolmanager.frontend.client.main.MainPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.main.MainView;

public class MainModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule(DefaultPlaceManager.class));

        bindConstant().annotatedWith(DefaultPlace.class).to(ApplicationPlaces.MAIN_PLACE);
        bindConstant().annotatedWith(ErrorPlace.class).to(ApplicationPlaces.MAIN_PLACE);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(ApplicationPlaces.MAIN_PLACE);

        bindPresenter(MainPresenter.class, MainPresenter.ViewDef.class, MainView.class, MainPresenter.Proxy.class);
    }
}
