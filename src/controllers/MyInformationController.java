package controllers;

import application.BaseApplication;
import entity.Information;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.BaseService;
import service.EmployeeService;
import service.serviceImpl.BaseServiceImpl;
import service.serviceImpl.EmployeeServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyInformationController implements Initializable {


    public Text informationToHomeButton;
    public ImageView photoImage;
    public Label departmentLabel;
    public Label telLabel;
    public Label sexLabel;
    public Label ageLabel;
    public Label nameLabel;
    public Label emailLabel;
    public TextField nameText;
    public TextField sexText;
    public TextField ageText;
    public TextField departmentText;
    public TextField telText;
    public TextField emailText;
    public Button saveButton;

    private BaseApplication application;
    private int id = LogInController.user.getId();
    Information information;
    BaseService baseService = new BaseServiceImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();

    public void setApp(BaseApplication application) throws SQLException, ClassNotFoundException {
        this.application = application;
        this.showInformation();
    }
    //展示个人信息
    public void showInformation() throws SQLException, ClassNotFoundException {
        information = baseService.getInformation(id);
//        nameLabel.setText("姓名：" + information.getName());
//        sexLabel.setText("性别：" + information.getSex());
//        ageLabel.setText("年龄：" + information.getAge());
//        departmentLabel.setText("部门：" + information.getDepartment());
//        telLabel.setText("电话：" + information.getTelNumber());
//        emailLabel.setText("邮箱：" + information.getEmail());
        //初始无聚焦
        nameText.setFocusTraversable(false);
        sexText.setFocusTraversable(false);
        ageText.setFocusTraversable(false);
        departmentText.setFocusTraversable(false);
        telText.setFocusTraversable(false);
        emailText.setFocusTraversable(false);
        saveButton.setFocusTraversable(false);
        //文本开发编辑
        nameText.setEditable(true);
        sexText.setEditable(true);
        ageText.setEditable(true);
        departmentText.setEditable(true);
        telText.setEditable(true);
        emailText.setEditable(true);

        if(information.getName() == null)
            nameText.setText("");
        else
            nameText.setText("" + information.getName());
        if(information.getSex() == null)
            sexText.setText("" );
        else
            sexText.setText("" + information.getSex());
        if(information.getAge() == 0)
            ageText.setText("");
        else
            ageText.setText("" + information.getAge());
        if(information.getDepartment() == null)
            departmentText.setText("");
        else
          departmentText.setText("" + information.getDepartment());
        if(information.getTelNumber() == 0)
            telText.setText("");
        else
         telText.setText("" + information.getTelNumber());
        if(information.getEmail() == null)
            emailText.setText("");
        else
            emailText.setText("" + information.getEmail());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void goToHome(MouseEvent mouseEvent) {
        //跳转至员工首页
        if(LogInController.user.isLeader() == false){
            application.goToEmployeeHome();
        }
        //跳转至领导首页
        else{
            application.goToEmployeesPerformance();
        }
    }

    public void saveInformation(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Information information = new Information();
        information.setId(LogInController.user.getId());
        information.setName(nameText.getText());
        information.setSex(sexText.getText());
        information.setAge(Integer.parseInt(ageText.getText()));
        information.setDepartment(departmentText.getText());
        information.setTelNumber(Long.parseLong(telText.getText()));
        information.setEmail(emailText.getText());
        employeeService.saveInformation(information);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("保存成功！");
        alert.showAndWait();
    }
}
