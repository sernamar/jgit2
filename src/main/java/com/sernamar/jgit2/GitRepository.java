package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

public final class GitRepository {
    private final MemorySegment segment;

    public GitRepository(MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }
}
