package cz.muni.fi.pa165.methanolmanager.frontend.client.gin;

import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;
import cz.muni.fi.pa165.methanolmanager.frontend.client.ApplicationPlaces;
import cz.muni.fi.pa165.methanolmanager.frontend.client.main.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.main.ApplicationView;

public class MainModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule.Builder().placeManager(DefaultPlaceManager.class).build());

        bindConstant().annotatedWith(DefaultPlace.class).to(ApplicationPlaces.MAIN_PLACE);
        bindConstant().annotatedWith(ErrorPlace.class).to(ApplicationPlaces.MAIN_PLACE);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(ApplicationPlaces.MAIN_PLACE);

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.ViewDef.class, ApplicationView.class, ApplicationPresenter.Proxy.class);
    }
}
