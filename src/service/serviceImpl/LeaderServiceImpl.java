package service.serviceImpl;

import dao.LeaderDao;
import dao.daoImpl.LeaderDaoImpl;
import entity.Information;
import entity.ObservablePerformance;
import entity.Performance;
import javafx.collections.ObservableList;
import service.LeaderService;

import java.sql.SQLException;
import java.util.List;

public class LeaderServiceImpl implements LeaderService {
    LeaderDao leaderDao = new LeaderDaoImpl();
    //无参返回全体员工业绩情况
    @Override
    public List<Performance> getAllEmployeePerformance() throws SQLException, ClassNotFoundException {
        List<Performance>  list = leaderDao.getAllEmployeePerformance();
        return list;
    }
    //带参返回全体员工业绩情况
    @Override
    public ObservableList<ObservablePerformance> getAllEmployeePerformance(boolean isObsevabel) throws SQLException, ClassNotFoundException{
        ObservableList<ObservablePerformance>  list = leaderDao.getAllEmployeePerformance(isObsevabel);
        return list;
    }

    //更新绩效情况
    @Override
    public void updatePerformance(Performance performance) throws SQLException, ClassNotFoundException {
        leaderDao.updatePerformance(performance);
    }

    @Override
    public void changeUserAppeal(int id) throws SQLException, ClassNotFoundException {
        leaderDao.changeUserAppeal(id);
    }

    @Override
    public List<Information> getAllEmployeeInformation() throws SQLException, ClassNotFoundException {
        List<Information> list;
        list = leaderDao.getAllEmployeeInformation();
        return list;
    }
}
