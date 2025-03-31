package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_tree_free;

public final class GitTree implements AutoCloseable {
    private MemorySegment segment;
    private final boolean owned;

    GitTree(MemorySegment segment, boolean owned) {
        this.segment = segment;
        this.owned = owned;
    }

    MemorySegment segment() {
        return segment;
    }

    @Override
    public void close() {
        if (owned && segment != MemorySegment.NULL) {
            git_tree_free(segment);
            segment = MemorySegment.NULL;
        }
    }
}
