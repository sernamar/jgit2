package com.sernamar.jgit2.examples;


import com.sernamar.jgit2.Oid;

public class OidExample {
    public static void main(String[] args) {
        var sha = "4a202b346bb0fb0db7eff3cffeb3c70babbd2045";
        var oid = Oid.gitOidFromString(sha);
        var sha2 = Oid.gitOidToString(oid, sha.length());
        System.out.println("Original SHA:   " + sha);
        System.out.println("Round-trip SHA: " + sha2);
        System.out.println("Are they equal? " + sha.equals(sha2));
    }
}
