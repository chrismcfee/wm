package api.model;

public class GoogleMapsGeocodeResult {
    private String address;
    private GoogleMapsStatus status;
    private LatLng location;

    public GoogleMapsGeocodeResult(String address, LatLng location, GoogleMapsStatus status) {
        this.address = address;
        this.location = location;
        this.status = status;
    }

    public GoogleMapsStatus getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public LatLng getLocation() {
        return location;
    }
}
