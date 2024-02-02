package design.hexagonal.architecture.domain.entity;

import design.hexagonal.architecture.domain.exception.NotImplementedYet;

import design.hexagonal.architecture.domain.vo.Activity;
import design.hexagonal.architecture.domain.vo.EventId;

import java.time.OffsetDateTime;

public class Event implements Comparable<Event> {

    private EventId id;
    private OffsetDateTime timestamp;
    String protocol;
    Activity activity;

    @Override
    public int compareTo(Event o) {
        throw new NotImplementedYet();
    }

}
