package com.sernamar.jgit2;

import java.lang.foreign.MemorySegment;

public final class GitOid extends OpaqueType {
    public GitOid(MemorySegment segment) {
        super(segment);
    }
}
