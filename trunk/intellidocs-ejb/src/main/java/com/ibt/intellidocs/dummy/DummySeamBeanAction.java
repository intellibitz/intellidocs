package com.ibt.intellidocs.dummy;

import org.jboss.seam.annotations.Name;

import javax.ejb.Stateless;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA. User: sara Date: Dec 15, 2007 Time: 12:42:47 PM To
 * change this template use File | Settings | File Templates.
 */
@Stateless
@Name("dummySeamBean")
public class DummySeamBeanAction
        implements DummySeamBean
{
    Logger logger = Logger.getLogger("DummySeamBeanAction");

    public void run()
    {
        logger.info("dummySeamBean#run.. Done!");
    }

    public void cancel()
    {
        logger.info("dummySeamBean#cancel.. Done!");
    }

    public void destroy()
    {
        logger.info("dummySeamBean#destroy.. Done!");
    }
}
