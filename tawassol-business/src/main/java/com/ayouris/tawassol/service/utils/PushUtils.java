package com.ayouris.tawassol.service.utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class PushUtils {

    private static final String API_URL = "https://fcm.googleapis.com/v1/projects/tawassol-4e2dc/messages:send";

    public static HttpURLConnection initConnection() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + getAccessToken());

        connection.setRequestProperty("Content-Type", "application/json");

        return connection;
    }

    public static void postNotification(JSONObject object) throws IOException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity<String> httpEntity = new HttpEntity(object.toString(), httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(API_URL, httpEntity, String.class);
        System.out.println(" Response : " + response);

    }


    public static void postNotification(HttpURLConnection connection, JSONObject object) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        outputStreamWriter.write(object.toString());
        outputStreamWriter.flush();

        int status = 0;
        status = connection.getResponseCode();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(connection.getInputStream()));
        switch (status) {
            case 200:
                System.out.println(" Response : " + reader.readLine());
                break;
            default:
                System.out.println("An error has occured");
                break;
        }
    }

    public static void sendNotificationToTopic(String topic, String title, String body) throws IOException, JSONException {

        HttpURLConnection connection = initConnection();

        JSONObject json = new JSONObject();
        JSONObject msg = new JSONObject();
        JSONObject notif = new JSONObject();

        json.put("message", msg);
        msg.put("topic", topic);
        msg.put("notification", notif);
        notif.put("title", title);
        notif.put("body", body);

        postNotification(connection, json);
    }

    private static String getAccessToken() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("firebase" + File.separator + "tawassol-4e2dc-firebase.json");
        GoogleCredential googleCredential = GoogleCredential
                // .fromStream(new FileInputStream("C:" + File.separator + "test" + File.separator + "firebase" + File.separator + "tawassol-4e2dc-firebase.json"))
                .fromStream(is)
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase.messaging"));
        googleCredential.refreshToken();
        String token = googleCredential.getAccessToken();
        return token;
    }

}
