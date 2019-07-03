package application;

import controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseApplication extends Application{
    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 400.0;
    private final double MINIMUM_WINDOW_HEIGHT = 250.0;
    //启动方法
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = primaryStage;
        stage.setTitle("Checking System");
//        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
//        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        primaryStage.setScene(new Scene(root, 600, 450));
        goToLogin();
        stage.show();
    }
    //跳转至登录界面
    public void goToLogin(){
        try {
            LogInController login = (LogInController) replaceSceneContent("login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BaseApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //跳转至注册界面
    public void goToRegister() {
        try {
            RegisterController register = (RegisterController) replaceSceneContent("register.fxml");
            register.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BaseApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //跳转至员工首页
    public void goToEmployeeHome(){
        try {
            EHomeController home = (EHomeController) replaceSceneContent("EmployeeHome.fxml");
            home.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BaseApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //跳转至领导首页
    public void goToLeaderHome() {
        try {
            LHomeController leaderHome = (LHomeController) replaceSceneContent("leaderHome.fxml");
            leaderHome.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BaseApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //跳转至领导查看员工业绩情况界面
    public void goToEmployeesPerformance(){
        try {
            EmployeesPerformanceController employeesPerformance = (EmployeesPerformanceController) replaceSceneContent("employeesPerformance.fxml");
            employeesPerformance.setApp(this);
        }catch (Exception ex){
            Logger.getLogger(BaseApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //跳转至领导查看员工个人信息界面
    public void goToEmployeesInformation(){
        try {
            EmployeesInformationController employeesInformationController = (EmployeesInformationController) replaceSceneContent("employeesInformation.fxml");
            employeesInformationController.setApp(this);
        }catch (Exception ex){
            Logger.getLogger(BaseApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //跳转至我的信息界面（职工）
    public void goToInformationForEmployee(){
        try{
            MyInformationController informationController = (MyInformationController)replaceSceneContent("myInformation.fxml");
            informationController.setApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //跳转至职工个人信息界面（管理员）
//    public void goToInformationForAdministator{
//    }
    //跳转至管理员首页
    public void goToAdministratorHome(){
        try{
            AHomeController admin = (AHomeController) replaceSceneContent("administratorHome.fxml");
            admin.setApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //跳转至管理员的设置业绩计算方法界面
    public void goToDefaultData(){
        try{
            DefaultDataController defaultDataController = (DefaultDataController) replaceSceneContent("defaultData.fxml");
            defaultDataController.setApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //实现初始化接口，用于初始化界面，以达到界面跳转的目的
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = BaseApplication.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(BaseApplication.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 600, 450);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {
        //加载参数，用于启动界面
        launch(args);
    }

}