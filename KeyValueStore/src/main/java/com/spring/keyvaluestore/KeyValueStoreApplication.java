package com.spring.keyvaluestore;

import com.spring.keyvaluestore.model.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class KeyValueStoreApplication {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public static void main(String[] args) {
        SpringApplication.run(KeyValueStoreApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        String sql = "SELECT * FROM KV_STORE";
//
//        List<KeyValue> kvList = jdbcTemplate.query(sql,
//                BeanPropertyRowMapper.newInstance(KeyValue.class));
//
//        for(KeyValue item : kvList){
//            System.out.println(item.toString());
//        }
//    }
}
