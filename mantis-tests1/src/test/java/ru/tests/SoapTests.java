package ru.tests;

import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.Issue;
import ru.models.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{

   @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
       Set<Project> projects = app.soap().getProjects();
       System.out.println(projects.size());
       for (Project project : projects){
           System.out.println(project.getName());
       }
   }

   //@Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
       Set<Project> projects = app.soap().getProjects();
       Issue issue = new Issue().withSummary("Test issue")
               .withDescription("Test issue description").withProject(projects.iterator().next());
       Issue created =  app.soap().addIssue(issue);
       assertEquals(issue.getSummary(), created.getSummary());
   }

   @Test
    public void testGetIssues() throws RemoteException, ServiceException, MalformedURLException {
       skipIfNotFixed(24); //этот баг в статусе закрыт
       //skipIfNotFixed(25); //этот баг в статусе открыт - тест проигорируется

       Project project = app.soap().getProjects().iterator().next(); //выбрали первый попавшийся проект
       Set<Issue> issues = app.soap().getIssues(project); //получили список багов для выбранного проекта
       System.out.println(issues.size());
       for (Issue issue : issues){
           System.out.println("Проверка работы функции  skipIfNotFixed");
       }
   }

}
