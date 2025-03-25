package com.sernamar.jgit2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.sernamar.jgit2.Global.gitLibgit2Init;
import static org.junit.jupiter.api.Assertions.*;

class OidTest {

    @BeforeAll
    static void beforeAll() {
        gitLibgit2Init();
    }

    @Test
    void oidShortenRoundTrip() {
        String oid = "fc2f1be150833453be26f10d2a26cd2f967b9297";
        long minLength = 7;
        GitOidShorten shortener = Oid.gitOidShortenNew(minLength);
        int length = Oid.gitOidShortenAdd(shortener, oid);
        String shortened = oid.substring(0, length);
        assertEquals("fc2f1be", shortened);
        Oid.gitOidShortenFree(shortener);
    }
}