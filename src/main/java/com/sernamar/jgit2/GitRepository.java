package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_repository_free;

public final class GitRepository extends OpaqueDataType {

    GitRepository(MemorySegment segment, boolean owned) {
        super(segment, owned);
    }

    @Override
    void free(MemorySegment segment) {
        git_repository_free(segment);
    }
}
