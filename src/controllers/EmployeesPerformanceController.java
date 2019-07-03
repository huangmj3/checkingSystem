package controllers;

import application.BaseApplication;
import entity.Performance;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;
import service.AdministratorService;
import service.LeaderService;
import service.serviceImpl.AdministratorServiceImpl;
import service.serviceImpl.LeaderServiceImpl;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeesPerformanceController implements Initializable {
    public Pane myInformationPage;
    public ImageView myImage;
    public TableView appealTable;
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn rankingColumn;
    public TableColumn scoreColumn;
    public TableColumn saleroomColumn;
    public TableColumn numberOfNewColumn;
    public TableColumn numberOfLostColumn;
    public TableColumn numberOfComplainColumn;
    public TableColumn numberOfVisitColumn;
    public TableColumn appealContextColumn;
    public TextField nameField;
    public TextField scoreField;
    public TextField rankingField;
    public TextField saleroomField;
    public TextField numberOfNewField;
    public TextField numberOfLostField;
    public TextField numberOfComplainField;
    public TextField numberOfVisitField;
    public Button addButton;
    public Button clearbutton;
    public Button changeButton;
    public Button rejectButton;
    public Button deleteButton;
    public Text performanceToHome;

    private  BaseApplication application;
    LeaderService leaderService = new LeaderServiceImpl();
    private IntegerProperty index = new SimpleIntegerProperty(); //用于获取所得到的行下标
    //获得行
    public final double getIndex() {
        return index.get();
    }

    public final void setIndex(Integer value) {
        index.set(value);
    }

    public IntegerProperty indexProperty() {
        return index;
    }


    //初始化界面
    public void setApp(BaseApplication application) throws SQLException, ClassNotFoundException {
        this.application = application;
        this.showHome();
    }
    //加载首页信息
    public void showHome() throws SQLException, ClassNotFoundException {
        //管理员借用该界面，增加增加字段功能
        if(LogInController.user.isAdministrator()){
            setItemsVisible();
        }
        //为字符类型变量设置监听工厂
        Callback<TableColumn, TableCell> cellFactoryForString =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCellForString();
                    }
                };
        //为数值类型设置监听工厂
        Callback<TableColumn, TableCell> cellFactoryForInteger =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCellForInteger();
                    }
                };
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));//数据映射，通过PropertyValueFactory指定单元格工厂
        nameColumn.setCellFactory(TextFieldTableCell.<Performance>forTableColumn());   //设置文本域用于编辑
//        nameColumn.setCellFactory(cellFactoryForString);    //设置监听工厂，离开焦点后自动保存
        //事件处理，更新列值并写入数据库
        nameColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Performance, String>>() {
                    @Override
                    public void handle(CellEditEvent<Performance, String> t) {
                        ((Performance) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setName(t.getNewValue());
                        Performance performance = t.getRowValue();
                        //去掉"（申诉！）"字符串
//                        if(performance.getName().contains(("申诉！"))) {
//                            String str = performance.getName().substring(5);
//                            performance.setName(str);
//                        }
                        try {
                            //更新绩效情况
                            leaderService.updatePerformance(performance);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        rankingColumn.setCellValueFactory(new PropertyValueFactory("ranking"));
        rankingColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));   //添加数值类型到字符类型转换器
//        rankingColumn.setCellFactory(cellFactoryForInteger);    //设置监听工厂，离开焦点后自动保存
        //事件处理，更新列值并写入数据库
        rankingColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Performance, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<Performance, Integer> t) {
                        ((Performance) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setRanking(t.getNewValue());
                        Performance performance = t.getRowValue();
                        performance.setRanking(t.getNewValue());
                        try {
                            //更新绩效情况
                            leaderService.updatePerformance(performance);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        scoreColumn.setCellValueFactory(new PropertyValueFactory("score"));
        scoreColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));   //添加数值类型到字符类型转换器
//        scoreColumn.setCellFactory(cellFactoryForInteger);    //设置监听工厂，离开焦点后自动保存
        //事件处理，更新列值并写入数据库
        scoreColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Performance, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<Performance, Integer> t) {
                        ((Performance) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setScore(t.getNewValue());
                        Performance performance = t.getRowValue();
                        performance.setScore(t.getNewValue());
                        try {
                            //更新绩效情况
                            leaderService.updatePerformance(performance);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        saleroomColumn.setCellValueFactory(new PropertyValueFactory("saleroom"));
        saleroomColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));   //添加数值类型到字符类型转换器
//        saleroomColumn.setCellFactory(cellFactoryForInteger);    //设置监听工厂，离开焦点后自动保存
        //事件处理，更新列值并写入数据库
        saleroomColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Performance, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<Performance, Integer> t) {
                        ((Performance) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setSaleroom(t.getNewValue());
                        Performance performance = t.getRowValue();
                        performance.setSaleroom(t.getNewValue());
                        try {
                            //更新绩效情况
                            leaderService.updatePerformance(performance);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        numberOfNewColumn.setCellValueFactory(new PropertyValueFactory("numberOfNew"));
        numberOfNewColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));   //添加数值类型到字符类型转换器
//        numberOfNewColumn.setCellFactory(cellFactoryForInteger);    //设置监听工厂，离开焦点后自动保存
        //事件处理，更新列值并写入数据库
        numberOfNewColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Performance, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<Performance, Integer> t) {
                        ((Performance) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNumberOfNew(t.getNewValue());
                        Performance performance = t.getRowValue();
                        performance.setNumberOfNew(t.getNewValue());
                        try {
                            //更新绩效情况
                            leaderService.updatePerformance(performance);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        numberOfLostColumn.setCellValueFactory(new PropertyValueFactory("numberOfLost"));
        numberOfLostColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));   //添加数值类型到字符类型转换器
//        numberOfLostColumn.setCellFactory(cellFactoryForInteger);    //设置监听工厂，离开焦点后自动保存
        //事件处理，更新列值并写入数据库
        numberOfLostColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Performance, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<Performance, Integer> t) {
                        ((Performance) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNumberOfLost(t.getNewValue());
                        Performance performance = t.getRowValue();
                        performance.setNumberOfLost(t.getNewValue());
                        try {
                            //更新绩效情况
                            leaderService.updatePerformance(performance);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        numberOfComplainColumn.setCellValueFactory(new PropertyValueFactory("numberOfComplain"));
        numberOfComplainColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));   //添加数值类型到字符类型转换器
//        numberOfComplainColumn.setCellFactory(cellFactoryForInteger);    //设置监听工厂，离开焦点后自动保存
        //事件处理，更新列值并写入数据库
        numberOfComplainColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Performance, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<Performance, Integer> t) {
                        ((Performance) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNumberOfComplain(t.getNewValue());
                        Performance performance = t.getRowValue();
                        performance.setNumberOfComplain(t.getNewValue());
                        try {
                            //更新绩效情况
                            leaderService.updatePerformance(performance);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        numberOfVisitColumn.setCellValueFactory(new PropertyValueFactory("numberOfVisit"));
        numberOfVisitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));   //添加数值类型到字符类型转换器
//        numberOfVisitColumn.setCellFactory(cellFactoryForInteger);    //设置监听工厂，离开焦点后自动保存
        //事件处理，更新列值并写入数据库
        numberOfVisitColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Performance, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<Performance, Integer> t) {
                        ((Performance) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNumberOfVisit(t.getNewValue());
                        Performance performance = t.getRowValue();
                        performance.setNumberOfNew(t.getNewValue());
                        try {
                            //更新绩效情况
                            leaderService.updatePerformance(performance);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        appealContextColumn.setCellValueFactory(new PropertyValueFactory("appealContext"));//数据映射，通过PropertyValueFactory指定单元格工厂
        appealContextColumn.setCellFactory(TextFieldTableCell.<Performance>forTableColumn());   //设置文本域用于编辑
//        appealContextColumn.setCellFactory(cellFactoryForString);    //设置监听工厂，离开焦点后自动保存
        //事件处理，更新列值并写入数据库
        appealContextColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Performance, String>>() {
                    @Override
                    public void handle(CellEditEvent<Performance, String> t) {
                        ((Performance) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setAppealContext(t.getNewValue());
                        Performance performance = t.getRowValue();
                        try {
                            //更新绩效情况
                            leaderService.updatePerformance(performance);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        //获取可观察数组
//        ObservableList<ObservablePerformance> data = leaderService.getAllEmployeePerformance(true);
        //通过无参构造方法获得List，再强制转换成为可观察数组
        List<Performance> list = leaderService.getAllEmployeePerformance();
        //遍历显示列，寻找需要显示申诉的列
        for(Performance item : list){
            if(item.isAppeal() == true){
                String name = item.getName();
                item.setName("(申诉！)" + name);
            }
        }
        ObservableList data = (ObservableList)list;
        appealTable.setItems(data);
        appealTable.setEditable(true);
        //增加监听事件，获得当前所聚焦的行下标
        appealTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
//                System.out.println(newValue+"---newvalue");
//                Performance selectedPerson = (Performance) newValue;
                setIndex(data.indexOf(newValue));
//                System.out.println("OK");
            }
        });

        //增加按钮事件处理，增加新列
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Performance performance = new Performance();
                performance.setName(nameField.getText());
                performance.setScore(Integer.parseInt(scoreField.getText()));
                performance.setRanking(Integer.parseInt(rankingField.getText()));
                performance.setSaleroom(Integer.parseInt(saleroomField.getText()));
                performance.setNumberOfNew(Integer.parseInt(numberOfNewField.getText()));
                performance.setNumberOfLost(Integer.parseInt(numberOfLostField.getText()));
                performance.setNumberOfComplain(Integer.parseInt(numberOfComplainField.getText()));
                performance.setNumberOfVisit(Integer.parseInt(numberOfVisitField.getText()));
                data.add(performance);
                //清空所有输入文本框
                clearFields();
            }
        });
        //增加清除按钮事件处理，将除了名字外的所有列值置为0
        clearbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //遍历所有数组
                for(Performance item : list){
                    //去掉申诉标志
                    if(item.getName().contains("申诉！")){
                        String str = item.getName().substring(5);
                        item.setName(str);
                    }
                    item.setScore(0);
                    item.setRanking(0);
                    item.setSaleroom(0);
                    item.setNumberOfNew(0);
                    item.setNumberOfLost(0);
                    item.setNumberOfComplain(0);
                    item.setNumberOfVisit(0);
                    item.setAppeal(false);
                    item.setAppealContext("");
                    try {
                        //提交更新后的结果
                        leaderService.updatePerformance(item);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                List<Performance> list = null;
                try {
                    //重新获取全部数据
                    list = leaderService.getAllEmployeePerformance();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                ObservableList data = (ObservableList)list;
                //更新表格
                appealTable.setItems(data);
                //清空所有文本框
                clearFields();
            }
        });
    }
    //文本清空按钮实现
    private void clearFields(){
        nameField.clear();
        scoreField.clear();
        rankingField.clear();
        saleroomField.clear();
        numberOfNewField.clear();
        numberOfLostField.clear();
        numberOfComplainField.clear();
        numberOfVisitField.clear();
    }

    private void setItemsVisible() {
        nameField.setVisible(true);
        scoreField.setVisible(true);
        rankingField.setVisible(true);
        saleroomField.setVisible(true);
        numberOfNewField.setVisible(true);
        numberOfLostField.setVisible(true);
        numberOfComplainField.setVisible(true);
        numberOfVisitField.setVisible(true);
        addButton.setVisible(true);
        clearbutton.setVisible(true);
        deleteButton.setVisible(true);
        //设置修改按钮和驳回按钮不可见
        changeButton.setVisible(false);
        rejectButton.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void goToInformation(MouseEvent mouseEvent) {
        application.goToInformationForEmployee();
    }

    public void performanceToHome(MouseEvent mouseEvent) {
        //如果是领导则返回管理员首页
        if(LogInController.user.isAdministrator())
            application.goToAdministratorHome();
            //否则返回领导首页
        else {
            application.goToLeaderHome();
        }
    }
    //驳回事件，去除申诉字样，清空申诉内容
    public void reject(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        //获得所选行的下标
         int index = appealTable.getSelectionModel().getFocusedIndex();
        List<Performance> list = leaderService.getAllEmployeePerformance();

        //获得所选行数据
        Performance performance = list.get(index);
        performance.setAppealContext("");
        performance.setAppeal(false);
        performance.setReject(true);
        //更新绩效情况
        leaderService.updatePerformance(performance);
        //改变用户的申诉和确认状态
        leaderService.changeUserAppeal(performance.getId());
        //刷新界面，重新加载
        this.showHome();
//        //增加监听事件，获得当前所聚焦的行下标
//        appealTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
//            @Override
//            public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
////                System.out.println(newValue+"---newvalue");
////                Performance selectedPerson = (Performance) newValue;
//                setIndex(data.indexOf(newValue));
////                System.out.println("OK");
//            }
//        });
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("驳回成功！");
        alert.showAndWait();
    }

    public void changePerformance(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("修改成功！");
        alert.showAndWait();
        //获得所选行的下标
        int index = appealTable.getSelectionModel().getFocusedIndex();
        List<Performance> list = leaderService.getAllEmployeePerformance();
        //获得所选行数据
        Performance performance = list.get(index);
        performance.setAppealContext("");
        performance.setAppeal(false);
        //更新绩效情况
        leaderService.updatePerformance(performance);
        //改变用户的申诉和确认状态
        leaderService.changeUserAppeal(performance.getId());
    }
    //删除所选行按钮实现
    public void deleteRow(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        //获得所选行的下标
        int index = appealTable.getSelectionModel().getFocusedIndex();
        List<Performance> list = leaderService.getAllEmployeePerformance();

        //获得所选行数据
        Performance performance = list.get(index);
//        System.out.println(performance.getId());
//        System.out.println(performance.getAppealContext());
        AdministratorService administratorService = new AdministratorServiceImpl();
//        employeesPerformanceController.deletePerformance();
        administratorService.hidePerformance(performance);
        //刷新界面，重新加载
        this.showHome();
    }

    //字符类型单元格编辑类
    class EditingCellForString extends TableCell<Performance, String> {

        private TextField textField;

        public EditingCellForString() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0,
                                    Boolean arg1, Boolean arg2) {
                    if (!arg2) {
                        commitEdit(textField.getText());
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
    //数值类型单元格编辑类
    class EditingCellForInteger extends TableCell<Performance, String> {

        private TextField textField;

        public EditingCellForInteger() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0,
                                    Boolean arg1, Boolean arg2) {
                    if (!arg2) {
                        commitEdit(textField.getText());
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}
