package cz.muni.fi.pa165.methanolmanager.frontend.client.widget.bottle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.dispatch.rest.shared.RestDispatch;
import com.gwtplatform.mvp.client.ViewImpl;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.BottleService;

import java.util.List;

public class BottleListWidget extends Composite {
    interface ViewUiBinder extends UiBinder<HTMLPanel, BottleListWidget> {
    }

    @UiField
    ListBox listBox;

    @Inject
    public BottleListWidget(ViewUiBinder uiBinder, RestDispatch restDispatch, BottleService bottleService) {
        initWidget(uiBinder.createAndBindUi(this));

        restDispatch.execute(bottleService.getBottles(), new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("fail!");
            }

            @Override
            public void onSuccess(List<String> result) {
                setBottles(result);
            }
        });
    }

    public void setBottles(List<String> bottles) {
        for (String bottle : bottles) {
            listBox.addItem(bottle);
        }
    }
}