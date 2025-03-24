package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

public final class GitOidShorten {
    private final MemorySegment segment;

    public GitOidShorten(MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }
}
