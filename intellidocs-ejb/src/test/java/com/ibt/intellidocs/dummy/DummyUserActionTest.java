package com.ibt.intellidocs.dummy;

import org.jboss.deployers.spi.DeploymentException;
import org.jboss.embedded.Bootstrap;
import org.jboss.virtual.plugins.context.vfs.AssembledContextFactory;
import org.jboss.virtual.plugins.context.vfs.AssembledDirectory;

import javax.naming.InitialContext;
import java.util.logging.Logger;

public class DummyUserActionTest
//    extends SeamTest
{

    public DummyUserActionTest()
    {
        super();
    }

    Logger log = Logger.getLogger("DummyUserActionTest");

    private static AssembledDirectory jar;
    private static boolean globalSetup = false;

    private static void deploy()
    {
        jar = AssembledContextFactory.getInstance()
                .create("intellidocs-ejb.jar");
        jar.addClass(DummyUser.class);
        jar.addClass(IDummyUserLocal.class);
        jar.addClass(DummyUserAction.class);
        jar.mkdir("META-INF").addResource("META-INF/persistence.xml");
        try
        {
            Bootstrap.getInstance().deploy(jar);
        }
        catch (DeploymentException e)
        {
            throw new RuntimeException("Unable to deploy", e);
        }
    }

    private static void undeploy()
    {
        try
        {
            Bootstrap.getInstance().undeploy(jar);
            AssembledContextFactory.getInstance().remove(jar);
        }
        catch (DeploymentException e)
        {
            throw new RuntimeException("Unable to undeploy", e);
        }
    }

    //    @Configuration(beforeTestClass = true)
    protected void setUp()
            throws Exception
    {
        if (!Bootstrap.getInstance().isStarted())
        {
            Bootstrap.getInstance().bootstrap();
        }
        deploy();
    }

    //    @Configuration (afterTest = true)
    protected void tearDown()
            throws Exception
    {
        undeploy();
        if (System.getProperty("shutdown.embedded.jboss") != null)
        {
            Bootstrap.getInstance().shutdown();
        }
    }

    //    @Test
    public void testSeamComponents()
            throws Exception
    {
        /*       new ComponentTest(){

                   protected void testComponents()
                           throws Exception
                   {
                       log.info ("Seam components test complete");
                   }
               }.run ();
        */
    }

    //    @Test
    public void testEJBs()
            throws Exception
    {
        InitialContext ctx = new InitialContext();
        IDummyUserLocal local =
                (IDummyUserLocal) ctx.lookup("DummyUserAction/local");
//       CustomerDAORemote remote = (CustomerDAORemote) ctx.lookup("CustomerDAOBean/remote");

        DummyUser user = new DummyUser();
        user.setName("test1");
        user.setPassword("test1");
        local.setVerify("test1");
        local.setDummyUser(user);
        long id = local.register();
        DummyUser cust = local.findDummyUser(id);
        assert (null != cust);
        System.out.println(
                "Successfully created and found user test1 from @Local interface");

/*
       id = remote.createCustomer("Emmanuel");
       cust = remote.findCustomer(id);
       assertNotNull(cust);
       System.out.println("Successfully created and found Emmanuel from @Remote interface");
*/
    }

}
