package com.flashmart.delivery.service;

public class DistanceService {

    private final double[] origin = {7.484403, 80.367822};
    public double calculateDistance( double latitude, double longitude) {
        double lat1 = this.origin[0];
        double lon1 = this.origin[1];
        double lat2 = latitude;
        double lon2 = longitude;
        double radius = 6371; // Earth's radius in kilometers

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = radius * c;

        return distance;
    }


}
