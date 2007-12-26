package com.ibt.intellidocs.dummy;

import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.mock.SeamTest;
import org.jboss.virtual.plugins.context.vfs.AssembledDirectory;

import javax.naming.InitialContext;
import java.util.logging.Logger;

public class DummyUserActionTest
        extends SeamTest
{

    public DummyUserActionTest()
    {
        super();
    }

    Logger log = Logger.getLogger("DummyUserActionTest");

    private static AssembledDirectory jar;
    private static boolean globalSetup = false;

    @Override
    protected void startJbossEmbeddedIfNecessary()
            throws Exception
    {
    }

    //    @Test
    public void testSeamComponents()
            throws Exception
    {
        new ComponentTest()
        {

            protected void testComponents()
                    throws Exception
            {
                InitialContext ctx = new InitialContext();
                IDummyUserLocal local =
                        (IDummyUserLocal) ctx.lookup("DummyUserAction/local");
/*
                IDummyUserLocal local = (IDummyUserLocal)
//                               Contexts.getApplicationContext().get("dummyUserAction");
                             getInstance("DummyUserAction/local");
*/
                DummyUser user = new DummyUser();
                user.setName("test1");
                user.setPassword("test1");
                Contexts.getSessionContext().set("dummyUser", user);
                setValue("#{dummyUser.username}", "test1");
                local.setVerify("test1");
                local.setDummyUser(user);
                long id = local.register();
                DummyUser cust = local.findDummyUser(id);
                assert (null != cust);
                System.out.println(
                        "Successfully created and found user test1 from @Local interface");
                log.info("Seam components test complete");

            }
        }.run();


    }

}
