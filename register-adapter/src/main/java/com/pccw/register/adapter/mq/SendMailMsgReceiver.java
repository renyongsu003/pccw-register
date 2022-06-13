package com.pccw.register.adapter.mq;

import com.pccw.register.application.impl.RegisterEmailSender;
import com.pccw.register.domain.vo.SendEmailMsg;
import com.pccw.register.domain.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class SendMailMsgReceiver {

    @Autowired
    private RegisterEmailSender registerEmailSender;

    @KafkaListener(topics = "${kafka.email.topic}")
    public void  receive(ConsumerRecord<String,String> consumerRecord, Acknowledgment ack)throws Exception{
        log.info("receive msg : {}" , consumerRecord);
        Optional<String> optional = Optional.ofNullable(consumerRecord.value());
        if(optional.isPresent()){

            String value = optional.get();
            SendEmailMsg sendEmailMsg = JsonUtils.json2Obj(value, SendEmailMsg.class);
            registerEmailSender.sendEmail(sendEmailMsg.getEmail());
        }
        ack.acknowledge();

    }



}
