package controllers;

import application.BaseApplication;
import entity.Information;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import service.BaseService;
import service.serviceImpl.BaseServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    public Button registerButton;
    public TextField accountField;
    public TextField passwordField;
    public TextField nameField;
    public TextField sexField;
    public TextField ageField;
    public TextField departmentField;
    public TextField telNumberField;
    public TextField emailField;
    public Label accountCheckLabel;
    private BaseApplication application;
    private BaseService service = new BaseServiceImpl();

//   passwordField.textProperty().addListener(new ChangeListener<String>() {
////        @Override
////        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
////            application.goToLogin();
////        }
////    });

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //获得原本的应用程序窗口
    public void setApp(BaseApplication application){
        this.application = application;
    }
    //实现账号的注册,并返回登陆界面
    public void register(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
//        passwordField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                application.goToLogin();
//            }
//        });
        String account = accountField.getText();
        String password = passwordField.getText();
        //获取注册界面信息
        Information information = new Information();
        information.setName(nameField.getText());
        information.setSex(sexField.getText());
        information.setAge(Integer.parseInt(ageField.getText()));
        information.setDepartment(departmentField.getText());
        String text = telNumberField.getText();
        //电话号码要使用长整型
        Long telNumber = Long.parseLong(text);
        information.setTelNumber(telNumber);
//        information.setTelNumber(19912321L);
        information.setEmail(emailField.getText());
        service.register(account, password, information);
        //提示注册成功
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("注册成功！");
        alert.setHeaderText("");
        alert.setContentText("恭喜你已成功注册！");
        alert.showAndWait();
        //返回登陆界面
        application.goToLogin();
    }
    //键盘改变事件，一旦在账号文本上输入内容，自动检测账号是否已存在
    public void checkAccount(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
//        boolean isExist = service.searchAccount(accountField.getText());
//        //该账号已存在
//        if(isExist){
//            //显示账号已存在
//            accountCheckLabel.setVisible(true);
//            //禁用注册按钮
//            registerButton.setDisable(true);
//        }
//        else{
//            accountCheckLabel.setVisible(false);
//            registerButton.setDisable(false);
//        }
    }

//    public void change(InputMethodEvent inputMethodEvent) {
//        accountField.setText("1");
//    }
//
//    public void change2(KeyEvent keyEvent) {
//        accountField.setText("1");
//    }
}
