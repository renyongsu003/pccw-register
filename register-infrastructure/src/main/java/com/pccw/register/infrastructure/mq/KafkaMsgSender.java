package com.pccw.register.infrastructure.mq;

import com.pccw.register.domain.mq.MsgSender;
import com.pccw.register.domain.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class KafkaMsgSender implements MsgSender {

    @Resource
    KafkaTemplate<String,String> kafkaTemplate ;

    @Override
    public void sendMessage(String topic,Object obj){
        try{
            String data  = JsonUtils.obj2Json(obj);
            log.info("send to topic: {} , msg: {}",topic,data);
            kafkaTemplate.send(topic,data);
        }catch(Exception e){

           log.error("send msg error: ",e);
        }
    }


}
