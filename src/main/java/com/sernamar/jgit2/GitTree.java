package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_tree_free;

public final class GitTree extends OpaqueDataType {

    GitTree(MemorySegment segment, boolean owned) {
        super(segment, owned);
    }

    @Override
    void free(MemorySegment segment) {
        git_tree_free(segment);
    }
}
