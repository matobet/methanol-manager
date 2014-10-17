package cz.muni.fi.pa165.methanolmanager.frontend.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import com.mvp4g.client.presenter.PresenterInterface;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.BottleService;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.StoreService;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import javax.inject.Inject;
import java.util.List;

@Presenter(view = MainView.class)
public class MainPresenter extends BasePresenter<MainPresenter.ViewDef, MainEventBus> {
    public interface ViewDef {
        void setBottles(List<String> bottles);
        void setErrorMessage(String message);
    }
    private final BottleService bottleService;

    private final StoreService storeService;

    @Inject
    public MainPresenter(BottleService bottleService, StoreService storeService) {
        this.bottleService = bottleService;
        this.storeService = storeService;
    }

    public void onApplicationLoad() {
        storeService.getStores(new MethodCallback<List<String>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                view.setErrorMessage(exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<String> response) {
                view.setBottles(response);
            }
        });
    }
}
