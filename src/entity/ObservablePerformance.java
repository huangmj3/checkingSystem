package entity;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
//该类为可视化绩效展示类，专门用于表格展示绩效信息
public class ObservablePerformance {
    //id,作为唯一标识
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    //姓名
    private SimpleStringProperty name = new SimpleStringProperty();
    //得分
    private SimpleIntegerProperty score = new SimpleIntegerProperty();
    //排名
    private SimpleIntegerProperty ranking = new SimpleIntegerProperty();
    //销售额
    private SimpleIntegerProperty saleroom = new SimpleIntegerProperty();
    //新客户人数
    private SimpleIntegerProperty numnberOfNew = new SimpleIntegerProperty();
    //流失客户人数
    private SimpleIntegerProperty numberOfLost = new SimpleIntegerProperty();
    //客户投诉次数
    private SimpleIntegerProperty numberOfComplain = new SimpleIntegerProperty();
    //客户访问次数
    private SimpleIntegerProperty numberOfVisit = new SimpleIntegerProperty();
    //记录是否申诉
    private SimpleBooleanProperty isAppeal = new SimpleBooleanProperty();
    //申诉内容
    private SimpleStringProperty appealContext = new SimpleStringProperty();

    public int getId() {
        return this.id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public int getRanking() {
        return this.ranking.get();
    }

    public void setRanking(int ranking) {
        this.ranking.set(ranking);
    }

    public int getSaleroom() {
        return saleroom.get();
    }

    public void setSaleroom(int saleroom) {
        this.saleroom.set(saleroom);
    }

    public int getNumnberOfNew() {
        return numnberOfNew.get();
    }

    public void setNumnberOfNew(int numnberOfNew) {
        this.numnberOfNew.set(numnberOfNew);
    }

    public int getNumberOfLost() {
        return numberOfLost.get();
    }

    public void setNumberOfLost(int numberOfLost) {
        this.numberOfLost.set(numberOfLost);
    }

    public int getNumberOfComplain() {
        return numberOfComplain.get();
    }

    public void setNumberOfComplain(int numberOfComplain) {
        this.numberOfComplain.set(numberOfComplain);
    }

    public int getNumberOfVisit() {
        return numberOfVisit.get();
    }

    public void setNumberOfVisit(int numberOfVisit) {
        this.numberOfVisit.set(numberOfVisit);
    }

    public boolean isAppeal() {
        return isAppeal.get();
    }

    public void setAppeal(boolean appeal) {
        isAppeal.set(appeal);
    }

    public String getAppealContext() {
        return appealContext.get();
    }

    public void setAppealContext(String appealContext) {
        this.appealContext.set(appealContext);
    }
}
