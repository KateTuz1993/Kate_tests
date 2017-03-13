package ru.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.info;

public class HttpSession {
    private CloseableHttpClient httpclient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app){
        this.app = app;
        //создание новой сесси для работы по протоколу http. объект, который будет отправлять запросы на сервер
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public boolean login(String username, String password) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php"); //создание post запроса
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params)); //устанавливаем параметры запроса post
        CloseableHttpResponse response = httpclient.execute(post); //отправка запроса
        String body = getTextFrom(response); //получаем текст ответа от сервера
        //return body.contains(String.format("<span class=\"label hidden-xs label-default arrowed\">%s</span>", username));
        JOptionPane.showMessageDialog(null, body);
        return body.contains(String.format("<span class=\"user-info\">%s</span>",username)); //проверка, действительно ли зашел пользователь
        //return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
    }

    private String getTextFrom(CloseableHttpResponse response) throws IOException {
        try{
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }

    }

    //метод для проверки, какой пользователь сейчас залогинен
    public boolean isLoggedInAs(String username) throws IOException {
        HttpGet get =  new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
        CloseableHttpResponse response = httpclient.execute(get);
        String body = getTextFrom(response);
        //return body.contains(String.format("<span class=\"label hidden-xs label-default arrowed\">%s</span>", username));
        return body.contains(String.format("<span class=\"user-info\">%s</span>",username));
        //return body.contains(String.format("<span class=\"user-info\">%s</span>", username));



    }
}
