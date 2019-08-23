package com.ecorau.vbn.queue;

import com.ecorau.vbn.RequestContex;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer {
    private final static Logger logger = Logger.getLogger(Consumer.class);

    public static BlockingQueue queue = new LinkedBlockingQueue<RequestContex>();

    public static void addMessageToQueue(RequestContex requestContex) {
        try {
            queue.put(requestContex);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }
}
