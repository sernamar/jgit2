package com.sernamar.jgit2;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class GitTimeTest {
    private static final long TIME = 1633072800L;
    private static final int OFFSET = 120;
    private static final byte SIGN = '+';

    @Test
    void toInstant() {
        GitTime gitTime = new GitTime(TIME, OFFSET, SIGN);
        Instant instant = gitTime.toInstant();
        assertEquals(TIME, instant.getEpochSecond());
    }

    @Test
    void toOffsetDateTime() {
        GitTime gitTime = new GitTime(TIME, OFFSET, SIGN);
        var offsetDateTime = gitTime.toOffsetDateTime();
        assertEquals(TIME, offsetDateTime.toInstant().getEpochSecond());
        assertEquals(OFFSET, offsetDateTime.getOffset().getTotalSeconds() / 60);
        assertEquals(SIGN, offsetDateTime.getOffset().getId().charAt(0));
    }
}