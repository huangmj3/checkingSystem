package dao.daoImpl;

import dao.LeaderDao;
import entity.Information;
import entity.ObservablePerformance;
import entity.Performance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class LeaderDaoImpl implements LeaderDao {
    //获取所有业绩情况信息，带一个boolean类型参数的构造方法，返回ObservabelList
    @Override
    public ObservableList<ObservablePerformance> getAllEmployeePerformance(boolean isObservabel) throws ClassNotFoundException, SQLException {
        //加载JDBC驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //进行数据库连接
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        //构建SQL语句，查询业绩情况表中的所有内容
        String sql = "select * from tb_performance";
        //SQL语句预编译
        PreparedStatement ps = con.prepareStatement(sql);
        //执行SQL语句，并获得返回集
        ResultSet rs = ps.executeQuery();
        ObservableList<ObservablePerformance> list = FXCollections.observableArrayList();
        //按行获取结果集
        while(rs.next()){
            ObservablePerformance performance = new ObservablePerformance();
            int id= rs.getInt("id");
            String name = rs.getString("name");
            performance.setId(rs.getInt("id"));
            performance.setName(rs.getString("name"));
            performance.setScore(rs.getInt("score"));
            performance.setRanking(rs.getInt("ranking"));
            performance.setSaleroom(rs.getInt("saleroom"));
            performance.setNumnberOfNew(rs.getInt("numberOfNew"));
            performance.setNumberOfLost(rs.getInt("numberOfLost"));
            performance.setNumberOfComplain(rs.getInt("numberOfComplain"));
            performance.setNumberOfVisit(rs.getInt("numberOfVisit"));
            list.add(performance);
        }
        return list;
    }
    //获取所有业绩情况信息，无参构造方法，返回ArrayList
    @Override
    public List<Performance> getAllEmployeePerformance() throws SQLException, ClassNotFoundException {
        //加载JDBC驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //进行数据库连接
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        //构建SQL语句，查询业绩情况表中的所有内容
        String sql = "select * from tb_performance";
        //SQL语句预编译
        PreparedStatement ps = con.prepareStatement(sql);
        //执行SQL语句，并获得返回集
        ResultSet rs = ps.executeQuery();
        List<Performance> list = FXCollections.observableArrayList();
        //按行获取结果集
        while(rs.next()) {
            Performance performance = new Performance();
            performance.setId(rs.getInt("id"));
            performance.setName(rs.getString("name"));
            performance.setScore(rs.getInt("score"));
            performance.setRanking(rs.getInt("ranking"));
            performance.setSaleroom(rs.getInt("saleroom"));
            performance.setNumberOfNew(rs.getInt("numberOfNew"));
            performance.setNumberOfLost(rs.getInt("numberOfLost"));
            performance.setNumberOfComplain(rs.getInt("numberOfComplain"));
            performance.setNumberOfVisit(rs.getInt("numberOfVisit"));
            performance.setAppeal(rs.getBoolean("isAppeal"));
            performance.setAppealContext(rs.getString("appealContext"));
            list.add(performance);
        }
        return list;
    }
    //更新绩效情况
    @Override
    public void updatePerformance(Performance performance) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        String sql = "update tb_performance set name=?,score=?,ranking=?,saleroom=?,numberOfNew=?,numberOfLost=?,numberOfComplain=?,numberOfVisit=?,isAppeal=?,appealContext=?,isReject=? where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,performance.getName());
        ps.setInt(2,performance.getScore());
        ps.setInt(3,performance.getRanking());
        ps.setInt(4,performance.getSaleroom());
        ps.setInt(5,performance.getNumberOfNew());
        ps.setInt(6,performance.getNumberOfLost());
        ps.setInt(7,performance.getNumberOfComplain());
        ps.setInt(8,performance.getNumberOfVisit());
        ps.setBoolean(9,performance.isAppeal());
        ps.setString(10,performance.getAppealContext());
        ps.setBoolean(11,performance.isReject());
        //通过唯一标识ID去更新绩效情况
        ps.setInt(12,performance.getId());
        ps.executeUpdate();
    }
    //修改用户申诉情况，设置为未申诉，同时开放确认按钮
    @Override
    public void changeUserAppeal(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        String sql = "update tb_user set isConfirm=?,isAppeal=? where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setBoolean(1,false);
        ps.setBoolean(2,false);
        ps.setInt(3,id);
        ps.executeUpdate();
    }
    //获得全体员工个人信息，返回ArrayList
    public List<Information> getAllEmployeeInformation() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        String sql = "select * from tb_information";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Information> list = FXCollections.observableArrayList();
        while (rs.next()){
            Information information = new Information();
            information.setId(rs.getInt("id"));
            int id = rs.getInt("id");
            information.setName(rs.getString("name"));
            information.setSex(rs.getString("sex"));
            information.setAge(rs.getInt("age"));
            information.setDepartment(rs.getString("department"));
            information.setTelNumber(rs.getLong("telNumber"));
            information.setEmail(rs.getString("email"));
            list.add(information);
        }
        return list;
    }
}
