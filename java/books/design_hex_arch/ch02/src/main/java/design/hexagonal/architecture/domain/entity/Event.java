package design.hexagonal.architecture.domain.entity;

import design.hexagonal.architecture.domain.exception.NotImplementedYet;

import design.hexagonal.architecture.domain.vo.Activity;
import design.hexagonal.architecture.domain.vo.EventId;
import design.hexagonal.architecture.domain.vo.Protocol;

import java.time.OffsetDateTime;

public class Event implements Comparable<Event> {

    private EventId id;
    private OffsetDateTime timestamp;
    String protocol;
    Activity activity;

    public Event(OffsetDateTime timestamp, EventId id, Protocol protocol, Activity activity) {
        throw new NotImplementedYet();
    }

    @Override
    public int compareTo(Event o) {
        throw new NotImplementedYet();
    }

}
