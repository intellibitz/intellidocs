package com.ibt.intellidocs.dummy;

import javax.ejb.Local;

/**
 * Created by IntelliJ IDEA. User: sara Date: Dec 15, 2007 Time: 12:40:14 PM To
 * change this template use File | Settings | File Templates.
 */
@Local
public interface DummySeamBean
{
    public void run();

    public void cancel();

    public void destroy();
}
