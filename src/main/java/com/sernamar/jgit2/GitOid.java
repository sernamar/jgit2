package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

// NOTE: This class does not implement `AutoCloseable` because `git_oid` is a fixed-size structure that does not
// allocate memory dynamically. Since `git_oid` is always embedded in other structures or passed by value, it does not
// require explicit de-allocation, making `AutoCloseable` unnecessary.
public final class GitOid {
    private final MemorySegment segment;

    GitOid(MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }
}
