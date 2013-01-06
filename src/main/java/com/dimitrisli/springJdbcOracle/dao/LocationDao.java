package com.dimitrisli.springJdbcOracle.dao;

import com.dimitrisli.springJdbcOracle.model.Location;

import java.util.List;

public interface LocationDao {

    public void createLocation(Location location);
    public List<Location> getLocations();
    public Location getLocation(Long locationId);
    public void updateLocation(Location location);
    public void deleteLocation(Long locationId);

}
