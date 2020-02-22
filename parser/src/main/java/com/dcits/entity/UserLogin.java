package com.dcits.entity;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author xuzzb
 * @since 2020-02-20
 */
public class UserLogin extends Model<UserLogin> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    private String password;

    private String userInfo;

    private String userCard;

    private String userCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
        ", id=" + id +
        ", userName=" + userName +
        ", password=" + password +
        ", userInfo=" + userInfo +
        ", userCard=" + userCard +
        ", userCreate=" + userCreate +
        "}";
    }
}
