package net.square.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URL;

/**
 * Copyright © SquareCode 2018
 * created on: 13.12.2018 / 17:50
 * Project: AntiReach
 */
public class EthernetAPI {

    public static String getCountry(final InetSocketAddress ip) {
        try {
            final URL url = new URL("http://ip-api.com/json/" + ip.getHostName());
            final BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
            final StringBuilder entirePage = new StringBuilder();
            String inputLine;
            while ((inputLine = stream.readLine()) != null) {
                entirePage.append(inputLine);
            }
            stream.close();
            if (!entirePage.toString().contains("\"country\":\"")) {
                return null;
            }
            return entirePage.toString().split("\"country\":\"")[1].split("\",")[0];
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCity(final InetSocketAddress ip) {
        try {
            final URL url = new URL("http://ip-api.com/json/" + ip.getHostName());
            final BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
            final StringBuilder entirePage = new StringBuilder();
            String inputLine;
            while ((inputLine = stream.readLine()) != null) {
                entirePage.append(inputLine);
            }
            stream.close();
            if (!entirePage.toString().contains("\"city\":\"")) {
                return null;
            }
            return entirePage.toString().split("\"city\":\"")[1].split("\",")[0];
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getISP(final InetSocketAddress ip) {
        try {
            final URL url = new URL("http://ip-api.com/json/" + ip.getHostName());
            final BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
            final StringBuilder entirePage = new StringBuilder();
            String inputLine;
            while ((inputLine = stream.readLine()) != null) {
                entirePage.append(inputLine);
            }
            stream.close();
            if (!entirePage.toString().contains("\"isp\":\"")) {
                return null;
            }
            return entirePage.toString().split("\"isp\":\"")[1].split("\",")[0];
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTimezone(final InetSocketAddress ip) {
        try {
            final URL url = new URL("http://ip-api.com/json/" + ip.getHostName());
            final BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
            final StringBuilder entirePage = new StringBuilder();
            String inputLine;
            while ((inputLine = stream.readLine()) != null) {
                entirePage.append(inputLine);
            }
            stream.close();
            if (!entirePage.toString().contains("\"timezone\":\"")) {
                return null;
            }
            return entirePage.toString().split("\"timezone\":\"")[1].split("\",")[0];
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
