package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_oid_shorten_free;

public final class GitOidShorten implements AutoCloseable {
    private MemorySegment segment;

    GitOidShorten(MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }

    @Override
    public void close() {
        if (segment != MemorySegment.NULL) {
            git_oid_shorten_free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
