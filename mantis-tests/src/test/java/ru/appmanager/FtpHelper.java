package ru.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {

    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient();
    }

    //метод загружает новый конфиг. файл, а старый переименовывает
    public void upload(File file, String target, String backup) throws IOException{
        ftp.connect(app.getProperty("ftp.host")); //устанавливаем соединение
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(backup); //удаляем предыдущую резервную копию
        ftp.rename(target, backup); //переименовываем существующий, делаем резервную копию
        ftp.enterLocalPassiveMode(); //вкл пассивный режим передачи данных
        ftp.storeFile(target, new FileInputStream(file)); //передаем данные из локального файла и сохраняем на сервере в файле target
        ftp.disconnect(); //разрываем соединение
    }

    //метод восстанавливает старый файл
    public void restore(String backup, String target) throws IOException {
        ftp.connect(app.getProperty("ftp.host")); //устанавливаем соединение
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target); //удаляем загруженный нами файл
        ftp.rename(backup, target); //восстанавливаем оригинальный файл из копии backup
        ftp.disconnect();
    }
}
