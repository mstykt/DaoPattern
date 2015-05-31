/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mesut.daopattern;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mesut
 */
@Repository
public class OffersDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource jdbcTemplate) {
        this.jdbcTemplate =new JdbcTemplate(jdbcTemplate);
    }
    
    public List<Offer> getOffers(){
        return jdbcTemplate.query("select * from offers", new RowMapper<Offer>() {

            public Offer mapRow(ResultSet rs, int i) throws SQLException {
                Offer offer=new Offer();
                offer.setId(rs.getInt("id"));
                offer.setName(rs.getString("name"));
                offer.setEmail(rs.getString("email"));
                offer.setText(rs.getString("text"));
                return offer;
            }
        });
    }
}
