package ru.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;


public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("4a669b26eb9102acc155e0769d030c88fa4c3ad5");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("KateTuz1993", "Kate_tests")).commits();
        for (RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());

        }
    }

}
