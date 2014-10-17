package cz.muni.fi.pa165.methanolmanager.frontend.client;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.event.EventBusWithLookup;

@Events(startPresenter = MainPresenter.class)
public interface MainEventBus extends EventBusWithLookup {
    @Start
    @Event(handlers = MainPresenter.class)
    void applicationLoad();
}
