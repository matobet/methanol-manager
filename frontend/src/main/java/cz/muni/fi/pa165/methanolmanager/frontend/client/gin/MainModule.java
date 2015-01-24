package cz.muni.fi.pa165.methanolmanager.frontend.client.gin;

import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationView;
import cz.muni.fi.pa165.methanolmanager.frontend.client.place.NameTokens;

public class MainModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new DefaultModule.Builder().placeManager(DefaultPlaceManager.class).build());
        install(new PresenterModule());

        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.STORES);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.STORES);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.LOGIN);
    }
}
