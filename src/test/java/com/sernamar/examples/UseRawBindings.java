package com.sernamar.examples;

import com.sernamar.jgit2.bindings.git_oid;

import java.io.File;
import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            System.err.println("Failed to initialize libgit2: " + ret);
            return;
        }

        try (Arena arena = Arena.ofConfined()) {
            // Create a new repository
            MemorySegment repoSegment = arena.allocate(C_POINTER);
            MemorySegment pathSegment = arena.allocateFrom(path);
            ret = git_repository_init(repoSegment, pathSegment, 0);
            if (ret < 0) {
                System.err.println("Failed to create repository: " + ret);
                return;
            }
            MemorySegment repo = repoSegment.get(C_POINTER, 0);
            System.out.println("Repository created: " + repo);

            // Create a default signature
            MemorySegment signatureSegment = arena.allocate(C_POINTER);
            ret = git_signature_default(signatureSegment, repo);
            if (ret < 0) {
                System.err.println("Failed to create default signature: " + ret);
                git_repository_free(repo);
                return;
            }
            MemorySegment signature = signatureSegment.get(C_POINTER, 0);
            System.out.println("Default signature created: " + signature);

            // Open the repository index
            MemorySegment indexSegment = arena.allocate(C_POINTER);
            ret = git_repository_index(indexSegment, repo);
            if (ret < 0) {
                System.err.println("Failed to open repository index: " + ret);
                git_signature_free(signature);
                git_repository_free(repo);
                return;
            }
            MemorySegment index = indexSegment.get(C_POINTER, 0);
            System.out.println("Repository index opened: " + index);

            // Write initial tree
            MemorySegment treeId = git_oid.allocate(arena);
            ret = git_index_write_tree(treeId, index);
            if (ret < 0) {
                System.err.println("Failed to write tree: " + ret);
                git_index_free(index);
                git_signature_free(signature);
                git_repository_free(repo);
                return;
            }
            System.out.println("Initial tree written: " + treeId);

            // Look up the tree
            MemorySegment treeSegment = arena.allocate(C_POINTER);
            ret = git_tree_lookup(treeSegment, repo, treeId);
            if (ret < 0) {
                System.err.println("Failed to look up tree: " + ret);
                git_index_free(index);
                git_signature_free(signature);
                git_repository_free(repo);
                return;
            }
            MemorySegment tree = treeSegment.get(C_POINTER, 0);
            System.out.println("Tree looked up: " + tree);

            // Free git* objects
            git_tree_free(tree);
            git_index_free(index);
            git_signature_free(signature);
            git_repository_free(repo);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
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
            System.err.println("Failed to shutdown libgit2: " + ret);
        }
    }
}
