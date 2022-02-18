package com.example.balancechecker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIConnector {

    private  final String urlString;

    public APIConnector(String urlString) {
        this.urlString = urlString;
    }

    public JSONArray getJSONArray(String query) {

        try {
            URL url = new URL(urlString + query);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            //Check if connect is made
            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {

                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                JSONParser parser = new JSONParser();

                return (JSONArray) parser.parse(String.valueOf(informationString));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONObject getJSONObject(String query) {

        try {
            URL url = new URL(urlString + query);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            //Check if connect is made
            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                JSONParser parser = new JSONParser();

                return (JSONObject) parser.parse(String.valueOf(informationString));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
