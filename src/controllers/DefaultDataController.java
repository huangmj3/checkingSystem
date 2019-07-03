package controllers;

import application.BaseApplication;
import entity.DefaultData;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.AdministratorService;
import service.serviceImpl.AdministratorServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DefaultDataController implements Initializable {
    public Text goToHomeButton;
    public TextField planedSaleroomTextField;
    public TextField basicSalaryTextField;
    public TextField planedSalaryTextField;
    public Button saveButton;

    BaseApplication application;

    AdministratorService administratorService = new AdministratorServiceImpl();
    
    public void setApp(BaseApplication application) throws SQLException, ClassNotFoundException {
        this.application = application;
        this.showHome();
    }

    private void showHome() throws SQLException, ClassNotFoundException {
        //获取默认数据
        DefaultData defaultData = administratorService.getDefaultData(1);
        //显示出默认数据
        planedSaleroomTextField.setText("" + defaultData.getPlanedSaleroom());
        basicSalaryTextField.setText("" + defaultData.getBasicSalary());
        planedSalaryTextField.setText("" + defaultData.getPlanedSalary());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void goToHome(MouseEvent mouseEvent) {
        application.goToAdministratorHome();
    }

    public void saveData(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        administratorService.saveDefaultData(Integer.parseInt(planedSaleroomTextField.getText()),
                Integer.parseInt(basicSalaryTextField.getText()), Integer.parseInt(planedSalaryTextField.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("保存成功！");
        alert.setContentText("已根据默认数据重新计算了绩效情况和工资情况");
        alert.showAndWait();
    }
}
