package com.spring.keyvaluestore.DAO;

import com.spring.keyvaluestore.model.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class kvDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<KeyValue> list(){
        String sql = "SELECT * FROM KV_STORE";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(KeyValue.class));
    }

    public void delete(String Id){
        String sql  = "DELETE FROM KV_STORE WHERE ID=?";
        Object[] params = {Id};
        jdbcTemplate.update(sql, params);
    }

    public void update(KeyValue keyValue){
        String sql = "UPDATE KV_STORE SET value=?, expires_at=? WHERE id=?";
        Timestamp ts = getExpiryTimestamp();
        Object[] params = {keyValue.getValue(), ts, keyValue.getId()};
        jdbcTemplate.update(sql, params);
    }

    private static Timestamp getExpiryTimestamp() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 2);
        Timestamp ts = new Timestamp(cal.getTime().getTime());
        return ts;
    }

    public void save(KeyValue keyValue){
        String sql = "INSERT INTO KV_STORE (Id, value, expires_at) VALUES (?, ?, ?)";
        Object[] params = {keyValue.getId(), keyValue.getValue(), getExpiryTimestamp()};
        jdbcTemplate.update(sql, params);
    }

    public KeyValue get(String Id){
        String sql = "SELECT * FROM KV_STORE WHERE ID = ?";
        Object[] params = {Id};
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(KeyValue.class), params);

    }


}
