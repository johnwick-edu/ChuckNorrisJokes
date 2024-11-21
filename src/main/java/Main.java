import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            // Define the API endpoint
            String apiUrl = "https://api.chucknorris.io/jokes/random";


            // Create an HttpClient to hit the endpoint
            HttpClient client = HttpClient.newHttpClient();

            // Build a HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();



            boolean anotherOne = true;

            while(anotherOne){
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


                // if request was successful
                if(response.statusCode()==200){
                    // read the response
                    JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

                    String value = jsonObject.get("value").getAsString();

                    System.out.println(value);

                }else{
                    System.out.println("Failed to fetch Joke. Http Error Code: "+response.statusCode());
                    anotherOne = false;
                    break;
                }


                System.out.print("Would you like another one?");
                String anotherJoke = scanner.nextLine();
                if(anotherJoke.equalsIgnoreCase("no")){
                    anotherOne = false;
                }

            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}
