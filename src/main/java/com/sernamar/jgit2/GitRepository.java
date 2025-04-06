package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.OpaqueDataType;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_repository_free;

public final class GitRepository extends OpaqueDataType {

    GitRepository(MemorySegment segment, boolean owned) {
        super(segment, owned);
    }

    @Override
    protected void free(MemorySegment segment) {
        git_repository_free(segment);
    }
}
