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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mesut
 */
@Repository
public class OffersDAO {
    //private JdbcTemplate jdbc;
    private NamedParameterJdbcTemplate jdbc;
    
    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc =new NamedParameterJdbcTemplate(jdbc);
    }
    
    /**
     * Gets List of offers with jdbc template<br/>
     * <strong>sql query:</strong> <code>select * from offers</code>
     * @return List<Offer> 
     * @see List
     * @see Offer
     */
    public List<Offer> findOffers(){
        return jdbc.query("select * from offers", new RowMapper<Offer>() {

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
    
    public Offer findById(int id){
        MapSqlParameterSource params=new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbc.queryForObject("select * from offers where id= :id", params, new RowMapper<Offer>() {

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
    
    public boolean deleteById(int id){
        MapSqlParameterSource params=new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbc.update("delete from offers where id= :id", params)==1;
    }
    
    public boolean addOffer(Offer offer){
        BeanPropertySqlParameterSource params=new BeanPropertySqlParameterSource(offer);
        return jdbc.update("insert into offers (name, email, text) values(:name, :email, :text)", params)==1;
    }
    
    public boolean updateOffer(Offer offer){
        BeanPropertySqlParameterSource params=new BeanPropertySqlParameterSource(offer);
        return jdbc.update("update offers set name=:name, email=:email, text=:text where id=:id", params)==1;
    }
    
    public int []createOffers(List<Offer> offers){
        SqlParameterSource[] params=SqlParameterSourceUtils.createBatch(offers.toArray());
        return jdbc.batchUpdate("insert into offers (name, email, text) values(:name, :email, :text)", params);
    }
}
