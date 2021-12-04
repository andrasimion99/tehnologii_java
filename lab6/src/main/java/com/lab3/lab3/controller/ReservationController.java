package com.lab3.lab3.controller;

import com.lab3.lab3.DAO.ExamDao;
import com.lab3.lab3.DAO.ResourceDao;
import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Resource;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "reservationController")
@Named
@SessionScoped
public class ReservationController implements Serializable {
    private List<Resource> resources;
    private final Logger logger = Logger.getLogger(getClass().getName());
    @EJB
    private ResourceDao resourceDao;
    @EJB
    private ExamDao examDao;

    public ReservationController() {
        resources = new ArrayList<>();
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public void loadResources() {
        logger.info("Loading resources");
        try {
            resources = resourceDao.getAll();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while loading the resources");
            addErrorMessage(e);
        }
    }

    private void addErrorMessage(Exception e) {
        FacesMessage message = new FacesMessage("Error: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addReservation(Integer examId, String resourceIds) {
        System.out.println("Adding reservation");
        System.out.println(examId);

        List<Integer> ids = new ArrayList<>();
        for (String number : resourceIds.split(",")) {
            ids.add(Integer.parseInt(number));
        }
        System.out.println("IDS:" + ids);

        Exam exam = examDao.getById(examId);
        List<Resource> resourceList = resourceDao.getByIdList(ids);
        boolean ok = true;
        for (Resource r : resourceList) {
            if (!resourceDao.checkAvailability(exam, r)) {
                ok = false;
                break;
            }
        }
        if (ok) {
            exam.setResources(resourceList);
            examDao.update(exam);
        } else {
            logger.log(Level.SEVERE, "The resources are not available");
            addErrorMessage(new Exception("The resources are not available"));
        }

    }
}
