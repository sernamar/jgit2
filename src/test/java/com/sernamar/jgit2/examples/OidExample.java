package com.sernamar.jgit2.examples;


import com.sernamar.jgit2.Global;
import com.sernamar.jgit2.Oid;

public class OidExample {
    public static void main(String[] args) {
        // Initialize libgit2
        Global.gitLibgit2Init();

        // OIDs to shorten
        var oid1 = "fc2f1be150833453be26f10d2a26cd2f967b9297";
        var oid2 = "e81e21fdc32c92e9a038a8b81d0ca22168b48c61";
        var oid3 = "0b5f2d2d328ef51052fa33b67985dd3c9cc602df";

        // Create a new OID shortener
        var minLength = 7;
        var shortener = Oid.gitOidShortenNew(minLength);

        // Add OIDs to the shortener
        var length1 = Oid.gitOidShortenAdd(shortener, oid1);
        var length2 = Oid.gitOidShortenAdd(shortener, oid2);
        var length3 = Oid.gitOidShortenAdd(shortener, oid3);

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
