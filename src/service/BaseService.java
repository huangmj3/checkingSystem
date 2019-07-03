package service;

import entity.Information;
import entity.User;

import java.sql.SQLException;

public interface BaseService {
    //登录检查
    User logInCheck(String account, String password) throws SQLException, ClassNotFoundException;
    //获取个人信息
    Information getInformation(int id) throws SQLException, ClassNotFoundException;
    //账号注册
    void register(String account, String password, Information information) throws SQLException, ClassNotFoundException;
    //用于注册时搜索账号是否已存在
    boolean searchAccount(String account) throws SQLException, ClassNotFoundException;
}
