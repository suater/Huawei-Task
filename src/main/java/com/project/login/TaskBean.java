/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.login;

import com.project.huaweiproject.Tasks;
import com.project.huaweiproject.Users;
import com.project.Dao.HuaweiDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named; 
import org.primefaces.event.SelectEvent;

/**
 *
 * @author suat.erdogan
 */
@Named(value = "taskBean")
@SessionScoped
public class TaskBean implements Serializable {

    private List<Tasks> listTasks;
    private List<Users> listUsers;
    private Tasks selectedTask;
    private Users selectedUser;
    private String task;
    private Date begdate;
    private Date enddate;
    private Date dateline;
    private List<String> listUserName;
    private boolean controlUpState = false;
    private List<Tasks> listFilter = new ArrayList<>();

    /**
     * Creates a new instance of TaskBean
     */
    public TaskBean() {
    }

    public List<Tasks> getListTasks() {
        return listTasks;
    }

    public void setListTasks(List<Tasks> listTasks) {
        this.listTasks = listTasks;
    }

    public List<Tasks> getListFilter() {
        return listFilter;
    }

    public void setListFilter(List<Tasks> listFilter) {
        this.listFilter = listFilter;
    }

    public List<Users> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<Users> listUsers) {
        this.listUsers = listUsers;
    }

    public Tasks getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Tasks selectedTask) {
        this.selectedTask = selectedTask;
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<String> getListUserName() {
        return listUserName;
    }

    public void setListUserName(List<String> listUserName) {
        this.listUserName = listUserName;
    }

    public boolean isControlUpState() {
        return controlUpState;
    }

    public void setControlUpState(boolean controlUpState) {
        this.controlUpState = controlUpState;
    }

    @PostConstruct
    public void saveAllTask() {
        HuaweiDao huaweiDao = new HuaweiDao();
        listTasks = huaweiDao.callTask();
        listFilter = new ArrayList<>(listTasks);
        listUsers = huaweiDao.callUser();

    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getBegdate() {
        return begdate;
    }

    public void setBegdate(Date begdate) {
        this.begdate = begdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getDateline() {
        return dateline;
    }

    public void setDateline(Date dateline) {
        this.dateline = dateline;
    }

    public void newTask() {
        task = "";
        Calendar cal = Calendar.getInstance();
        dateline = cal.getTime();
        begdate = cal.getTime();
        enddate = cal.getTime();

    }
     /**
      * Güncelleme
      */
    public void updateTask() {
        HuaweiDao huaweiDao = new HuaweiDao();
        huaweiDao.updateTask(selectedTask);
        newTask();

    }
    /**
     * silme
     */
    public void deleteTask() {
        HuaweiDao huaweiDao = new HuaweiDao();
        huaweiDao.deleteTask(selectedTask);
        newTask();

    }
   /**
    * 
    * @param selectTaskRef seçilen görev ve göreve ait kullanıcı bilgilisini alır
    */
    public void selectTask(int selectTaskRef) {

        controlUpState = false;
        for (Tasks temp : listTasks) {
            if (temp.getLogicalref() == selectTaskRef) {
                selectedTask = temp;
                break;
            }
        }

        for (Users temp : listUsers) {
            if (temp.getLogicalref() == selectedTask.getUserref()) {
                selectedUser = temp;
                break;
            }
        }

        if (selectedTask.getInjecttaskref() != 0) {
            for (Tasks temp : listTasks) {
                if (temp.getLogicalref() == selectedTask.getInjecttaskref()) {
                    if (temp.getState() != 0) {
                        controlUpState = true;
                    }
                    break;
                }
            }
        }
    }
    /**
     * yeni görevi kayıt eder
     */
    public void saveNewTask() {
        HuaweiDao huaweiDao = new HuaweiDao();
        huaweiDao.saveTask(task, dateline, begdate, enddate);
        newTask();
    }
     /**
      * 
      * @param event data tablede seçilen görev referansını alır
      */
    public void rowSelected(SelectEvent event) {
        Tasks task = (Tasks) event.getObject();
        System.err.println(task.getLogicalref());  
    }

}
