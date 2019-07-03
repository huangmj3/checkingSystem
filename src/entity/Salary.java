package entity;

public class Salary {
    //id,作为唯一标识
    private int id;
    //总额
    private int totalAmount;
    //绩效额
    private int performanceAmount;
    //其他增减项
    private int otherItem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPerformanceAmount() {
        return performanceAmount;
    }

    public void setPerformanceAmount(int performanceAmount) {
        this.performanceAmount = performanceAmount;
    }

    public int getOtherItem() {
        return this.otherItem;
    }

    public void setOtherItem(int otherItem) {
        this.otherItem = otherItem;
    }
}
