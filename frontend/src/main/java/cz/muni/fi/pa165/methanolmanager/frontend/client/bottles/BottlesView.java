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

import com.google.gwt.uibinder.client.UiBinder;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.Row;

public class BottlesView extends ViewImpl implements BottlesPresenter.ViewDef {

    interface Binder extends UiBinder<Row, BottlesView> {
    }

    @Inject
    public BottlesView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }
}