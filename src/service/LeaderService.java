package service;

import entity.Information;
import entity.ObservablePerformance;
import entity.Performance;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface LeaderService {
    //无参构造方法，获取全体员工业绩情况，返回ArrayList
    List<Performance> getAllEmployeePerformance() throws SQLException, ClassNotFoundException;
    //带一个Boolean类型变量的参数，获取全体员工业绩情况，返回ObservabelList
    ObservableList<ObservablePerformance> getAllEmployeePerformance(boolean isObsevabel) throws SQLException, ClassNotFoundException;
    //更新绩效情况
    void updatePerformance(Performance performance) throws SQLException, ClassNotFoundException;
    //更改用户申诉情况，设置为未申诉，同时开放确认按钮
    void changeUserAppeal(int id) throws SQLException, ClassNotFoundException;
    //获得全体员工个人信息，返回ArrayList
    List<Information> getAllEmployeeInformation() throws SQLException, ClassNotFoundException;
}
