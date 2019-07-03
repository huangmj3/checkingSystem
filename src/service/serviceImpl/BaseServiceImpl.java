package service.serviceImpl;

import dao.BaseDao;
import dao.daoImpl.BaseDaoImpl;
import entity.Information;
import entity.User;
import service.BaseService;

import java.sql.SQLException;

public class BaseServiceImpl implements BaseService {
    BaseDao basedao = new BaseDaoImpl();

    @Override
    public User logInCheck(String account, String password) throws SQLException, ClassNotFoundException {
        return basedao.login(account,password);
    }

    @Override
    public Information getInformation(int id) throws SQLException, ClassNotFoundException {
        return basedao.getInformation(id);
    }

    @Override
    public void register(String account, String password, Information information) throws SQLException, ClassNotFoundException {
        basedao.register(account,password,information);
    }

    @Override
    public boolean searchAccount(String account) throws SQLException, ClassNotFoundException {
        return basedao.searchAccount(account);
    }
}
