package com.ecorau.vbn;

import com.ecorau.vbn.queue.Consumer;
import com.ecorau.vbn.queue.Producer;
import com.ecorau.vbn.sctp.AppConfig;
import com.ecorau.vbn.sctp.SctpClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VbnMain {
    public static void main(String[] args) throws Exception {
        //connect SCTP
        new Thread(SctpClient.getInstance()).start();

        //Consumer that process message
        Consumer consumer = new Consumer(Producer.queue);
        int numberOfThread = AppConfig.getInstance().getNumberOfThread();
        ExecutorService executionException = Executors.newFixedThreadPool(numberOfThread);

        for (int i = 0; i < numberOfThread; i++) {
            executionException.execute(consumer);
        }
    }
}
