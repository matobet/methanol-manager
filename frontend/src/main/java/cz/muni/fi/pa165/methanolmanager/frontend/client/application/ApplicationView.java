package cz.muni.fi.pa165.methanolmanager.frontend.client.application;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.ViewDef {

    interface Binder extends UiBinder<ScrollPanel, ApplicationView> {
    }

    @UiField
    SimplePanel contentContainer;

    @Inject
    public ApplicationView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == ApplicationPresenter.MAIN_CONTENT) {
            contentContainer.setWidget(content);
        } else {
            super.setInSlot(slot, content);
        }
    }
}