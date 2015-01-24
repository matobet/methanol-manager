package cz.muni.fi.pa165.methanolmanager.frontend.client.bottles;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import cz.muni.fi.pa165.methanolmanager.frontend.client.i18n.ApplicationMessages;
import cz.muni.fi.pa165.methanolmanager.frontend.client.rest.BottleService;
import cz.muni.fi.pa165.methanolmanager.frontend.client.utils.NotificationUtils;
import cz.muni.fi.pa165.methanolmanager.service.dto.BottleDto;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Collection;

public class BottleStamper implements HasHandlers {

    private final EventBus eventBus;
    private final BottleService bottleService;
    private final ApplicationMessages messages;

    private int outstandingStampRequests;

    @Inject
    BottleStamper(EventBus eventBus, BottleService bottleService, ApplicationMessages messages) {
        this.eventBus = eventBus;
        this.bottleService = bottleService;
        this.messages = messages;
    }

    public void stampBottles(Collection<BottleDto> bottlesToStamp) {
        outstandingStampRequests = 0;
        for (final BottleDto bottle : bottlesToStamp){
            if (bottle.getStampDate() == null) {
                outstandingStampRequests++;
                bottleService.stampBottle(bottle.getId(), new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        NotificationUtils.error(messages.stampBottleError(bottle.getName(), exception.getLocalizedMessage()));
                        stampRequestDone();
                    }

                    @Override
                    public void onSuccess(Method method, Void response) {
                        NotificationUtils.info(messages.bottleStamped(bottle.getName()));
                        stampRequestDone();
                    }
                });
            }
        }
    }

    private void stampRequestDone() {
        if (--outstandingStampRequests == 0) {
            AllBottlesStampedEvent.fire(this);
        }
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }
}
