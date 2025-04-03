package com.sernamar.examples;

import com.sernamar.jgit2.bindings.git_error;
import com.sernamar.jgit2.bindings.git_oid;

import java.io.File;
import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import static com.sernamar.jgit2.bindings.git2_1.*;
import static com.sernamar.jgit2.bindings.git2_2.C_POINTER;
import static com.sernamar.jgit2.bindings.git2_2.git_repository_init;

public class UseRawBindings {
    public static String path = "/tmp/repo";

    public static void main(String[] args) {
        // Init libgit2
        int ret = git_libgit2_init();
        if (ret < 0) {
            System.err.println("Failed to initialize libgit2: " + git_error.message(git_error_last()));
            return;
        }

        // Create git* variables
        MemorySegment repo = MemorySegment.NULL;
        MemorySegment signature = MemorySegment.NULL;
        MemorySegment index = MemorySegment.NULL;
        MemorySegment tree = MemorySegment.NULL;

        try (Arena arena = Arena.ofConfined()) {

            // Create a new repository
            MemorySegment repoSegment = arena.allocate(C_POINTER);
            MemorySegment pathSegment = arena.allocateFrom(path);
            ret = git_repository_init(repoSegment, pathSegment, 0);
            if (ret < 0) {
                throw new Exception("Failed to create repository: " + git_error.message(git_error_last()));
            }
            repo = repoSegment.get(C_POINTER, 0);
            System.out.println("Repository created: " + repo);

            // Create a default signature
            MemorySegment signatureSegment = arena.allocate(C_POINTER);
            ret = git_signature_default(signatureSegment, repo);
            if (ret < 0) {
                throw new Exception("Failed to create default signature: " + git_error.message(git_error_last()));
            }
            signature = signatureSegment.get(C_POINTER, 0);
            System.out.println("Default signature created: " + signature);

            // Open the repository index
            MemorySegment indexSegment = arena.allocate(C_POINTER);
            ret = git_repository_index(indexSegment, repo);
            if (ret < 0) {
                throw new Exception("Failed to open repository index: " + git_error.message(git_error_last()));
            }
            index = indexSegment.get(C_POINTER, 0);
            System.out.println("Repository index opened: " + index);

            // Write initial tree
            MemorySegment treeId = git_oid.allocate(arena);
            ret = git_index_write_tree(treeId, index);
            if (ret < 0) {
                throw new Exception("Failed to write tree: " + git_error.message(git_error_last()));
            }
            System.out.println("Initial tree written: " + treeId);

            // Look up the tree
            MemorySegment treeSegment = arena.allocate(C_POINTER);
            ret = git_tree_lookup(treeSegment, repo, treeId);
            if (ret < 0) {
                throw new Exception("Failed to look up tree: " + git_error.message(git_error_last()));
            }
            tree = treeSegment.get(C_POINTER, 0);
            System.out.println("Tree looked up: " + tree);

            // Create the initial commit
            MemoryLayout[] variadicLayouts = new MemoryLayout[0]; // No parents for the initial commit
            Arrays.fill(variadicLayouts, C_POINTER);
            git_commit_create_v invoker = git_commit_create_v.makeInvoker(variadicLayouts);

            MemorySegment commitId = git_oid.allocate(arena);
            MemorySegment updateRefSegment = arena.allocateFrom("HEAD");
            MemorySegment messageEncodingSegment = MemorySegment.NULL;
            MemorySegment messageSegment = arena.allocateFrom("Initial commit");
            ret = invoker.apply(
                    commitId,
                    repo,
                    updateRefSegment,
                    signature,
                    signature,
                    messageEncodingSegment,
                    messageSegment,
                    tree,
                    0
            );
            if (ret < 0) {
                System.err.println("Failed to create commit: " + git_error.message(git_error_last()));
                return;
            }
            System.out.println("Initial commit created: " + commitId);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Free git* objects
            git_tree_free(tree);
            git_index_free(index);
            git_signature_free(signature);
            git_repository_free(repo);

            // Delete the repository directory
            try (Stream<Path> paths = Files.walk(Paths.get(path))) {
                paths.sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                System.err.println("Error deleting repo directory: " + e.getMessage());
            }
        }

        // Shutdown libgit2
        ret = git_libgit2_shutdown();
        if (ret < 0) {
            System.err.println("Failed to shutdown libgit2: " + git_error.message(git_error_last()));
        }
    }
}
