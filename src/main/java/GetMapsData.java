import api.GoogleMapsGeocodeAPI;
import api.model.GoogleMapsGeocodeResult;
import api.model.GoogleMapsStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetMapsData {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(args[0]);
        final int maxRetries = 5;

        Stream<String> lines = Files.lines(path);
        List<GoogleMapsGeocodeResult> locations = lines.map(address -> getLocationWithRetries(address, maxRetries)).collect(Collectors.toList());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // flush out any logged messages
        System.err.flush();

        String json = gson.toJson(locations);
        System.out.println(json);
    }

    private static GoogleMapsGeocodeResult getLocationWithRetries(String address, int retries) {
        System.err.println("Getting location of: " + address);

        // TODO: the robust approach here would be to use use a Leaky Bucket or Token Bucket rate limiter
        try {
            Thread.sleep(20); // 50 requests per second cap
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        GoogleMapsGeocodeResult gr = GoogleMapsGeocodeAPI.geocode(address);

        if (gr.getStatus() == GoogleMapsStatus.FOUND) {
            System.err.println("Found.");
            return gr;
        } else if (retries == 0) {
            System.err.println("Not found.");
            return gr;
        } else {
            return getLocationWithRetries(address, retries - 1);
        }
    }

}
