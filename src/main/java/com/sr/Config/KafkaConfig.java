package com.sr.Config;

//import com.sr.Constants.AppConstant;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.config.TopicBuilder;
//
//@Configuration
//@Slf4j
//public class KafkaConfig {
//
//    @Bean
//    public NewTopic topic(){
//        return TopicBuilder.name(AppConstant.LOCATION_TOPIC_NAME)
////                .partitions(2)
//                .build();
//    }
//
//
//    @KafkaListener(topics = AppConstant.LOCATION_UPDATE_TOPIC ,groupId = AppConstant.GROUP_ID)
//    public void kafkaConsumer(){
//        log.info("Location Consumed By Consumer");
//
//    }
//}
