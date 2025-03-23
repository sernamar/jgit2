package com.sernamar.jgit2;

import com.sernamar.jgit2.bindings.git_oid;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.nio.file.Paths;

import static com.sernamar.jgit2.bindings.git2_1.git_libgit2_init;
import static com.sernamar.jgit2.bindings.git2_1.git_libgit2_shutdown;
import static com.sernamar.jgit2.bindings.git2_2.*;

public class Scratch {
    public static void main(String[] args) {
        // Initialize libgit2
        if (git_libgit2_init() < 0) {
            throw new RuntimeException("Failed to initialize libgit2");
        }

        String path = Paths.get("").toAbsolutePath().toString();
        try (Arena arena = Arena.ofConfined()) {
            // Open repository
            MemorySegment repoSegment = arena.allocate(C_POINTER);
            MemorySegment pathSegment = arena.allocateFrom(path);
            int ret = git_repository_open(repoSegment, pathSegment);
            if (ret < 0) {
                throw new RuntimeException("Failed to open repository");
            }
            MemorySegment repoPtr = repoSegment.get(C_POINTER, 0);

            // Get OID of main branch (refs/heads/main)
            String refName = "refs/heads/main";
            MemorySegment oidSegment = git_oid.allocate(arena);
            MemorySegment refNameSegment = arena.allocateFrom(refName);
            ret = git_reference_name_to_id(oidSegment, repoPtr, refNameSegment);
            if (ret < 0) {
                throw new RuntimeException("Failed to lookup ref");
            }

            // Get commit
            MemorySegment commitSegment = arena.allocate(C_POINTER);
            ret = git_commit_lookup(commitSegment, repoPtr, oidSegment);
            if (ret < 0) {
                throw new RuntimeException("Failed to lookup commit");
            }
            MemorySegment commitPtr = commitSegment.get(C_POINTER, 0);

            // Get commit message
            MemorySegment messageSegment = git_commit_message(commitPtr);
            String message = messageSegment.getString(0);
            System.out.println(message);
        }

        // Shutdown libgit2
        if (git_libgit2_shutdown() < 0) {
            throw new RuntimeException("Failed to shutdown libgit2");
        }
    }
}

