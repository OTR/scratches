package design.hexagonal.architecture.domain.policy;

import design.hexagonal.architecture.domain.entity.Event;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public sealed interface EventParser
        permits RegexEventParser, SplitEventParser {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd HH:mm:ss.SSS"
    ).withZone(ZoneId.of("UTC"));

    Event parseEvent(String event);

}
