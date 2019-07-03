package dao;

import entity.DefaultData;
import entity.Performance;

import java.sql.SQLException;

public interface AdministratorDao {
    //获取全部默认数据
    DefaultData getDefaultData(int id) throws SQLException, ClassNotFoundException;
    //默认数据得到修改，保存默认数据，并根据默认数据重新计算绩效情况和工资情况
    void changeAndSaveData(int planedSaleroom, int basicSalary, int planedSalary) throws ClassNotFoundException, SQLException;
    //设置数据列隐藏属性
    void hidePerformance(Performance performance) throws ClassNotFoundException, SQLException;
}
