package controllers;

import entity.User;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import service.BaseService;
import service.serviceImpl.BaseServiceImpl;
import application.BaseApplication;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    public Button LogInButton;
    public Button RegisterButton;
    public TextField accountField;
    public PasswordField passwordField;
    private BaseApplication application;
    public static User user = null;

    public void logIn(MouseEvent mouseEvent) throws Exception {
//        System.out.println(1111 + "\n" + accountField.getText() + "\n" + passwordField.getText());
        BaseService baseService = new BaseServiceImpl();
        user = baseService.logInCheck(accountField.getText(),passwordField.getText());
//        System.out.println("\n" + user);
        //账号或密码错误
        if(user == null){
            JOptionPane.showMessageDialog(null,"账号或密码错误，请检查！",
                    "错误",JOptionPane.ERROR_MESSAGE);
            clean();
        }
        else{
            //是领导，前往领导首页
            if(user.isLeader())
                application.goToLeaderHome();
            //是管理员，前往管理员首页
            else if(user.isAdministrator()){
                application.goToAdministratorHome();
            }
            //是职工，前往职工首页
            else{
                application.goToEmployeeHome();
            }
        }
    }
    //前往注册界面
    public void goToRegister(MouseEvent mouseEvent) {
//        System.out.println(1111);
        application.goToRegister();
    }
    //用于账号密码输入错误时清空文本
    public void clean(){
        accountField.setText("");
        passwordField.setText("");
    }
    //获得原本的应用程序窗口
    public void setApp(BaseApplication application){
        this.application = application;
    }
    //实现初始化接口，调用初始化方法
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
