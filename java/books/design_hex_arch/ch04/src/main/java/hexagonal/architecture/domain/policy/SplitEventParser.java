package hexagonal.architecture.domain.policy;

import hexagonal.architecture.domain.entity.Event;
import hexagonal.architecture.domain.vo.Activity;
import hexagonal.architecture.domain.vo.EventId;
import hexagonal.architecture.domain.vo.Protocol;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import java.util.Arrays;
import java.util.List;

public final class SplitEventParser implements EventParser {

    @Override
    public Event parseEvent(String event) {
        List<String> fields = Arrays.asList(event.split(" "));

        OffsetDateTime timestamp = LocalDateTime
                .parse(fields.get(0), this.formatter)
                .atOffset(ZoneOffset.UTC);
        EventId id = EventId.of(fields.get(1));
        Protocol protocol = Protocol.valueOf(fields.get(2));
        Activity activity = new Activity(fields.get(3), fields.get(5));

        return new Event(timestamp, id, protocol, activity);
    }

}
