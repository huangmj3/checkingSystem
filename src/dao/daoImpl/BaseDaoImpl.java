package dao.daoImpl;

import dao.BaseDao;
import entity.Information;
import entity.User;

import java.sql.*;

public class BaseDaoImpl implements BaseDao {
    @Override
    public User login(String account, String password) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //数据库连接
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        //构建sql，通过账号进行数据库查询
        String sql = "select * from tb_user where account=?";
        //创建预编译对象
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,account);
        //创建返回结果对象
        ResultSet rs = ps.executeQuery();
        //创建字符串数组用于接收返回结果
        String[] str = new String[2];
//        rs.next()
//        for(int i = 0; i < str.length; i++){
//            //结果集下标是从1开始计算的
//            tr[i] = rs.getString(i+1);
//       }
        //返回结果为空，账号不存在
        //调用next()方法使得当前行为第一行
        User user = null;
        if(!rs.next())
            return user;
        str[0] = rs.getString("account");
        str[1] = rs.getString("password");
        //验证账号密码是否相等
        if(str[0].equals(account) && str[1].equals(password)){
            //账号密码相等，给User对象赋值
            user = new User();
            user.setId(rs.getInt("id"));
            user.setAccount(rs.getString("account"));
            user.setPassword(rs.getString("password"));
            user.setConfirm(rs.getBoolean("isConfirm"));
            user.setAppeal(rs.getBoolean("isAppeal"));
            user.setLeader(rs.getBoolean("isLeader"));
            user.setAdministrator(rs.getBoolean("isAdministrator"));
        }
        return user;
    }

    @Override
    public Information getInformation(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        String sql = "select * from tb_information where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        Information information = null;
        if(rs.next()){
            information = new Information();
            information.setId(rs.getInt("id"));
            information.setName(rs.getString("name"));
            information.setSex(rs.getString("sex"));
            information.setAge(rs.getInt("age"));
            information.setTelNumber(rs.getLong("telNumber"));
            information.setEmail(rs.getString("email"));
            information.setDepartment(rs.getString("department"));
        }
        return information;
    }

    @Override
    public void register(String account, String password, Information information) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
//      向tb_user表中插入账号密码信息
        String sql1 = "insert into tb_user(account,password) values(?,?)";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ps1.setString(1,account);
        ps1.setString(2,password);
        ps1.executeUpdate();
        //获取自动生成的id号
        String sql2 = "select id from tb_user where account=? and password=?";
        PreparedStatement ps2 = con.prepareStatement(sql2);
        ps2.setString(1,account);
        ps2.setString(2,password);
        ResultSet rs = ps2.executeQuery();
        rs.next();
        int id = rs.getInt(1);
        //在tb_information表中新建一列并插入信息
        String sql3 = "insert into tb_information(id,name,sex,age,department,telNumber,email) values(?,?,?,?,?,?,?)";
        PreparedStatement ps3 = con.prepareStatement(sql3);
        ps3.setInt(1,id);
        ps3.setString(2,information.getName());
        ps3.setString(3,information.getSex());
        ps3.setInt(4,information.getAge());
        ps3.setString(5,information.getDepartment());
        ps3.setLong(6,information.getTelNumber());
        ps3.setString(7,information.getEmail());
        //电话号码要使用长整型
        ps3.executeUpdate();
        //在tb_performance中新建一列，等待填写
        String  sql4 = "insert into tb_performance(id) values(?)";
        PreparedStatement ps4 = con.prepareStatement(sql4);
        ps4.setInt(1,id);
        ps4.executeUpdate();
        //在tb_salary中新建一列，等待填写
//        String sql5 = "insert into tb_salary(id) values(id)";
//        Statement st = con.createStatement();
        //        st.executeUpdate(sql5);
        String sql5 = "insert into tb_salary(id) values(?)";
        PreparedStatement ps5 = con.prepareStatement(sql5);
        ps5.setInt(1,id);
        ps5.executeUpdate();
    }

    @Override
    public boolean searchAccount(String account) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkingsystem?serverTimezone=Hongkong", "root", "123456a");
        String sql = "select * from tb_user where account=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,account);
        ResultSet rs = ps.executeQuery();
        //存在结果集，则表明账号已存在
        if(rs.next())
            return true;
        return false;
    }
}
