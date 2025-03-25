package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

// NOTE: this class does not implement the AutoCloseable interface,
// as there is not a git_oid_free() function in `libgit2`
public final class GitOid {
    private final MemorySegment segment;

    GitOid(MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }
}
