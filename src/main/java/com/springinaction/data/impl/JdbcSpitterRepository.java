package com.springinaction.data.impl;

import com.springinaction.common.Spitter;
import com.springinaction.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by zjjfly on 2016/12/30.
 */
@Repository
public class JdbcSpitterRepository implements SpitterRepository {

    private JdbcOperations jdbcOperations;

//    @Autowired
//    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    private static final String INSERT_SPITTER = "insert into Spitter (username, password, first_name, last_name, email) values (?, ?, ?, ?, ?)";

    private static final String INSERT_SPITTER_NAMED_PARA = "insert into Spitter (username, password, first_name, last_name, email) values (:username, :password, :firstname, :lastname, :email)";

    private static final String SELECT_SPITTER = "select id, username, first_name, last_name, email from Spitter";

    private static final String SELECT_SPITTER_BY_USERNAME = SELECT_SPITTER + " where username=?";

    @Autowired
    public JdbcSpitterRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Spitter save(Spitter spitter) {
        jdbcOperations.update(INSERT_SPITTER, spitter.getUsername(), spitter.getPassword(), spitter.getFirstName(), spitter.getLastName(), spitter.getEmail());
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("username",spitter.getUsername());
//        map.put("password",spitter.getPassword());
//        map.put("firstname",spitter.getFirstName());
//        map.put("lastname",spitter.getLastName());
//        map.put("email",spitter.getEmail());
//        namedParameterJdbcOperations.update(INSERT_SPITTER_NAMED_PARA,map);
        return spitter;
    }

    @Override
    public Spitter findByUsername(String username) {
        Spitter spitter = jdbcOperations.queryForObject(SELECT_SPITTER_BY_USERNAME, (rs, num) -> {
            return new Spitter(rs.getLong("id"), rs.getString("username"), null, rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
        },username);
        return spitter;
    }
}
