package com.bridgelabz.service;

import com.bridgelabz.DAO.GreetingDAO;
import com.bridgelabz.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {
    @Autowired
    GreetingDAO greetingDAO;


    public int addGreeting(Greeting g)
    {
        return greetingDAO.addGreeting(g);
    }
    public String getGreeting(int id)
    {
        return  greetingDAO.getGreeting(id);
    }
    public List<Greeting> getAllGreeting()
    {
        return greetingDAO.getAllGreeting();
    }
    public int updateGreeting(int id,Greeting g)
    {
        return  greetingDAO.updateGreeting(id,g);
    }
    public  int deleteGreeting(int id)
    {
        return  greetingDAO.deleteGreeting(id);
    }
    public List<Greeting> searchGreeting(String name)
    {
        return greetingDAO.searchGreetings(name);
    }
    public List<Greeting> getUserGreetings(String name)
    {
        return greetingDAO.getUserGreetings(name);
    }
}
