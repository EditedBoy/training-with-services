package com.main.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        String resultFromFirst = makeRequest("http://localhost:8091/first", "POST", params);
        String resultFromSecond = makeRequest("http://localhost:8090/second", "GET", params);
        String resultFailed = makeRequest("http://localhost:8093/fail", "GET", params);

        System.out.println(resultFromFirst);
        System.out.println(resultFromSecond);
        System.out.println(resultFailed);

        new B();
    }

    public static String makeRequest(String url, String requestMehod, Map<String, String> requestParams) {
        String result = "BAD REQUEST";
        try {
            URL urlForGetRequest = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod(requestMehod);

            for (Map.Entry<String, String> elem : requestParams.entrySet()) {
                connection.setRequestProperty(elem.getKey(), elem.getValue());
                // connection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
            }
            int responseCode = connection.getResponseCode();

            String readLine;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                // print result
                result = response.toString();
//                GetAndPost.POSTRequest(response.toString());
            }
        } catch (IOException e) {
            /* NONE */
            // e.printStackTrace();
        }
        return result;
    }
}


class A {
    private A() {
        System.out.println("A");
    }

    static class A_inner extends A {
        A_inner() {
            System.out.println("A_inner");
        }
    }
}

class B extends A.A_inner {
    B() {
        System.out.println("B");
    }
}