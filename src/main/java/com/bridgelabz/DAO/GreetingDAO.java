package com.bridgelabz.DAO;

import com.bridgelabz.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class GreetingDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
//    UC1- Create Greeting
    public int addGreeting(Greeting g)
    {
        String sql="insert into greetings(user_name,message) values(?,?)";
        return jdbcTemplate.update(sql,g.getUserName(),g.getMessage());
    }

//    UC2- Get Greeting by id
    public String getGreeting(int id)
    {
        String sql="select message from greetings where id=?";
        return  jdbcTemplate.queryForObject(sql,String.class,id);
    }

//    UC3- GetALL Greetings
    public List<Greeting> getAllGreeting()
    {
        String sql="select * from greetings";
        return jdbcTemplate.query(sql,(res,rowNo)->{
            Greeting g=new Greeting();
            g.setId(res.getInt("id"));
            g.setUserName(res.getString("user_name"));
            g.setMessage(res.getString("message"));
            g.setCreatedDate(res.getTimestamp("created_date").toLocalDateTime());
            return g;
        });
    }
    //    4.Update Greeting
    public int updateGreeting(int id,Greeting g)
    {
        String sql="update greetings set message=? where id=?";
        return jdbcTemplate.update(sql,g.getMessage(),id);
    }

//    5.Delete Greeting
    public int deleteGreeting(int id)
    {
        String sql="delete from greetings where id=?";
        return  jdbcTemplate.update(sql,id);
    }

//    6. Search Greeting
public List<Greeting> searchGreetings(String name) {
    String sql = "SELECT * FROM greetings WHERE user_name = ?";

    return jdbcTemplate.query(sql, (res, rowNo) -> {
        Greeting g = new Greeting();
        g.setId(res.getInt("id"));
        g.setUserName(res.getString("user_name"));
        g.setMessage(res.getString("message"));
        g.setCreatedDate(
                res.getTimestamp("created_date").toLocalDateTime()
        );
        return g;
    }, name);


}

//7. Get User Greeting
public List<Greeting> getUserGreetings(String name) {
    String sql = "SELECT * FROM greetings WHERE user_name = ?";

    return jdbcTemplate.query(sql, (res, rowNo) -> {
        Greeting g = new Greeting();

        g.setId(res.getInt("id"));
        g.setUserName(res.getString("user_name"));
        g.setMessage(res.getString("message"));
        g.setCreatedDate(
                res.getTimestamp("created_date").toLocalDateTime()
        );

        return g;
    }, name);
}


}
