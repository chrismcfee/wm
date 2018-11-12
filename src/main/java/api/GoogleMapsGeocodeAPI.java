package api;

import api.model.GoogleMapsGeocodeResult;
import api.model.GoogleMapsStatus;
import api.model.LatLng;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.ReadContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleMapsGeocodeAPI extends GoogleMapsAPI {
    private static final String path = "maps/api/geocode/json";

    // if we were doing this properly,
    public static GoogleMapsGeocodeResult geocode(String address) {
        GoogleMapsGeocodeResult gr;

        try {
            String encodedAddress = URLEncoder.encode(address, "UTF-8");
            String params = String.format("address=%s", encodedAddress); // could create abstractions here
            URL url = new URL(String.format("%s%s?%s", baseurl, path, params));

            InputStream json = url.openStream();
            ReadContext ctx = JsonPath.parse(json);

            double lat = ctx.read("$.results[0].geometry.location.lat");
            double lng = ctx.read("$.results[0].geometry.location.lng");

            gr = new GoogleMapsGeocodeResult(address, new LatLng(lat, lng), GoogleMapsStatus.FOUND);
        } catch (IOException | JsonPathException e) {
            gr = new GoogleMapsGeocodeResult(address, null, GoogleMapsStatus.NOT_FOUND);
        }

        return gr;
    }
}
