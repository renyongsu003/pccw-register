package com.pccw.register.domain.mq;

public interface MsgSender {

    void sendMessage(String topic,Object obj);
}
