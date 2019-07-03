package service.serviceImpl;

import dao.AdministratorDao;
import dao.daoImpl.AdministratorDaoImpl;
import entity.DefaultData;
import entity.Performance;
import service.AdministratorService;

import java.sql.SQLException;

public class AdministratorServiceImpl implements AdministratorService {
    AdministratorDao administratorDao = new AdministratorDaoImpl();
    @Override
    public DefaultData getDefaultData(int id) throws SQLException, ClassNotFoundException {
        DefaultData defaultData = administratorDao.getDefaultData(id);
        return  defaultData;
    }
    @Override
    public void saveDefaultData(int planedSaleroom, int basicSalary, int planedSalary) throws SQLException, ClassNotFoundException {
        administratorDao.changeAndSaveData(planedSaleroom, basicSalary, planedSalary);
    }
    @Override
    public void hidePerformance(Performance performance){

    }
}
