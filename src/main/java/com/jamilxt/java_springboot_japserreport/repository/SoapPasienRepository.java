package com.jamilxt.java_springboot_japserreport.repository;

import com.jamilxt.java_springboot_japserreport.model.SoapPasienModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SoapPasienRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private class   SoapPasienMapper implements RowMapper<SoapPasienModel>{
        @Override
        public     SoapPasienModel mapRow(ResultSet rs, int rowNum) throws SQLException{
            SoapPasienModel soapPasienModel =   new SoapPasienModel();
            soapPasienModel.setNoreg(rs.getString("noreg"));
            soapPasienModel.setSkriningk1(rs.getString("skrining_k1"));
            soapPasienModel.setSkriningk2(rs.getString("skrining_k2"));
            soapPasienModel.setSkriningk3(rs.getString("skrining_k3"));
            soapPasienModel.setSkriningk3(rs.getString("skrining_k3"));
            soapPasienModel.setSkriningk4(rs.getString("skrining_k4"));
            soapPasienModel.setSkriningk5(rs.getString("skrining_k5"));
            soapPasienModel.setSkriningk6(rs.getString("skrining_k6"));
            soapPasienModel.setKdBagian(rs.getString("kd_bagian"));
            soapPasienModel.setSkriningRj(rs.getString("skrining_rj"));
            soapPasienModel.setB1(rs.getString("skrining_b1"));
            soapPasienModel.setB2(rs.getString("skrining_b2"));
            soapPasienModel.setF1(rs.getString("skrining_f1"));
            soapPasienModel.setF2(rs.getString("skrining_f2"));
            soapPasienModel.setF3(rs.getString("skrining_f3"));
            soapPasienModel.setF4(rs.getString("skrining_f4"));
            soapPasienModel.setR1(rs.getString("skrining_r1"));
            soapPasienModel.setR2(rs.getString("skrining_r2"));
            soapPasienModel.setR3(rs.getString("skrining_r3"));
            soapPasienModel.setR4(rs.getString("skrining_r4"));
            return  soapPasienModel;
        }
    }


    public  SoapPasienModel findByNoreg(String noreg){
        String sql = "SELECT noreg, kd_bagian, skrining_k1, skrining_k2, skrining_k3, skrining_k4, skrining_k5, skrining_k6, " +
                "skrining_f1, skrining_f2,skrining_f3,skrining_f4, skrining_b1, skrining_b2, skrining_rj,  skrining_r1, " +
                "skrining_r2, skrining_r3, skrining_r3, skrining_r4 FROM vicore_rme.dcppt_soap_pasien WHERE noreg=?";
        return  jdbcTemplate.queryForObject(sql,  new SoapPasienMapper(), noreg );
    }

}
