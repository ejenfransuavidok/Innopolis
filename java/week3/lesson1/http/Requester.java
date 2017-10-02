package week3.lesson1.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.Buffer;


public class Requester {
    public static void main(String[] args) {
        //sendGet("http://www.ivan1.ru");
        sendPost("http://selfsolve.apple.com/wcResult", "sn=12wekfjsldkfjaslkdfj");
    }

    public static void sendGet(String address){
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String tempStr = null;
                while((tempStr = reader.readLine()) != null){
                    stringBuilder.append(tempStr);
                }
                reader.close();
                System.out.println(stringBuilder.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendPost(String address, String data){
        URL url = null;
        try{
            url = new URL(address);
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            DataOutputStream dos =
                    new DataOutputStream(connection.getOutputStream());
            dos.writeBytes(data);
            dos.flush();
            dos.close();

            int responseCode = connection.getResponseCode();
            if(/*responseCode == 200*/true){
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String tempStr = null;
                while((tempStr = reader.readLine()) != null){
                    stringBuilder.append(tempStr);
                }
                reader.close();
                System.out.println(stringBuilder.toString());
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
