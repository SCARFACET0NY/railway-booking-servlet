package com.anton.railway.booking.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class AttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals("index")) {
            HttpSession session = event.getSession();

            session.removeAttribute("selectedWagonClass");
            session.removeAttribute("selectedWagon");
            session.removeAttribute("wagons");
            session.removeAttribute("seats");
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (event.getName().equals("trip")) {
            HttpSession session = event.getSession();

            session.removeAttribute("selectedWagonClass");
            session.removeAttribute("wagons");
            session.removeAttribute("selectedWagon");
            session.removeAttribute("seats");
            session.removeAttribute("selectedSeat");
            session.removeAttribute("ticket");
        }
    }
}
