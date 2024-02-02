package design.hexagonal.architecture.domain.policy;

import design.hexagonal.architecture.domain.entity.Event;
import design.hexagonal.architecture.domain.vo.Activity;
import design.hexagonal.architecture.domain.vo.Protocol;
import design.hexagonal.architecture.domain.vo.EventId;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexEventParser implements EventParser {

    @Override
    public Event parseEvent(String event) {
        final String regex = "(\\\"[^\\\"]+\\\")|\\S+";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(event);
        List<String> fields = new ArrayList<>();

        while (matcher.find()) {
            fields.add(matcher.group(0));
        }

        OffsetDateTime timestamp = LocalDateTime
                .parse(matcher.group(0), this.formatter)
                .atOffset(ZoneOffset.UTC);
        EventId id = EventId.of(matcher.group(1));
        Protocol protocol = Protocol.valueOf(matcher.group(2));
        Activity activity = new Activity(matcher.group(3), matcher.group(5));

        return new Event(timestamp, id, protocol, activity);
    }

}
