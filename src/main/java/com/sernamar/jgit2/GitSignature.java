package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_signature;

import java.lang.foreign.MemorySegment;

import static com.sernamar.jgit2.bindings.git2_1.git_signature_free;

public final class GitSignature extends OpaqueDataType {

    GitSignature(MemorySegment segment, boolean owned) {
        super(segment, owned);
    }

    public String name() {
        return git_signature.name(this.segment()).getString(0);
    }

    public String email() {
        return git_signature.email(this.segment()).getString(0);
    }

    public GitTime when() {
        MemorySegment whenSegment = git_signature.when(this.segment());
        return new GitTime(whenSegment);
    }

    @Override
    void free(MemorySegment segment) {
        git_signature_free(segment);
    }
}
