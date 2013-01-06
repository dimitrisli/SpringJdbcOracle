package com.dimitrisli.springJdbcOracle.dao.impl;

import com.dimitrisli.springJdbcOracle.dao.LocationDao;
import com.dimitrisli.springJdbcOracle.model.Location;
import com.dimitrisli.springJdbcOracle.orm.LocationRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

@Repository("locationDao")
public class LocationDaoImpl implements LocationDao {

    private static final String CREATE_SQL = "INSERT INTO LOCATIONS( LOCATION_ID, STREET_ADDRESS, POSTAL_CODE, CITY, " +
                                             "STATE_PROVINCE, COUNTRY_ID) " +
                                             "VALUES (LOCATIONS_SEQ.NEXTVAL, :streetAddress, :postalCode, :city, " +
                                             ":stateProvince, :countryId)";

    private static final String GET_ALL_SQL = "SELECT LOCATION_ID, STREET_ADDRESS, POSTAL_CODE, CITY, STATE_PROVINCE, COUNTRY_ID " +
                                              "FROM LOCATIONS";

    private static final String GET_SQL = "SELECT LOCATION_ID, STREET_ADDRESS, POSTAL_CODE, CITY, STATE_PROVINCE, COUNTRY_ID " +
                                          "FROM LOCATIONS WHERE LOCATION_ID = :locationId";

    private static final String DELETE_SQL = "DELETE LOCATIONS WHERE LOCATION_ID = :locationId";

    private static final String UPDATE_SQL = "UPDATE LOCATIONS SET STREET_ADDRESS = :streetAddress, POSTAL_CODE=:postalCode, " +
                                            "CITY = :city, STATE_PROVINCE = :stateProvince, COUNTRY_ID = :countryId " +
                                            "WHERE LOCATION_ID = :locationId";

    @Inject private NamedParameterJdbcOperations jdbcTemplate;
    @Inject private LocationRowMapper locationRowMapper;

    @Override
    public void createLocation(Location location) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("streetAddress", location.getStreetAddress())
                .addValue("postalCode", location.getPostalCode())
                .addValue("city", location.getCity())
                .addValue("stateProvince", location.getStateProvince())
                .addValue("countryId", location.getCountryId());
        jdbcTemplate.update(CREATE_SQL, params);
    }

    @Override
    public List<Location> getLocations() {
        return jdbcTemplate.query(GET_ALL_SQL, new HashMap<String, Object>(), locationRowMapper);
    }

    @Override
    public Location getLocation(Long locationId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("locationId", locationId);
        List<Location> locations = jdbcTemplate.query(GET_SQL, params, locationRowMapper);
        return locations.isEmpty()?null:locations.get(0);
    }

    @Override
    public void updateLocation(Location location) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("locationId", location.getLocationId())
                .addValue("streetAddress", location.getStreetAddress())
                .addValue("postalCode", location.getPostalCode())
                .addValue("city", location.getCity())
                .addValue("stateProvince", location.getStateProvince())
                .addValue("countryId", location.getCountryId());
        jdbcTemplate.update(UPDATE_SQL, params);
    }

    @Override
    public void deleteLocation(Long locationId) {
        jdbcTemplate.update(DELETE_SQL, new MapSqlParameterSource("locationId",locationId));
    }
}
