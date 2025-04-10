package com.sernamar.jgit2;

import com.sernamar.jgit2.utils.GitException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static com.sernamar.jgit2.Refs.gitReferenceNameToId;

public class TestUtils {

    static final String NAME = "name";
    static final String EMAIL = "name@mail.com";
    static final String MESSAGE = "Initial commit";

    static void createRepoWithInitialCommit(String path) throws GitException {
        try (GitRepository repo = Repository.gitRepositoryInit(path);
             GitIndex index = Repository.gitRepositoryIndex(repo)) {
            GitOid treeId = Index.gitIndexWriteTree(index);
            try (GitTree tree = Tree.gitTreeLookup(repo, treeId);
                 GitSignature signature = Signature.gitSignatureNow(NAME, EMAIL)) {
                Commit.gitCommitCreateV(
                        repo,
                        "HEAD",
                        signature,
                        signature,
                        null,
                        MESSAGE,
                        tree);
            }
        }
    }

    static void create3Branches(String path) throws GitException {
        try (GitRepository repo = Repository.gitRepositoryOpen(path)) {
            GitOid commitId = gitReferenceNameToId(repo, "HEAD");
            assert commitId != null;
            GitCommit commit = Commit.gitCommitLookup(repo, commitId);
            Branch.gitBranchCreate(repo, "branch1", commit);
            Branch.gitBranchCreate(repo, "branch2", commit);
            Branch.gitBranchCreate(repo, "branch3", commit);
        }
    }

    public static void deleteRepoDirectory(String path) {
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
