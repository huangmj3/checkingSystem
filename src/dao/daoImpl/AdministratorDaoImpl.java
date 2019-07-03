package dao.daoImpl;

import dao.AdministratorDao;
import entity.DefaultData;
import entity.Information;
import entity.Performance;
import entity.Salary;

import java.sql.*;

public class AdministratorDaoImpl implements AdministratorDao {
    @Override
    public DefaultData getDefaultData(int id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        String sql = "select * from tb_defaultdata where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        //进入返回集当前列
        rs.next();
        DefaultData defaultData = new DefaultData();
        defaultData.setId(rs.getInt("id"));
        defaultData.setPlanedSaleroom(rs.getInt("planedSaleroom"));
        defaultData.setBasicSalary(rs.getInt("basicSalary"));
        defaultData.setPlanedSalary(rs.getInt("planedSalary"));
        return  defaultData;
    }
    //公式默认数据发生改变，保存公式默认数据，并根据新的默认数据重新计算绩效情况和工资情况
    @Override
    public void changeAndSaveData(int planedSaleroom, int basicSalary, int planedSalary) throws ClassNotFoundException, SQLException {
        //加载数据库驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //建立数据库连接
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        //更新默认数据
        String sql1 = "update tb_defaultdata set planedSaleroom=?,basicSalary=?,planedSalary=? where id = ?";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ps1.setInt(1,planedSaleroom);
        ps1.setInt(2,basicSalary);
        ps1.setInt(3,planedSalary);
        ps1.setInt(4,1);
        //执行更新
        ps1.executeUpdate();
        //获取全体员工绩效情况
        String sql2 = "select * from tb_performance";
        PreparedStatement ps2 = con.prepareStatement(sql2);
        //获得全体员工绩效情况返回结果集
        ResultSet rs1 = ps2.executeQuery();
        //根据默认数据和公式重新计算得分，工资额和绩效额，并保存
        while(rs1.next()){
            Performance performance = new Performance();
            performance.setId(rs1.getInt("id"));
            //实际完成销售额
            int saleroom = rs1.getInt("saleroom");
            //新客户人数
            int numberOfNew = rs1.getInt("numberOfNew");
            //流失客户人数
            int numberOfLost = rs1.getInt("numberOfLost");
            //客户投诉次数
            int numberOfComplain = rs1.getInt("numberOfComplain");
            //客户访问次数
            int numberOfVisit = rs1.getInt("numberOfVisit");
            //得分
            int score = (int)(0.6 * 60 * (saleroom/planedSaleroom) + 0.2 * 60 * (numberOfNew/numberOfLost)
                    + 0.2 * 100 * (1 - numberOfComplain/numberOfVisit));
            //更新得分信息
            performance.setScore(score);
            String sql3 = "update tb_performance set score=? where id=?";
            PreparedStatement ps3 = con.prepareStatement(sql3);
            ps3.setInt(1,score);
            ps3.setInt(2, rs1.getInt("id"));
            ps3.executeUpdate();
            //绩效工资，转为浮点数，防止整数相除为零
            int performanceAmount = (int)(planedSalary * ((saleroom * 1.0)/planedSaleroom));
            //工资额
            int totalAmount = basicSalary + performanceAmount;
            //更新绩效工资和工资额
            String sql4 = "update tb_salary set totalAmount=?,performanceAmount=? where id=?";
            PreparedStatement ps4 = con.prepareStatement(sql4);
            ps4.setInt(1,totalAmount);
            ps4.setInt(2,performanceAmount);
            ps4.setInt(3, rs1.getInt("id"));
            ps4.executeUpdate();
        }
    }
    //设置数据列隐藏属性
    @Override
    public void hidePerformance(Performance performance) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        String sql = "update tb_information set isHide=? where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setBoolean(1,true);
        ps.setInt(2,performance.getId());
        ps.executeUpdate();
    }
}
