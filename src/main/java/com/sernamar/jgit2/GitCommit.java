package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

public final class GitCommit {
    private final MemorySegment segment;

    public GitCommit(MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }
}
