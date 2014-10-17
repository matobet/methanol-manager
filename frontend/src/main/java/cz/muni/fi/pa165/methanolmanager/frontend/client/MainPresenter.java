package cz.muni.fi.pa165.methanolmanager.frontend.client;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import com.mvp4g.client.presenter.PresenterInterface;

@Presenter(view = MainView.class)
public class MainPresenter extends BasePresenter<MainPresenter.ViewDef, MainEventBus> {
    public interface ViewDef {
    }
}
