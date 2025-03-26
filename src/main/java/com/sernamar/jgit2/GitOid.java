package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

// NOTE: This class does not implement `AutoCloseable` because `libgit2` does not provide a `git_oid_free()` function.
// `GitOid` represents an immutable value in the underlying C library, so it does not require manual memory management.
public final class GitOid {
    private final MemorySegment segment;

    GitOid(MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }
}
