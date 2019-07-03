package controllers;

import entity.Performance;
import entity.Salary;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import service.EmployeeService;
import service.serviceImpl.EmployeeServiceImpl;
import application.BaseApplication;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
//员工首页控制类
public class EHomeController implements Initializable {
    public Pane myInformationPage;
    public ImageView myImage;
    public Text scoreText;
    public Text rankingText;
    public Text saleroomText;
    public Text numberOfNewText;
    public Text numberOfLostText;
    public Text numberOfComplainText;
    public Text numberOfVisitText;
    public Text totalAmountText;
    public Text performanceAmountText;
    public Text otherItemText;
    public Button employeeConfirmButton;
    public Button employeeAppealButton;

    //    Image image = new Image("application/homeImage/myInformation.jpg");
    private  BaseApplication application;
    private  EmployeeService employeeService = new EmployeeServiceImpl();
    //获取唯一标识Id
    int id = LogInController.user.getId();
    Salary salary = new Salary();
    Performance performance = new Performance();

    public void setApp(BaseApplication application) throws SQLException, ClassNotFoundException {
        this.application = application;
        this.showHome();
        //设置初始无聚焦
        employeeConfirmButton.setFocusTraversable(false);
        employeeAppealButton.setFocusTraversable(false);
    }
    //获取并展示首页信息
    public void showHome() throws SQLException, ClassNotFoundException {
        //获取工资情况
        salary = employeeService.getSalary(id);
        //展示工资情况
        showSalary();
        //获取业绩情况
        performance = employeeService.getPerformance(id);
        //展示业绩情况
        showPerformance();
        //显示驳回弹窗
        if(performance.isReject()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("驳回！");
            alert.setContentText("你的请求已经被驳回！");
            alert.showAndWait();
            //更新驳回情况
            employeeService.setReject(LogInController.user.getId());
        }
        //根据确认和申诉情况初始化按钮
        setButtons();
}

    public void setButtons() throws SQLException, ClassNotFoundException {
        boolean isConfirmOrAppeal = employeeService.isConfirmOrAppeal(id);
        //已确认或已申诉，则禁用按钮
        if (isConfirmOrAppeal){
            employeeConfirmButton.setDisable(true);
            employeeAppealButton.setDisable(true);
        }
        //开启按钮
        else{
            employeeConfirmButton.setDisable(false);
            employeeAppealButton.setDisable(false);
        }
    }

    public void showSalary(){
        totalAmountText.setText("总额 " + salary.getTotalAmount());
        performanceAmountText.setText("绩效额 " + salary.getPerformanceAmount());
        otherItemText.setText("(其他增减项 " + salary.getOtherItem() + " )");

    }

    public void showPerformance(){
        scoreText.setText("得分 " + performance.getScore());
        rankingText.setText("排名 " + performance.getRanking());
        saleroomText.setText("销售额 " + performance.getSaleroom());
        numberOfNewText.setText("新客户人数 " + performance.getNumberOfNew());
        numberOfLostText.setText("流失客户人数 " + performance.getNumberOfLost());
        numberOfComplainText.setText("客户投诉次数 " + performance.getNumberOfComplain());
        numberOfVisitText.setText("客户访问次数 " + performance.getNumberOfVisit());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    //首页确认按钮点击事件，用于禁用申诉按钮
    public void employeeConfirm(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText("是否确认？");
        alert.setContentText("确认后将无法申诉！");
        //对话框消失之前一直等待
        Optional<ButtonType> result = alert.showAndWait();
        //按下确认后禁用申诉按钮
        if(result.get() == ButtonType.OK){
            //记录下已确认信息，无法再次确认
            employeeService.setConfirmed(id);
            //按钮可用性取消
            employeeConfirmButton.setDisable(true);
            employeeAppealButton.setDisable(true);
        }
        else{ }
    }

    public void employeeAppeal(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("申诉");
        dialog.setHeaderText("申诉情况将提交至领导");
        dialog.setContentText("请输入你的申诉内容：");
        Optional<String> result = dialog.showAndWait();
        //获得申诉内容
        String appealContext = result.get();
        //用户按下了确认申诉
        if(result.isPresent() == true){
            employeeService.setAppealed(id, appealContext);
            employeeConfirmButton.setDisable(true);
            employeeAppealButton.setDisable(true);
        }
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("申诉");
//        alert.setHeaderText("是否申诉");
//        alert.setContentText("申诉情况将提交至领导");
//        Optional<ButtonType> result = dialog.showAndWait();
//        if(result.get() == ButtonType.OK){
//            employeeService.setAppealed(id);
//            employeeConfirmButton.setDisable(true);
//            employeeAppealButton.setDisable(true);
//        }
        else{ }
    }

    public void myInformation(MouseEvent mouseEvent) {
        application.goToInformationForEmployee();
    }
}
