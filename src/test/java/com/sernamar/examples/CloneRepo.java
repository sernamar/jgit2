package com.sernamar.examples;

import com.sernamar.jgit2.Clone;
import com.sernamar.jgit2.GitRepository;

import static com.sernamar.jgit2.Global.gitLibgit2Init;
import static com.sernamar.jgit2.Global.gitLibgit2Shutdown;

public class CloneRepo {
    public static void main(String[] args) {
        String url = "https://github.com/sernamar/jgit2";
        String localPath = "/tmp/repo";

        gitLibgit2Init();

        try (GitRepository repo = Clone.gitClone(url, localPath)) {
            System.out.println("Repository cloned successfully!");
            System.out.println("Repository: " + repo);
        } catch (Exception e) {
            System.err.println("Error cloning repository: " + e.getMessage());
        }

        gitLibgit2Shutdown();
    }
}
