package com.dimitrisli.springJdbcOracle.model;

public class Location {

    private Long locationId;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private String countryId;

    public Location(Long locationId, String streetAddress, String postalCode, String city, String stateProvince, String countryId) {
        this.locationId = locationId;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.countryId = countryId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public String getCountryId() {
        return countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!locationId.equals(location.locationId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return locationId.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Location");
        sb.append("{locationId=").append(locationId);
        sb.append(", streetAddress='").append(streetAddress).append('\'');
        sb.append(", postalCode='").append(postalCode).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", stateProvince='").append(stateProvince).append('\'');
        sb.append(", countryId=").append(countryId);
        sb.append('}');
        return sb.toString();
    }
}
