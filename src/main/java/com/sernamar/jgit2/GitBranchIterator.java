package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_branch_iterator_free;

public final class GitBranchIterator extends OpaqueDataType {

    GitBranchIterator(MemorySegment segment, boolean owned) {
        super(segment, owned);
    }

    @Override
    void free(MemorySegment segment) {
        git_branch_iterator_free(segment);
    }
}
