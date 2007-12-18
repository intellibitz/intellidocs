package com.ibt.intellidocs.dummy;

import org.jboss.seam.annotations.In;
import org.jboss.seam.mock.SeamTest;
import org.testng.annotations.Test;

import java.util.logging.Logger;


/**
 * NOTE: if extends SeamTest, an embedded jboss container is required to run
 * tests
 */
public class DummySeamBeanActionTest
        extends SeamTest
{
    Logger logger = Logger.getLogger("DummySeamBeanActionTest");

    // the dummy bean lives in stateless context
    // hence must be created here to access
    @In(create = true)
    DummySeamBean dummySeamBean;
/*
    DummySeamBean dummySeamBean = (DummySeamBean)
            Component.getInstance(DummySeamBeanAction.class);
*/

    @Test
    public void testRun()
            throws Exception
    {

        //todo: fix me!
        // this is throwing null pointer.. seam filter is null apparently
/*
        new NonFacesRequest(){

        }.run ();
*/

/*
        new FacesRequest() {

           @Override
           protected void invokeApplication() throws Exception
           {
              Contexts.getSessionContext().set("dummySeamBean",
                      new DummySeamBeanAction());
           }

        }.run();
*/

        new ComponentTest()
        {
            protected void testComponents()
                    throws Exception
            {
//                log.info (Contexts.getSessionContext().getNames().toString());
//                setValue ("#{dummySeamBean}", new DummySeamBeanAction());
//                log.info(super.getValue("dummySeamBean").toString());
/*
                Context seamApplicationContext =
                        Contexts.getApplicationContext();
                Context seamConversationContext =
                        Contexts.getConversationContext();

                log.info(seamApplicationContext.toString());
                log.info(seamConversationContext.get("dummySeamBean").toString());
*/
                // since the test class is not a seam component, new instance must be
                // created manually
/*
                DummySeamBean dummySeamBean = (DummySeamBean)
                        Component.getInstance(DummySeamBeanAction.class);
*/
/*
                assert null != dummySeamBean;
                dummySeamBean.run ();
*/
            }
        }.run();
    }

}
