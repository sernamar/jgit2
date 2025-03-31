package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_commit_free;

public final class GitCommit implements AutoCloseable {
    private MemorySegment segment;
    private final boolean owned;

    GitCommit(MemorySegment segment, boolean owned) {
        this.segment = segment;
        this.owned = owned;
    }

    MemorySegment segment() {
        return segment;
    }

    @Override
    public void close() {
        if (owned && segment != MemorySegment.NULL) {
            git_commit_free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
