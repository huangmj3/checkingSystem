package controllers;

import application.BaseApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LHomeController implements Initializable {
    public Button informationCheckButton;
    public Button performanceCheckButton;

    private BaseApplication application;

    public void checkInformation(MouseEvent mouseEvent) {
        application.goToEmployeesInformation();
    }

    public void checkPerformance(MouseEvent mouseEvent) {
        application.goToEmployeesPerformance();
    }

    public void setApp(BaseApplication application) {
        this.application = application;
        //设置不进行聚焦
        informationCheckButton.setFocusTraversable(false);
        performanceCheckButton.setFocusTraversable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
