package net.mynoise.noiseshow.entity;
import java.util.Date;

public class User {
    private long id;
    private String name;
    private String pwd;
    private String sexy;
    private Date birthday;
    private int age;
    private boolean isuse;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd==null?null:pwd.trim();
    }

    public String getSexy() {
        return sexy;
    }

    public void setSexy(String sexy) {
        this.sexy = sexy;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isIsuse() {
        return isuse;
    }

    public void setIsuse(boolean remark) {
        this.isuse = isuse;
    }

    @Override
    public String toString() {
        return "User{" +
                "编号=" + id +
                ", 姓名='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sexy='" + sexy + '\'' +
                ", birthday=" +String.format("%tF",birthday) +
                ",age="+ age+
                // ",age="+ (dt.getYear())+
                ", isuse=" + isuse +
                '}';
    }
}
