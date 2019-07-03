package dao;

import entity.Information;
import entity.User;

import java.sql.*;

public interface BaseDao {
    //登陆检查
    User login(String account, String password) throws ClassNotFoundException, SQLException;
    //获取个人信息
    Information getInformation(int id) throws ClassNotFoundException, SQLException;
    //账号注册
    void register(String account, String password, Information information) throws ClassNotFoundException, SQLException;
    //用于注册时搜索账号否已存在
    boolean searchAccount(String account) throws ClassNotFoundException, SQLException;
}
