package com.project.Dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.project.huaweiproject.SpringUtil;
import com.project.huaweiproject.Tasks;
import com.project.huaweiproject.Users;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author suat.erdogan
 */
@Configurable
@Repository
public class HuaweiDao implements Serializable {

    Configuration configuration;
    SessionFactory sessionFactory;

    /**
     *
     * @param userName user
     * @param code passsord
     * @return
     */
    public boolean checkLogin(String userName, String code) {
        boolean checkValue = false;
        Session session = null;
        configuration = new Configuration().configure(SpringUtil.class.getResource("/hibernate.cfg.xml"));
        sessionFactory = configuration.buildSessionFactory();

        try {
            session = sessionFactory.openSession();
            String hql = "from Users u where u.username=:username and u.usercode=:usercode";
            Query query = session.createQuery(hql);
            query.setString("username", userName);
            query.setString("usercode", code);

            List result = query.list();
            if (result.size() == 1) {
                checkValue = true;
            }

            session.close();
        } catch (HibernateException e) {
            System.err.println("Hiber Error" + e);
        }
        return checkValue;

    }

    /**
     *
     * @param userName kişi
     * @param code passwors state durumu Tipi admin ve kullanıcı
     *
     */
    public void saveMember(String userName, String code) {

        Session session = null;

        configuration = new Configuration().configure(SpringUtil.class.getResource("/hibernate.cfg.xml"));
        sessionFactory = configuration.buildSessionFactory();

        try {
            session = sessionFactory.openSession();
            String hql = "select max(u.logicalref) from Users u";
            Query query = session.createQuery(hql);
            List result = query.list();
            Users User = new Users();
            int newLogicalref = Integer.parseInt(result.get(0).toString()) + 1;
            User.setLogicalref(newLogicalref);
            User.setUsername(userName);
            User.setUsercode(code);
            User.setState(0);
            User.setType(1);

            Calendar cal = Calendar.getInstance();

            User.setCreatedate(cal.getTime());
            session.save(User);
            session.beginTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.err.println("Hiber Error" + e);
        }

    }

    /**
     *
     * @return Tüm görevler
     */
    public List<Tasks> callTask() {
        List<Tasks> result = null;
        Session session = null;
        configuration = new Configuration().configure(SpringUtil.class.getResource("/hibernate.cfg.xml"));
        sessionFactory = configuration.buildSessionFactory();

        try {
            session = sessionFactory.openSession();
            String hql = "from Tasks";
            Query query = session.createQuery(hql);
            result = query.list();

            session.close();
        } catch (HibernateException e) {
            System.err.println("Hiber Error" + e);
        }
        return result;
    }
    /**
     * 
     * @return Tüm kullanıcılar
     */
    public List<Users> callUser() {
        List<Users> result = null;
        Session session = null;
        configuration = new Configuration().configure(SpringUtil.class.getResource("/hibernate.cfg.xml"));
        sessionFactory = configuration.buildSessionFactory();

        try {
            session = sessionFactory.openSession();
            String hql = "from Users";
            Query query = session.createQuery(hql);
            result = query.list();

            session.close();
        } catch (HibernateException e) {
            System.err.println("Hiber Error" + e);
        }
        return result;
    }
    /**
     * 
     * @param task
     * @param dateline
     * @param begdate
     * @param enddate 
     */
    public void saveTask(String task, Date dateline, Date begdate, Date enddate) {
        Session session = null;

        configuration = new Configuration().configure(SpringUtil.class.getResource("/hibernate.cfg.xml"));
        sessionFactory = configuration.buildSessionFactory();

        try {
            session = sessionFactory.openSession();
            String hql = "select max(t.logicalref) from Tasks t";
            Query query = session.createQuery(hql);
            List result = query.list();
            Tasks Task = new Tasks();
            int newLogicalref = Integer.parseInt(result.get(0).toString()) + 1;
            Task.setLogicalref(newLogicalref);
            Task.setTask(task);
            Task.setBegdate(begdate);
            Task.setEnddate(enddate);
            Task.setDateline(dateline);
            Calendar cal = Calendar.getInstance();
            Task.setCreatedate(cal.getTime());
            Task.setUserref(0);
            Task.setState(0);
            Task.setInjecttaskref(0);
            session.save(Task);
            session.beginTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.err.println("Hiber Error" + e);
        }
    }
    /**
     * 
     * @param selectedTask seçilen task güncellenir
     */
    public void updateTask(Tasks selectedTask) {
        Session session = null;
        configuration = new Configuration().configure(SpringUtil.class.getResource("/hibernate.cfg.xml"));
        sessionFactory = configuration.buildSessionFactory();

        try {
            session = sessionFactory.openSession();
            String hql = "from Tasks t where t.logicalref=:logicalref ";
            Query query = session.createQuery(hql);
            query.setParameter("logicalref", selectedTask.getLogicalref());

            session.update(selectedTask);
            session.beginTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.err.println("Hiber Error" + e);
        }

    }
    /**
     * 
     * @param selectedTask seçilen task silinir
     */
    public void deleteTask(Tasks selectedTask) {
        Session session = null;
        configuration = new Configuration().configure(SpringUtil.class.getResource("/hibernate.cfg.xml"));
        sessionFactory = configuration.buildSessionFactory();

        try {
            session = sessionFactory.openSession();
            String hql = "from Tasks t where t.logicalref=:logicalref ";
            Query query = session.createQuery(hql);
            query.setParameter("logicalref", selectedTask.getLogicalref());

            session.delete(selectedTask);
            session.beginTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.err.println("Hiber Error" + e);
        }

    }

}
