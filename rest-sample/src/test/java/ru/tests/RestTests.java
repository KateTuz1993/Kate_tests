package ru.tests;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import jdk.nashorn.internal.ir.RuntimeNode;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

    private Executor executor;

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        System.out.println(oldIssues.size());
        Issue newIssue = new Issue().withSubject("test_Kate").withDescription("good_test");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);

    }


    private int createIssue(Issue newIssue) throws IOException {
        String json =  getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject",newIssue.getSubject()),
                          new BasicNameValuePair("description",newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json); //анализируем строчку полученную в ответе
        int issue_id = parsed.getAsJsonObject().get("issue_id").getAsInt();//id созданного баг репорта;
        return issue_id;
    }


}
