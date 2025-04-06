package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_revwalk_free;

public class GitRevwalk extends OpaqueDataType {

    GitRevwalk(MemorySegment segment, boolean owned) {
        super(segment, owned);
    }

    @Override
    void free(MemorySegment segment) {
        git_revwalk_free(segment);
    }
}
