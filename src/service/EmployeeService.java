package service;

import entity.Information;
import entity.Performance;
import entity.Salary;

import java.sql.SQLException;

public interface EmployeeService {
    //获得工资情况
    Salary getSalary(int id) throws SQLException, ClassNotFoundException;
    //获得业绩情况
    Performance getPerformance(int id) throws SQLException, ClassNotFoundException;
    //员工已确认工资情况和业绩情况
    void setConfirmed(int id) throws SQLException, ClassNotFoundException;
    //员工已投诉
    void setAppealed(int id, String appealContext) throws SQLException, ClassNotFoundException;
    //查询是否已经进行确认或者申诉
    boolean isConfirmOrAppeal(int id) throws  SQLException, ClassNotFoundException;
    //保存用户个人信息
    void saveInformation(Information information) throws SQLException, ClassNotFoundException;
    //更新驳回情况
    void setReject(int id) throws SQLException, ClassNotFoundException;
}
