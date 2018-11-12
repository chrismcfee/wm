package api;

import api.model.GoogleMapsGeocodeResult;
import api.model.GoogleMapsStatus;
import api.model.LatLng;
import org.junit.Assert;
import org.junit.Test;

public class GoogleMapsGeocodeAPITest {
    @Test
    public void testGetLocation() throws Exception {
        // better to mock the api, but this is fine for development. the issue is that the test doesn't always.

        GoogleMapsGeocodeResult actual = GoogleMapsGeocodeAPI.geocode("workmarket nyc");

        GoogleMapsGeocodeResult expected = new GoogleMapsGeocodeResult("workmarket nyc",
                new LatLng(40.7534195, -73.99097650000002), GoogleMapsStatus.FOUND);

        Assert.assertEquals(expected.getStatus(), actual.getStatus());
        Assert.assertEquals(expected.getAddress(), actual.getAddress());
        Assert.assertEquals(expected.getLocation().getLat(), actual.getLocation().getLat(), 0.0001);
        Assert.assertEquals(expected.getLocation().getLng(), actual.getLocation().getLng(), 0.0001);
    }

}