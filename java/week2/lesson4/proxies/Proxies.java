package week2.lesson4.proxies;

import java.lang.reflect.Proxy;
import java.util.Scanner;

public class Proxies {

    static IDataDownloader downloader;

    public static void main(String[] args) {
        downloader = new FTPDownloader();
        downloader.download("ya.ru");

        DownloaderProxy downloaderHandler = new DownloaderProxy(downloader);

        IDataDownloader proxyObject =
                (IDataDownloader)Proxy.newProxyInstance(
                        FTPDownloader.class.getClassLoader(),
                        new Class<?>[]{IDataDownloader.class, IDataUploader.class},/*FTPDownloader.class.getInterfaces(),*/
                        downloaderHandler
                );


        Scanner scanner = new Scanner(System.in);
        System.out.println("We have proxy object! Use?");
        String dession = scanner.next();
        if("y".equals(dession))
            downloader = proxyObject;

        downloader = proxyObject;

        downloader.download("ya.ru");

        System.out.println(
                downloader.getFileContent("google.com", 100)
        );
    }
}
