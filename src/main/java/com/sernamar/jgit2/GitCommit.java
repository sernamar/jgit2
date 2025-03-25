package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_commit_free;

public final class GitCommit implements AutoCloseable {
    private MemorySegment segment;

    GitCommit(MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }

    @Override
    public void close() {
        if (segment != MemorySegment.NULL) {
            git_commit_free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
