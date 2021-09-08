package com.springinaction.data.impl;

import com.springinaction.common.DuplicateSpittleException;
import com.springinaction.common.Spittle;
import com.springinaction.data.SpittleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zjjfly on 2016/12/29.
 */
@Repository
public class JdbcSpittleRepository implements SpittleRepository {

    private JdbcOperations jdbcOperations;

    private static final String INSERT_SPITTER = "insert into Spittle (message, created_at, latitude, longitude) values (?, ?, ?, ?)";
    private static final String SELECT_SPITTLE = "select sp.id, sp.message, sp.created_at, sp.latitude, sp.longitude from Spittle sp where 0 < 1";
    private static final String SELECT_SPITTLE_BY_ID = SELECT_SPITTLE + " and sp.id=?";
    private static final String SELECT_RECENT_SPITTLES = SELECT_SPITTLE + " order by sp.created_at desc limit ?";

    @Autowired
    public JdbcSpittleRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        List<Spittle> spittles = jdbcOperations.query(SELECT_RECENT_SPITTLES, new Object[]{count}, (rs, num) -> {
            Spittle spittle = new Spittle();
            spittle.setId(rs.getLong("id"));
            spittle.setMessage(rs.getString("message"));
            spittle.setTime(rs.getTime("created_at"));
            spittle.setLatitude(rs.getDouble("latitude"));
            spittle.setLongitude(rs.getDouble("longitude"));
            return spittle;
        });
        return spittles;
    }

    @Override
    public Spittle findOne(long id) {
        Spittle re = jdbcOperations.queryForObject(SELECT_SPITTLE_BY_ID, (rs, num) -> {
            Spittle spittle = new Spittle();
            spittle.setId(rs.getLong("id"));
            spittle.setMessage(rs.getString("message"));
            spittle.setTime(rs.getTime("created_at"));
            spittle.setLatitude(rs.getDouble("latitude"));
            spittle.setLongitude(rs.getDouble("longitude"));
            return spittle;
        }, id);
        return re;
    }

    @Override
    public Spittle save(Spittle spittle) throws DuplicateSpittleException {
        if (StringUtils.isEmpty(spittle.getMessage())){
            throw new DuplicateSpittleException();
        }
        int update = jdbcOperations.update(INSERT_SPITTER, spittle.getMessage(), spittle.getTime(), spittle.getLatitude(), spittle.getLongitude());
        spittle.setId((long) update);
        return spittle;
    }
}
