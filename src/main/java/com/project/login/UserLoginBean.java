/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.login;
 
import com.project.Dao.HuaweiDao;
import java.io.Serializable; 
import javax.enterprise.context.RequestScoped; 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named; 
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author suat.erdogan
 */
@Named(value = "userLoginBean")
@RequestScoped
public class UserLoginBean implements Serializable {

    /**
     * Creates a new instance of UserLoginBean
     */
    private String userName = "";
    private String password = "";

    private HuaweiDao huaweiDao;

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

    public HuaweiDao getHuaweiDao() {
        return huaweiDao;
    }

    @Autowired
    public void setHuaweiDao(HuaweiDao huaweiDao) {
        this.huaweiDao = huaweiDao;
    }
    /**
     * 
     * @return login control
     */
    public String checkUser() {
        huaweiDao = new HuaweiDao();
        Boolean isLogin = huaweiDao.checkLogin(userName, password);
        if (isLogin.equals(true)) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("code", userName);
            //saveAllTask();
            return "mainpage.xhtml?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username or Password is invalid"));
        }
        return "index.xhtml";
    }

    public void saveMember() {
        huaweiDao = new HuaweiDao();
        huaweiDao.saveMember(userName, password);
        
        

    }
    /**
     * 
     * @return oturumu sonlandırır
     */
    public String logout() { 
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("code");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getSession(false);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logout";
    }


}
