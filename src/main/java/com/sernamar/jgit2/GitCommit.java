package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_commit_free;

public final class GitCommit extends OpaqueDataType {

    GitCommit(MemorySegment segment, boolean owned) {
        super(segment, owned);
    }


    @Override
    void free(MemorySegment segment) {
        git_commit_free(segment);
    }
}
