package cz.muni.fi.pa165.methanolmanager.frontend.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import com.mvp4g.client.presenter.PresenterInterface;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.BottleService;
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

    @Inject
    public MainPresenter(BottleService bottleService) {
        this.bottleService = bottleService;
    }

    public void onApplicationLoad() {
        Defaults.setServiceRoot("/api");

        bottleService.getBottles(new MethodCallback<List<String>>() {
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
