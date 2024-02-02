package hexagonal.architecture.domain.entity;

import hexagonal.architecture.domain.policy.RegexEventParser;
import hexagonal.architecture.domain.policy.SplitEventParser;
import hexagonal.architecture.domain.vo.Activity;
import hexagonal.architecture.domain.vo.EventId;
import hexagonal.architecture.domain.vo.ParsePolicyType;
import hexagonal.architecture.domain.vo.Protocol;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Event implements Comparable<Event> {

    private final OffsetDateTime timestamp;
    private final EventId id;
    private final Protocol protocol;
    private final Activity activity;

    public Event(
        OffsetDateTime timestamp,
        EventId id,
        Protocol protocol,
        Activity activity
    ) {
        this.timestamp = timestamp;
        this.id = id;
        this.protocol = protocol;
        this.activity = activity;
    }

    public static Event parsedEvent(String unparsedEvent, ParsePolicyType policy) {
        return switch (policy) {
            case REGEX -> new RegexEventParser().parseEvent(unparsedEvent);
            case SPLIT -> new SplitEventParser().parseEvent(unparsedEvent);
            default -> throw new IllegalArgumentException("Unsupported Policy Type");
        };
    }

    @Override
    public int compareTo(Event event) {
        return this.timestamp.compareTo(event.timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return (
                event.timestamp.equals(this.timestamp)
                && event.id.equals(this.id)
                && event.protocol.equals(this.protocol)
                && event.activity.equals(this.activity)
            );
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, id, protocol, activity) + 31;
    }

}
