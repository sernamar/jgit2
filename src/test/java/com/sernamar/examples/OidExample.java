package com.sernamar.examples;

import com.sernamar.jgit2.GitOidShorten;
import com.sernamar.jgit2.Global;
import com.sernamar.jgit2.Oid;

public class OidExample {
    public static void main(String[] args) {
        // Initialize libgit2
        Global.gitLibgit2Init();

        // OIDs to shorten
        String oid1 = "fc2f1be150833453be26f10d2a26cd2f967b9297";
        String oid2 = "e81e21fdc32c92e9a038a8b81d0ca22168b48c61";
        String oid3 = "0b5f2d2d328ef51052fa33b67985dd3c9cc602df";

        // Create a new OID shortener
        long minLength = 7;
        GitOidShorten shortener = Oid.gitOidShortenNew(minLength);

        // Add OIDs to the shortener
        int length1 = Oid.gitOidShortenAdd(shortener, oid1);
        int length2 = Oid.gitOidShortenAdd(shortener, oid2);
        int length3 = Oid.gitOidShortenAdd(shortener, oid3);

        // Print the shortened OIDs
        System.out.println("Shortened OIDs:");
        System.out.println("OID 1: " + oid1.substring(0, length1));
        System.out.println("OID 2: " + oid2.substring(0, length2));
        System.out.println("OID 3: " + oid3.substring(0, length3));

        // Free the OID shortener
        Oid.gitOidShortenFree(shortener);

        // Shutdown libgit2
        Global.gitLibgit2Shutdown();
    }
}
