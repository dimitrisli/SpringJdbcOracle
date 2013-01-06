package com.dimitrisli.springJdbcOracle.orm;

import com.dimitrisli.springJdbcOracle.model.Location;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LocationRowMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        return  new Location(rs.getLong("LOCATION_ID"),
                             rs.getString("STREET_ADDRESS"),
                             rs.getString("POSTAL_CODE"),
                             rs.getString("CITY"),
                             rs.getString("STATE_PROVINCE"),
                             rs.getString("COUNTRY_ID"));
    }
}
