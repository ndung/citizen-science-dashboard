package id.go.lipi.informatika.alboom.dashboard.manager;

import id.go.lipi.informatika.alboom.dashboard.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("dataManager")
public class DataManager {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String sql;
    List<String> params;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public List<Data> getData(List<String> statusList,
                              String user,
                              String uploadFrom,
                              String uploadTo){
        sql = "select b.id as uid, b.full_name, a.* from data a, user b where a.user_id=b.id ";
        params = new ArrayList<String>();
        if (statusList!=null && !statusList.isEmpty()){
            sql = sql + "and a.status in (";
            addSQLParams(statusList);
        }
        if (user!=null && !user.isEmpty()){
            sql = sql + "and b.full_name like ? ";
            params.add("%"+user+"%");
        }
        if(uploadFrom!=null && !uploadFrom.isEmpty()){
            sql = sql + "and a.create_at between ? and ? ";
            params.add(uploadFrom+" 00:00:00");
            if (uploadTo!=null && !uploadTo.isEmpty()) {
                params.add(uploadTo+" 23:59:59");
            }else{
                params.add(sdf.format(new Date()));
            }
        }
        sql = sql + " order by a.create_at desc";
        return jdbcTemplate.query(sql, params.toArray(), new DataRowMapper());
    }

    public Data getDatum(String id){
        String sql = "select b.id as uid, b.full_name, a.* from data a, user b where a.user_id=b.id and a.id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new DataRowMapper());
    }

    private void addSQLParams(List<String> list){
        for (String str : list){
            sql = sql + "?,";
            params.add(str);
        }
        sql = sql.substring(0,sql.length()-1)+") ";
    }

    public void updateData(String status, String id, String username){
        String sql = "update data set status = ?, verificator = ?, verification_time = ? where id = ?";
        Object[] param = new Object[]{status, username, new Date(), id};
        jdbcTemplate.update(sql, param);
    }

    public void updateNotes(String status, String id, String username){
        String sql = "update data set notes = ? where id = ?";
        Object[] param = new Object[]{status, id};
        jdbcTemplate.update(sql, param);
    }

    public void updateImage(String status, String id, String username){
        String sql = "update image set status = ?, verificator = ?, verification_time = ? where image_id = ?";
        Object[] param = new Object[]{status, username, new Date(), id};
        jdbcTemplate.update(sql, param);
    }

    public List<Image> getImages(final String dataId){
        String sql = "select * from image where data_id = ?";
        Object[] param = new Object[]{dataId};
        return jdbcTemplate.query(sql, param, new RowMapper<Image>() {
            @Override
            public Image mapRow(ResultSet resultSet, int i) throws SQLException {
                Image image = new Image();
                image.setId(resultSet.getString("id"));
                image.setSectionId(resultSet.getString("section_id"));
                image.setUrl("https://foridn.brin.go.id/api/v1/images/"+image.getId());
                image.setCreateAt(resultSet.getTimestamp("create_at"));
                image.setStatus(resultSet.getInt("status"));
                image.setVerificator(resultSet.getString("verificator"));
                image.setVerificationTime(resultSet.getTimestamp("verification_time"));
                return image;
            }
        });
    }

    public List<Detail> getDetails(final String dataId){
        String sql = "select a.id, a.response, a.date_time, b.attribute from " +
                "survey_response a, survey_question b where a.question_id = b.id and a.data_id = ? " +
                "order by a.question_id desc";
        Object[] param = new Object[]{dataId};
        return jdbcTemplate.query(sql, param, new RowMapper<Detail>() {
            @Override
            public Detail mapRow(ResultSet resultSet, int i) throws SQLException {
                Detail detail = new Detail();
                detail.setId(resultSet.getLong("id"));
                detail.setQuestion(resultSet.getString("attribute"));
                detail.setResponse(resultSet.getString("response"));
                detail.setTime(resultSet.getTimestamp("date_time"));
                return detail;
            }
        });
    }

    class DataRowMapper implements RowMapper<Data>{

        @Override
        public Data mapRow(ResultSet resultSet, int i) throws SQLException {
            Data datum = new Data();
            datum.setId(resultSet.getString("id"));
            datum.setNotes(resultSet.getString("notes"));
            datum.setLatitude(resultSet.getDouble("latitude"));
            datum.setLongitude(resultSet.getDouble("longitude"));
            datum.setCreateAt(resultSet.getTimestamp("create_at"));
            datum.setStartDate(resultSet.getTimestamp("start_date"));
            datum.setFinishDate(resultSet.getTimestamp("finish_date"));
            datum.setStatus(resultSet.getInt("status"));
            datum.setImagePath("images.html?id="+datum.getId());

            User user = new User();
            user.setId(resultSet.getLong("uid"));
            user.setFullName(resultSet.getString("full_name"));
            datum.setUser(user);
            datum.setVerificator(resultSet.getString("verificator"));
            datum.setVerificationTime(resultSet.getTimestamp("verification_time"));
            datum.setImages(getImages(datum.getId()));
            datum.setDetails(getDetails(datum.getId()));
            return datum;
        }
    }
}
