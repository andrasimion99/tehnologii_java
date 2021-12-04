package com.example.lab7.dao;

import com.example.lab7.entity.User;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserDao extends GenericDaoImpl<User> {
    public UserDao() {
        super(User.class);
    }

    @Override
    public User create(User user) throws Exception {
        if (findByUsername(user.getUsername()) != null)
            throw new Exception("Username already exists");
        return super.create(user);
    }

    @Override
    public User update(User user) {
        return super.update(user);
    }

    @Override
    public User get(Integer id) {
        return super.get(id);
    }

    public List<User> getAll() {
        return super.getAll("User.findAll");
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    public User login(User user) throws Exception {
        User foundUser = findByUsername(user.getUsername());
        if (foundUser == null)
        {
            throw new Exception("User doesn't exist");
        }
        if (!user.getPass().equals(foundUser.getPass()))
            throw new Exception("Wrong credentials");
        return foundUser;
    }

    public User findByUsername(String username) {
        return super.getByProperty("User.findByUsername", username);
    }
}
