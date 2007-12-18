package com.ibt.intellidocs.dummy;

import static org.jboss.seam.ScopeType.EVENT;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: sara Date: Dec 15, 2007 Time: 12:42:47 PM To
 * change this template use File | Settings | File Templates.
 */
@Stateful
@Scope(EVENT)
@Name("dummySeamBean")
public class DummySeamBeanAction
        implements DummySeamBean, Serializable
{
    @Logger
    Log log;

    @In
    private User dummyUser;

    @PersistenceContext
    private EntityManager entityManager;

    private String verify;
    private boolean registered;

    public void register()
    {
        if (dummyUser.getPassword().equals(verify))
        {
            // List existing = entityManager.createQuery("select u.username from User u where u.username=:username")
            //   .setParameter("username", dummyUser.getUsername())
            List existing = entityManager.createQuery
                    ("select u.username from User u where u.username=#{user.username}")
                    .getResultList();
            if (existing.size() == 0)
            {
                entityManager.persist(dummyUser);
                // facesMessages.add("Successfully registered as #{dummyUser.username}");
                log.info("Username #{dummyUser.username} already exists");
                registered = true;
            }
            else
            {
                log.info("Username #{dummyUser.username} already exists");
            }
        }
        else
        {
            log.info("Re-enter your password");
            verify = null;
        }
    }

    public void invalid()
    {
        log.info("Please try again");
    }

    public boolean isRegistered()
    {
        return registered;
    }

    public String getVerify()
    {
        return verify;
    }

    public void setVerify(String verify)
    {
        this.verify = verify;
    }

    public void run()
    {
        log.info("dummySeamBean#run.. Done!");
    }

    public void cancel()
    {
        log.info("dummySeamBean#cancel.. Done!");
    }

    @Destroy
    @Remove
    public void destroy()
    {
        log.info("dummySeamBean#destroy.. Done!");
    }


}
