/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.go.lipi.informatika.alboom.dashboard.manager;

import id.go.lipi.informatika.alboom.dashboard.model.Menu;
import id.go.lipi.informatika.alboom.dashboard.model.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service("roleManager")
public class RoleManager {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Menu> getMenuParents(String parent, Map<String, Menu> others) {
        List<Menu> menus = new ArrayList<Menu>();
        if(others.get(parent) == null) {
            Menu       menu  = getMenuParent(parent);
            while (menu != null) {
                menus.add(menu);
                if(menu.getParent() != null && others.get(menu.getParent()) == null) menu = getMenuParent(menu.getParent());
                else menu = null;
            }
        }
        return menus;
    }

    private Menu getMenuParent(String parent) {
        String query = "SELECT * FROM menu WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{parent}, new RowMapper<Menu>() {
                @Override
                public Menu mapRow(ResultSet rs, int i) throws SQLException {
                    Menu menu = new Menu();
                    menu.setId(rs.getString("id"));
                    menu.setName(rs.getString("name"));
                    menu.setLink(rs.getString("link"));
                    menu.setParent(rs.getString("parent"));
                    menu.setStatus(rs.getInt("status"));
                    menu.setIcon(rs.getString("icon"));
                    return menu;
                }
            });
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<Menu> getMenus(Integer id) {
        String query = "SELECT m.* FROM menu m, role_menu r WHERE r.menu_id=m.id AND r.role_id=?";
        return jdbcTemplate.query(query, new Object[]{id}, new RowMapper() {
            @Override
            public Menu mapRow(ResultSet rs, int i) throws SQLException {
                Menu menu = new Menu();
                menu.setId(rs.getString("id"));
                menu.setName(rs.getString("name"));
                menu.setLink(rs.getString("link"));
                menu.setParent(rs.getString("parent"));
                menu.setStatus(rs.getInt("status"));
                menu.setIcon(rs.getString("icon"));
                return menu;
            }
        });
    }

    public Role getRole(Integer id) {
        String query = "SELECT * FROM role WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Role>() {
                @Override
                public Role mapRow(ResultSet rs, int i) throws SQLException {
                    Role role = new Role();
                    role.setId(rs.getInt("id"));
                    role.setName(rs.getString("name"));
                    //role.setLevel(rs.getString("level"));
                    return role;
                }
            });
        } catch (DataAccessException e) {
            return null;
        }
    }

}
