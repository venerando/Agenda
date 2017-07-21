package com.geovanni.agenda;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by geovanni on 20/07/17.
 */

public class WebClient {

    public String post (String json){
        try {
            URL url = new URL("https://www.caelum.com.br/mobile");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-type", "application/json");

            connection.setRequestProperty("Acept","application/json");

            PrintStream output = new PrintStream(connection.getOutputStream());


            connection.setDoOutput(true);

            output.println(json);

            connection.connect();

            Scanner scanner = new Scanner (connection.getInputStream());

            String resposta = scanner.next();

            return resposta;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
