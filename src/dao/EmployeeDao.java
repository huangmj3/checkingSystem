package dao;

import entity.Information;
import entity.Performance;
import entity.Salary;

import java.sql.SQLException;

public interface EmployeeDao {
    //通过唯一标识id，获得工资情况
    Salary getSalary(int id) throws SQLException, ClassNotFoundException;
    //通过唯一标识id,获得业绩情况
    Performance getPerformance(int id) throws ClassNotFoundException, SQLException;
    //通过唯一标识Id,记录下用户已确认工资情况和业绩情况
    void setConfirmed(int id) throws ClassNotFoundException, SQLException;
    //通过唯一标识Id,记录下用于已进行申诉
    void setAppealed(int id, String appealContext) throws ClassNotFoundException, SQLException;
    //通过唯一表示id，查询是否已经进行确认或者申诉
    boolean isConfirmOrAppeal(int id) throws  ClassNotFoundException, SQLException;
    //保存用户个人信息
    void saveInformation(Information information) throws ClassNotFoundException, SQLException;
    //更新驳回情况
    void setReject(int id) throws ClassNotFoundException, SQLException;
}
