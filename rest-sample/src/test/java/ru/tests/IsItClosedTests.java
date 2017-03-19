package ru.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class IsItClosedTests extends TestBase{

    @Test
    public void isBugClosedTest() throws IOException {
        skipIfNotFixed(1);
        System.out.println("Test done");
    }
}
