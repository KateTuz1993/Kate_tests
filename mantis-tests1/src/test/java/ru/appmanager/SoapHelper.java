package ru.appmanager;


import biz.futureware.mantis.rpc.soap.client.*;
import ru.models.Issue;
import ru.models.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private ApplicationManager app;

    public SoapHelper(ApplicationManager app){

        this.app = app;
    }

    //метод для установления соединения
    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("mantis.soap")));
    }

    //для получения множества багов для проекта
    public Set<Issue> getIssues(Project project) throws MalformedURLException, RemoteException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData[] issues = mc
                .mc_project_get_issues("administrator", "root", BigInteger.valueOf(project.getId()), BigInteger.valueOf(1),BigInteger.valueOf(4)); //получить список багов для определенного проекта
        return Arrays.asList(issues).stream()
                .map((p) -> new Issue().withId(p.getId().intValue()).withSummary(p.getSummary())
                        .withDescription(p.getDescription()).withStatus(p.getStatus().getName())).collect(Collectors.toSet());
    }

    //для получения множества проектов
    public Set<Project> getProjects() throws MalformedURLException, RemoteException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc
                .mc_projects_get_user_accessible("administrator", "root"); //получить список проектов, к которым пользователь имеет доступ
        return Arrays.asList(projects).stream()
                .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    //для регистрации нового обращения (бага)
    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect(); //открываем соединение
        String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));//запрашиваем категории (для создания баг репорта)
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData); //id созданного баг репорта
        IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
        return new Issue().withId(createdIssueData.getId().intValue())
                .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                          .withName(createdIssueData.getProject().getName()));

    }
}
