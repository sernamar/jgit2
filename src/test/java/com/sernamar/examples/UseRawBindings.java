package com.sernamar.examples;

import java.io.File;
import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static com.sernamar.jgit2.bindings.git2_1.git_libgit2_init;
import static com.sernamar.jgit2.bindings.git2_1.git_libgit2_shutdown;
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

            // Free git repository
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
