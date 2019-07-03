package entity;
//职工、领导、管理员都是本软件的用户，都在用户类中进行字段定义
public class User {
    //id,作为唯一标识
    private int id;
    //账号
    private String account;
    //密码
    private String password;
    //是否进行了业绩情况和工资情况的确认
    private boolean isConfirm;
    //是否申诉
    private boolean isAppeal;
    //是不是领导
    private boolean isLeader;
    //是不是管理员
    private boolean isAdministrator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConfirm() {
        return isConfirm;
    }

    public void setConfirm(boolean confirm) {
        isConfirm = confirm;
    }
    public boolean isAppeal() {
        return isAppeal;
    }

    public void setAppeal(boolean appeal) {
        isAppeal = appeal;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }
}
