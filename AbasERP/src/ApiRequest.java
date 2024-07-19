import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequest {
    private final HttpClient client = HttpClient.newHttpClient();
    ;

    public void sendPostRequest(String uri) throws IOException, InterruptedException {
        String requestBody = "{"
                + "\"name\": \"Apple MacBook Pro 16\","
                + "\"data\": {"
                + "    \"year\": 2019,"
                + "    \"price\": 1849.99,"
                + "    \"CPU model\": \"Intel Core i9\","
                + "    \"Hard disk size\": \"1 TB\""
                + "}"
                + "}";
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("POST Response Status Code: " + postResponse.statusCode());
        System.out.println("POST Response Body: " + postResponse.body());
        writeResultToFile("POST Response Status Code: " + postResponse.statusCode() + "POST Response Body: " + postResponse.body(), "postRequest.txt");
    }

    public void sendGetRequest(String uri) throws IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("GET Response Status Code: " + getResponse.statusCode());
        System.out.println("GET Response Body: " + getResponse.body());
        writeResultToFile("GET Response Status Code: " + getResponse.statusCode() + "GET Response Body: " + getResponse.body(), "getRequest.txt");
    }

    private void writeResultToFile(String result, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(result);
            writer.newLine();
        } catch (IOException e) {

        }
    }


}