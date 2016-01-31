package com.z.listener;

import java.util.ArrayList;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.z.model.User;

/**
 * return all the online login user name.
 *
 * @author User
 * @return ArrayList onlineList
 */

public class OnlineUserListener implements HttpSessionAttributeListener, HttpSessionListener {

    private static ArrayList<User> onlineList = new ArrayList<User>();

    /**
     * add user while people login and the session is being used.
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent arg0) {
        //
        //log.debug("=========2star===========");
        String username = (String) arg0.getSession().getAttribute("username");
        // String[] atts = new String[]{username,emailaddess};
        User user = new User();
        user.setUsername(username);
        onlineList.add(user);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {

    }

    /**
     * delete user while people logout or the session is being destroy
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        //
        String username = (String) arg0.getSession().getAttribute("username");
        onlineList.remove(username);
    }

    public static ArrayList getUsers() {
        return onlineList;
    }
}
