package com.sernamar.jgit2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignatureTest {

    private static final String NAME = "name";
    private static final String EMAIL = "name@mail.com";

    @BeforeAll
    static void beforeAll() {
        Global.gitLibgit2Init();
    }

    @AfterAll
    static void afterAll() {
        Global.gitLibgit2Shutdown();
    }

    @Test
    void testSignature() {
        try (GitSignature signature = Signature.gitSignatureNow(NAME, EMAIL)) {
            assertNotNull(signature);
            assertEquals(NAME, Signature.gitSignatureName(signature));
            assertEquals(EMAIL, Signature.gitSignatureEmail(signature));
            assertNotNull(Signature.gitSignatureTime(signature));
        }
    }
}