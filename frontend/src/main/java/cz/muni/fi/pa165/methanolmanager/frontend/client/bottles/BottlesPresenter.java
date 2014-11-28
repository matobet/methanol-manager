/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.methanolmanager.frontend.client.bottles;

/**
 *
 * @author petr
 */

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import cz.muni.fi.pa165.methanolmanager.frontend.client.application.ApplicationPresenter;
import cz.muni.fi.pa165.methanolmanager.frontend.client.place.NameTokens;

import javax.inject.Inject;

public class BottlesPresenter extends Presenter<BottlesPresenter.ViewDef, BottlesPresenter.Proxy> {

    public interface ViewDef extends View {
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.BOTTLES)
    public interface Proxy extends ProxyPlace<BottlesPresenter> {
    }

    @Inject
    public BottlesPresenter(EventBus eventBus, ViewDef view, Proxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT);
    }
}
