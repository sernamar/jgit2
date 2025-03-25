package com.sernamar.jgit2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.sernamar.jgit2.Global.gitLibgit2Init;
import static org.junit.jupiter.api.Assertions.*;

class CommonTest {

    @BeforeAll
    static void beforeAll() {
        gitLibgit2Init();
    }

    @Test
    void gitLibgit2Version() {
        String version = Common.gitLibgit2Version();
        assertNotNull(version);
        assertTrue(version.matches("\\d+\\.\\d+\\.\\d+"));
    }
}