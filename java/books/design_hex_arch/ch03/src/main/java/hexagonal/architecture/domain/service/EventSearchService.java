package hexagonal.architecture.domain.service;

import hexagonal.architecture.domain.entity.Event;
import hexagonal.architecture.domain.vo.ParsePolicyType;

import java.util.ArrayList;
import java.util.List;

public class EventSearchService {

    public List<Event> retrieveEvents(
        List<String> unparsedEvents,
        ParsePolicyType policyType
    ) {
        List<Event> parsedEvents = new ArrayList<>();

        unparsedEvents.forEach(event -> {
            parsedEvents.add(
                Event.parsedEvent(event, policyType)
            );
        });

        return parsedEvents;
    }

}
