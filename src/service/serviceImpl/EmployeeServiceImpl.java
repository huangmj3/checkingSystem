package service.serviceImpl;

import dao.EmployeeDao;
import dao.daoImpl.EmployeeDaoImpl;
import entity.Information;
import entity.Performance;
import entity.Salary;
import service.EmployeeService;

import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    @Override
    public Salary getSalary(int id) throws SQLException, ClassNotFoundException {
        Salary salary = employeeDao.getSalary(id);
        return salary;
    }

    @Override
    public Performance getPerformance(int id) throws SQLException, ClassNotFoundException {
        Performance performance = employeeDao.getPerformance(id);
        return performance;
    }

    @Override
    public void setConfirmed(int id) throws SQLException, ClassNotFoundException {
        employeeDao.setConfirmed(id);
    }

    @Override
    public void setAppealed(int id, String appealContext) throws SQLException, ClassNotFoundException {
        employeeDao.setAppealed(id, appealContext);
    }

    @Override
    public boolean isConfirmOrAppeal(int id) throws  SQLException, ClassNotFoundException{
        return employeeDao.isConfirmOrAppeal(id);
    }

    @Override
    public void saveInformation(Information information) throws SQLException, ClassNotFoundException {
        employeeDao.saveInformation(information);
    }

    @Override
    public void setReject(int id) throws SQLException, ClassNotFoundException {
        employeeDao.setReject(id);
    }
}
