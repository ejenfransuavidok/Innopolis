package week2.lesson4.proxies;

public class FTPDownloader implements IDataDownloader {
    @Override
    public void download(String url) {
        System.out.println("I finished from " + url);
    }

    @Override
    public String getFileContent(String url, int fileSize) {
        return "this is file content from " + url;
    }

    @Override
    public void log() {

    }
}
