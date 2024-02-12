package otr.elib.framework.adapter.in.file.parser;

import java.util.List;

public record ChapterParsingResponse(
    String outputDirName,
    List<SubtitleFile> subtitles
) {}
