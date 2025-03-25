package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_repository_free;

public final class GitRepository implements AutoCloseable {
    private MemorySegment segment;

    GitRepository(MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }

    @Override
    public void close() {
        if (segment != MemorySegment.NULL) {
            git_repository_free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
