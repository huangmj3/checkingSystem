package entity;
//业绩情况实体类，只有职工才会有业绩情况
public class Performance {
    //id,作为唯一标识
    private int id;
    //姓名
    private int score;
    //排名
    private String name;
    //得分
    private int ranking;
    //销售额
    private int saleroom;
    //新客户人数
    private int numberOfNew;
    //流失客户人数
    private int numberOfLost;
    //客户投诉次数
    private int numberOfComplain;
    //客户访问次数
    private int numberOfVisit;
    //记录是否申诉
    private boolean isAppeal;
    //申诉内容
    private String appealContext;
    //是否隐藏该绩效列
    private boolean isHide;
    //是否被驳回
    private boolean isReject;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRanking() {
        return this.ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSaleroom() {
        return saleroom;
    }

    public void setSaleroom(int saleroom) {
        this.saleroom = saleroom;
    }

    public int getNumberOfNew() {
        return numberOfNew;
    }

    public void setNumberOfNew(int numberOfNew) {
        this.numberOfNew = numberOfNew;
    }

    public int getNumberOfLost() {
        return numberOfLost;
    }

    public void setNumberOfLost(int numberOfLost) {
        this.numberOfLost = numberOfLost;
    }

    public int getNumberOfComplain() {
        return numberOfComplain;
    }

    public void setNumberOfComplain(int numberOfComplain) {
        this.numberOfComplain = numberOfComplain;
    }

    public int getNumberOfVisit() {
        return numberOfVisit;
    }

    public void setNumberOfVisit(int numberOfVisit) {
        this.numberOfVisit = numberOfVisit;
    }

    public boolean isAppeal() {
        return isAppeal;
    }

    public void setAppeal(boolean appeal) {
        isAppeal = appeal;
    }

    public String getAppealContext() {
        return appealContext;
    }

    public void setAppealContext(String appealContext) {
        this.appealContext = appealContext;
    }

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public boolean isReject() {
        return isReject;
    }

    public void setReject(boolean reject) {
        isReject = reject;
    }
}
