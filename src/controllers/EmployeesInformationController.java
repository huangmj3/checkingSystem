package controllers;

import application.BaseApplication;
import entity.Information;
import entity.ObservablePerformance;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import service.LeaderService;
import service.serviceImpl.LeaderServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeesInformationController implements Initializable {
    public TableColumn nameColumn;
    public TableColumn sexColumn;
    public TableColumn ageColumn;
    public TableColumn departmentColumn;
    public TableColumn telNumberColumn;
    public TableColumn emailColumn;
    public Button changeButton;
    public Text informationToHomeButton;
    public TableView informationTable;

    BaseApplication application;
    LeaderService leaderService = new LeaderServiceImpl();

    public void setApp(BaseApplication application) throws SQLException, ClassNotFoundException {
        this.application = application;
        this.showHome();
    }

    private void showHome() throws SQLException, ClassNotFoundException {
        nameColumn.setCellValueFactory(new PropertyValueFactory("name")); //列数据映射
        nameColumn.setCellFactory(TextFieldTableCell.<Information>forTableColumn());  //设置文本域用于编辑
        //        //事件处理，更新列值并写入数据库
        nameColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Information, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Information, String> t) {
                        ((Information) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setName(t.getNewValue());
                        Information information = t.getRowValue();
                    }
                }
        );

        sexColumn.setCellValueFactory(new PropertyValueFactory("sex"));
        ageColumn.setCellValueFactory(new PropertyValueFactory("age"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory("department"));
        telNumberColumn.setCellValueFactory(new PropertyValueFactory("telNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory("email"));

        List<Information> list = leaderService.getAllEmployeeInformation();
//        //遍历列，是年龄和电话号码可以显示为空
//        for(Information item : list){
//            if(item.getAge() == 0 || item.getTelNumber() == 0){
//                item.setName("(申诉！)" + name);
//            }
//        }
        //将得到的关于个人信息的ArrayList转成可观察数组形式
        ObservableList data = (ObservableList)list;
        informationTable.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void informationToHome(MouseEvent mouseEvent) {
        //如果是领导则返回管理员首页
        if(LogInController.user.isAdministrator())
            application.goToAdministratorHome();
        //否则返回领导首页
        else {
            application.goToLeaderHome();
        }
    }

    public void changeInformation(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("修改成功！");
        alert.showAndWait();
    }
}
