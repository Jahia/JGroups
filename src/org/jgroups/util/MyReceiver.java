package org.jgroups.util;

import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic receiver for a JChannel
 * @author Bela Ban
 * @since  3.3
 */
public class MyReceiver<T> extends ReceiverAdapter {
    protected final List<T> list=new ArrayList<T>();
    protected String        name;
    protected boolean       verbose;

    public void receive(Message msg) {
        T obj=(T)msg.getObject();
        list.add(obj);
        if(verbose) {
            System.out.println((name() != null? name() + ":" : "") + " received message from " + msg.getSrc() + ": " + obj);
        }
    }

    public List<T>       list()                {return list;}
    public MyReceiver<T> verbose(boolean flag) {verbose=flag; return this;}
    public String        name()                {return name;}
    public MyReceiver<T> name(String name)     {this.name=name; return this;}
    public MyReceiver<T> reset()               {list.clear(); return this;}
    public int           size()                {return list.size();}
}
