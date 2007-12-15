package com.ibt.intellidocs.dummy;

import org.jboss.seam.annotations.In;
import org.jboss.seam.mock.SeamTest;
import org.testng.annotations.Test;


/**
 * Created by IntelliJ IDEA. User: sara Date: Dec 15, 2007 Time: 1:13:26 PM To
 * change this template use File | Settings | File Templates.
 */
public class DummySeamBeanActionTest
        extends SeamTest
{
    @In
    DummySeamBean dummySeamBean;

    @Test
    public void testRun()
    {
/*
        dummySeamBean = (DummySeamBean)
                super.getInstance("dummySeamBean");
        dummySeamBean = (DummySeamBean)
                Contexts.getApplicationContext().get("dummySeamBean");
        assert null != dummySeamBean;
        dummySeamBean.run ();
*/
    }

}
