/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.go.lipi.informatika.alboom.dashboard.manager;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import id.go.lipi.informatika.alboom.dashboard.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service("userManager")
public class UserManager {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUser(String username, String password) {
        String query = "select u.*, r.id as role_id, r.name as role_name from user u, role r where u.role=r.id and u.username=? and u.password=?";
        String passwordmd5 = getHash(password, "MD5");
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{username, passwordmd5}, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int i) throws SQLException {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFullName(rs.getString("full_name"));
                    user.setRoleId(rs.getLong("role_id"));
                    user.setRoleName(rs.getString("role_name"));
                    user.setStatus(rs.getInt("status"));
                    return user;
                }
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUsers() {
        String query = "select * from user u";
        try {
            return jdbcTemplate.query(query, new Object[]{}, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int i) throws SQLException {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setFullName(rs.getString("full_name"));
                    user.setAddress(rs.getString("address"));
                    user.setEmail(rs.getString("email"));
                    user.setGender(rs.getString("gender"));
                    user.setPostalCode(rs.getString("postal_code"));
                    user.setProfession(rs.getString("profession"));
                    user.setEducation(rs.getString("education"));
                    user.setStatus(rs.getInt("status"));
                    user.setCreateAt(rs.getTimestamp("create_at"));
                    return user;
                }
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<User> getUsers(User userSession) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

}
