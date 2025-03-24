package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

public final class GitOid {
    private final MemorySegment segment;

    public GitOid(MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }
}
