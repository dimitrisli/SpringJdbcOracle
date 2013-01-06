package com.dimitrisli.springJdbcOracle.dao.impl;

import com.dimitrisli.springJdbcOracle.dao.LocationDao;
import com.dimitrisli.springJdbcOracle.model.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/context/applicationContext.xml")
@TransactionConfiguration(transactionManager = "jdbcTransactionManager", defaultRollback = true)
@Transactional
public class LocationDaoTest {

    @Inject private LocationDao locationDao;

    @Test
    public void testSelectAllLocations(){
    List<Location> locations = locationDao.getLocations();
    assertThat(locations.size(), is(23));
    }

    @Test
    public void testSelectOneLocation(){
      Location location = locationDao.getLocation(1000L);
      assertNotNull("test entry not found", location);
    }

    @Test
    public void testDeleteLocation(){
        assertNotNull("entry for test should be there", locationDao.getLocation(1000L));
        locationDao.deleteLocation(1000L);
        assertNull("entry wasn't successfully deleted", locationDao.getLocation(1000L));
    }

    @Test
    public void testInsertLocation(){
        Location location = new Location(1000L,"test","11111","athens","athens","IT");
        int sizeBeforeInsert = locationDao.getLocations().size();
        locationDao.createLocation(location);
        assertThat(locationDao.getLocations().size(),is(sizeBeforeInsert + 1));
    }

    @Test
    public void testUpdateLocation(){
        Location newLocation = new Location(1000L,"test","11111","athens","athens","IT");
        locationDao.updateLocation(newLocation);
        Location changedLocation = locationDao.getLocation(1000L);
        assertThat(changedLocation.getStreetAddress(), is("test"));
    }

}
