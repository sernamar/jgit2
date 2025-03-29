package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.sernamar.jgit2.Global.gitLibgit2Init;
import static com.sernamar.jgit2.Global.gitLibgit2Shutdown;
import static org.junit.jupiter.api.Assertions.*;

class CommonTest {

    @BeforeAll
    static void beforeAll() {
        gitLibgit2Init();
    }

    @AfterAll
    static void afterAll() {
        gitLibgit2Shutdown();
    }

    @Test
    void gitLibgit2Version() throws GitException {
        String version = Common.gitLibgit2Version();
        assertNotNull(version);
        assertTrue(version.matches("\\d+\\.\\d+\\.\\d+"));
    }
}