package dao.daoImpl;

import dao.EmployeeDao;
import entity.Information;
import entity.Performance;
import entity.Salary;

import java.sql.*;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public Salary getSalary(int id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //数据库连接
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        //构建sql，通过账号获取账号和密码，用于之后进行比对
        String sql = "select * from tb_salary where id=?";
        //创建预编译对象
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        //创建返回结果对象
        ResultSet rs = ps.executeQuery();
        Salary salary = null;
        if(rs.next()){
            salary = new Salary();
//            int i = rs.getInt(1);
            salary.setId(rs.getInt("id"));
            salary.setTotalAmount(rs.getInt("totalAmount"));
            salary.setPerformanceAmount(rs.getInt("performanceAmount"));
            salary.setOtherItem(rs.getInt("otherItem"));
        }
        return salary;
    }

    @Override
    public Performance getPerformance(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        String sql = "select * from tb_performance where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        Performance performance = null;
        if(rs.next()){
            performance = new Performance();
            performance.setId(rs.getInt("id"));
            performance.setScore(rs.getInt("score"));
            performance.setRanking(rs.getInt("ranking"));
            performance.setSaleroom(rs.getInt("saleroom"));
            performance.setNumberOfNew(rs.getInt("numberOfNew"));
            performance.setNumberOfLost(rs.getInt("numberOfLost"));
            performance.setNumberOfComplain(rs.getInt("numberOfComplain"));
            performance.setNumberOfVisit(rs.getInt("numberOfVisit"));
            performance.setReject(rs.getBoolean("isReject"));
        }
        return performance;
    }

    @Override
    public void setConfirmed(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        String sql = "update tb_user set isConfirm=? where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setBoolean(1,true);
        ps.setInt(2,id);
        ps.executeUpdate();
    }
    //修改tb_user和tb_performance中的是否已申诉，并在tb_performance中更新申诉内容项
    @Override
    public void setAppealed(int id, String appealContext) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        //更新tb_user中的是否已申诉项
        String sql1 = "update tb_user set isAppeal=? where id=?";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ps1.setBoolean(1,true);
        ps1.setInt(2,id);
        ps1.executeUpdate();
        //更新tb_performance中的是否已申诉还有申诉内容项
        String sql2 = "update tb_performance set isAppeal=?,appealContext=? where id=?";
        PreparedStatement ps2 = con.prepareStatement(sql2);
        ps2.setBoolean(1,true);
        ps2.setString(2,appealContext);
        ps2.setInt(3,id);
        ps2.executeUpdate();
    }

    @Override
    public boolean isConfirmOrAppeal(int id) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        String sql = "select isConfirm,isAppeal from tb_user where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        if(rs.getBoolean("isConfirm") == true || rs.getBoolean("isAppeal") == true)
            return true;
        return false;
    }

    @Override
    public void saveInformation(Information information) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        String sql = "update tb_information set name=?,sex=?,age=?,department=?,telNumber=?,email=? where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,information.getName());
        ps.setString(2,information.getSex());
        ps.setInt(3,information.getAge());
        ps.setString(4,information.getDepartment());
        ps.setLong(5,information.getTelNumber());
        ps.setString(6,information.getEmail());
        ps.setInt(7,information.getId());
        ps.executeUpdate();
    }

    @Override
    public void setReject(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        String sql = "update tb_performance set isReject=? where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setBoolean(1,false);
        ps.setInt(2,id);
        ps.executeUpdate();
    }
}
