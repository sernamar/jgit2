package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

     @BeforeAll
    static void beforeAll() {
        Global.gitLibgit2Init();
    }

    @AfterAll
    static void afterAll() {
        Global.gitLibgit2Shutdown();
    }

    @Test
    void gitMessagePrettify() throws GitException {
         // Message with trailing whitespace
        String message = "Hello, World!  ";
        String expected = "Hello, World!\n";
        assertEquals(expected, Message.gitMessagePrettify(message));
        assertEquals(expected, Message.gitMessagePrettify(message, true, '#'));

        // Message with comment
        message = "Hello, World!  \n# Comment\n";
        assertEquals(expected, Message.gitMessagePrettify(message));
        assertEquals(expected, Message.gitMessagePrettify(message, true, '#'));

    }
}