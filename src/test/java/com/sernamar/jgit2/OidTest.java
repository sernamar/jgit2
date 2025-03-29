package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.sernamar.jgit2.Global.gitLibgit2Init;
import static com.sernamar.jgit2.Global.gitLibgit2Shutdown;
import static com.sernamar.jgit2.bindings.git2.GIT_OID_SHA1_HEXSIZE;
import static org.junit.jupiter.api.Assertions.*;

class OidTest {

    @BeforeAll
    static void beforeAll() {
        gitLibgit2Init();
    }

    @AfterAll
    static void afterAll() {
        gitLibgit2Shutdown();
    }

    @Test
    void gitOidToString() throws GitException {
        String oidString = "fc2f1be150833453be26f10d2a26cd2f967b9297";
        GitOid oid = Oid.gitOidFromString(oidString);
        long length = GIT_OID_SHA1_HEXSIZE() + 1; // 40 + 1
        String oidString2 = Oid.gitOidToString(oid, length);
        assertNotNull(oidString2);
        assertEquals(oidString, oidString2);
        String oidString3 = Oid.gitOidToString(oid, length * 2);
        assertNotNull(oidString3);
        assertEquals(oidString, oidString3);
        assertThrows(IllegalArgumentException.class, () -> Oid.gitOidToString(oid, 33));
    }

    @Test
    void oidStringRoundTrip() throws GitException {
        String oidString = "fc2f1be150833453be26f10d2a26cd2f967b9297";
        GitOid oid = Oid.gitOidFromString(oidString);
        assertNotNull(oid);
        long length = GIT_OID_SHA1_HEXSIZE() + 1; // 40 + 1
        assertEquals(oidString, Oid.gitOidToString(oid, length));
    }

    @Test
    void oidShortenRoundTrip() throws GitException {
        String oid = "fc2f1be150833453be26f10d2a26cd2f967b9297";
        long minLength = 7;
        try (GitOidShorten shorten = Oid.gitOidShortenNew(minLength)) {
            int length = Oid.gitOidShortenAdd(shorten, oid);
            String shortened = oid.substring(0, length);
            assertEquals("fc2f1be", shortened);
        }
    }
}