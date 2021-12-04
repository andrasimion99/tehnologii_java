package com.lab3.lab3.DAO;

import com.lab3.lab3.entity.Exam;
import com.lab3.lab3.entity.Resource;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Stateless(name = "resourceDao")
@Stateful
@LocalBean
public class ResourceDao extends AbstractDao<Resource>{
    public ResourceDao() {
        super(Resource.class);
    }

    public List<Resource> getAll() {
        return super.findAll("Resource.findAll");
    }

    public void add(Resource resource) {
        super.persist(resource);
        super.refresh(resource);
    }

    public List<Resource> getByIdList(List<Integer> ids) {
        List<Resource> resources = new ArrayList<>();
        for (Integer id : ids) {
            resources.add(super.findById(id));
        }
        return resources;
    }

    public boolean checkAvailability(Exam exam, Resource resource)
    {
        Date startDate = exam.getStartingDate();
        Date finishDateExam = Date.from(LocalDate.from(startDate.toInstant().plus(exam.getDuration(), ChronoUnit.MINUTES)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        for(Exam e:resource.getExams())
        {
            Date finishDate = Date.from(LocalDate.from(e.getStartingDate().toInstant().plus(e.getDuration(), ChronoUnit.MINUTES)).atStartOfDay(ZoneId.systemDefault()).toInstant());
            if(!(startDate.after(finishDate) || finishDateExam.before(e.getStartingDate())))
            {
                return false;
            }
        }
        return true;
    }
}
