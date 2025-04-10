package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_2.git_oid_shorten_free;

public final class GitOidShorten extends OpaqueDataType {

    GitOidShorten(MemorySegment segment, boolean owned) {
        super(segment, owned);
    }

    @Override
    void free(MemorySegment segment) {
        git_oid_shorten_free(segment);
    }
}
