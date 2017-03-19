package ru.tests;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import ru.appmanager.ApplicationManager;
import ru.models.Issue;

import java.io.IOException;
import java.util.Set;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    public Set<Issue> getIssues() throws IOException {
        //отправляем запрос на получение списка всех баг репортов и хотим получить ответ в формате json
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");//получаем список багов
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }



    public Issue findIssueById(int id) throws IOException {
        Issue findedIssue = new Issue();
        //отправляем запрос на получение списка всех баг репортов и хотим получить ответ в формате json
        String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json",id)))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");//получаем список багов
        Set<Issue> myIssue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
        System.out.println(myIssue);
        myIssue.stream().
                findFirst().map((i) -> findedIssue
                .withId(i.getId()).withSubject(i.getSubject())
                .withDescription(i.getDescription()).withState(i.getState()));
        return findedIssue;

    }

    public Executor getExecutor() {
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==","");
    }


    boolean isIssueOpen(int issueId) throws IOException {
        Issue findedIssue = findIssueById(issueId);
        if (findedIssue.getState() == 3){ //3 - значит что статус "Closed"
            return false;
        } else return true;

    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }

    }


}
