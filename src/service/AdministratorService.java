package service;

import entity.DefaultData;
import entity.Performance;

import java.sql.SQLException;
import java.util.List;

public interface AdministratorService {
    //获得全部默认数据
    DefaultData getDefaultData(int id) throws SQLException, ClassNotFoundException;
    //默认数据发生改变，保存默认数据，并根据默认数据重新计算绩效情况和工资情况
    void saveDefaultData(int planedSaleroom, int basicSalary, int planedSalary) throws SQLException, ClassNotFoundException;
    //隐藏该绩效列，使得无法查询到
    void hidePerformance(Performance performance);
}
