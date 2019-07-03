package controllers;

import application.BaseApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AHomeController implements Initializable{
    public Button systemConfigureButton;
    public Button changeUserInformationButton;
    public Button recordPerformanceButton;
    public Button changeDefaultDataButton;
    private  BaseApplication application;

    public void setApp(BaseApplication application) {
        this.application = application;
        this.showHome();
        //设置初始无聚焦
        systemConfigureButton.setFocusTraversable(false);
        changeUserInformationButton.setFocusTraversable(false);
        recordPerformanceButton.setFocusTraversable(false);
        changeDefaultDataButton.setFocusTraversable(false);
    }

    public void showHome(){

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void systemConfigure(MouseEvent mouseEvent) {
    }

    public void changeUserInformation(MouseEvent mouseEvent) {
        //借用领导的查看个人信息界面
        application.goToEmployeesInformation();
    }

    public void recordPerformance(MouseEvent mouseEvent) {
        //借用领导的查看绩效情况界面
        application.goToEmployeesPerformance();
    }

    public void changeDefaultData(MouseEvent mouseEvent) {
        application.goToDefaultData();
    }
}
