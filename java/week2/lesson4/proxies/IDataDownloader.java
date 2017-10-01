package week2.lesson4.proxies;

public interface IDataDownloader {
    public void download(String url);
    String getFileContent(String url, int fileSize);
    void log();
}
