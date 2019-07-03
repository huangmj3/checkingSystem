package dao;

import entity.Information;
import entity.ObservablePerformance;
import entity.Performance;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface LeaderDao {
    //无参构造方法，返回ArrayList
    List<Performance> getAllEmployeePerformance() throws ClassNotFoundException, SQLException;
    //一个参数为boolean类型的参数，返回ObservabelList
    ObservableList<ObservablePerformance> getAllEmployeePerformance( boolean isObservabel) throws ClassNotFoundException, SQLException;
    //更新绩效情况
    void updatePerformance(Performance performance) throws SQLException, ClassNotFoundException;
    //更新用户申诉情况，设置为未申诉,同时开放确认按钮
    void changeUserAppeal(int id) throws ClassNotFoundException, SQLException;
    //获得全体员工个人信息，返回ArrayList
    List<Information> getAllEmployeeInformation() throws ClassNotFoundException, SQLException;
}
