package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_time;

import java.lang.foreign.MemorySegment;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public record GitTime(long time, int offset, byte sign) {

    public GitTime(MemorySegment segment) {
        this(
                git_time.time(segment),
                git_time.offset(segment),
                git_time.sign(segment)
        );
    }

    public Instant toInstant() {
        return Instant.ofEpochSecond(time);
    }

    public OffsetDateTime toOffsetDateTime() {
        int offsetMinutes = sign == '-' ? -offset : offset;
        ZoneOffset offsetZone = ZoneOffset.ofTotalSeconds(offsetMinutes * 60);
        return OffsetDateTime.ofInstant(toInstant(), offsetZone);
    }
}
