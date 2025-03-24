package com.sernamar.jgit2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalTest {

    @Test
    void gitLibgit2Init() {
        assertDoesNotThrow(() -> {
            int ret = Global.gitLibgit2Init();
            assertTrue(ret > 0);
        });
    }

    @Test
    void gitLibgit2Shutdown() {
        assertDoesNotThrow(() -> {
            int ret = Global.gitLibgit2Shutdown();
            assertTrue(ret >= 0);
        });
    }

    @Test
    void gitLibgit2InitShutdown() {
        assertDoesNotThrow(() -> {
            int ret = Global.gitLibgit2Init();
            assertEquals(1, ret);
            ret = Global.gitLibgit2Init();
            assertEquals(2, ret);
            ret = Global.gitLibgit2Shutdown();
            assertEquals(1, ret);
            ret = Global.gitLibgit2Shutdown();
            assertEquals(0, ret);
        });
        // This will throw an exception because the library has already been shut down.
        assertThrows(RuntimeException.class, Global::gitLibgit2Shutdown);
    }
}