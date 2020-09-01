package com.taximicroservice.driverservice.service.impl;

import com.taximicroservice.driverservice.exception.DriverServiceException;
import com.taximicroservice.driverservice.exception.ExternalServiceException;
import com.taximicroservice.driverservice.model.DriverAddDTO;
import com.taximicroservice.driverservice.model.DriverResponseDTO;
import com.taximicroservice.driverservice.model.DriverResponseDTOPage;
import com.taximicroservice.driverservice.model.PageRequestDTO;
import com.taximicroservice.driverservice.model.utils.RestPageImpl;
import com.taximicroservice.driverservice.service.DriverService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class DriverServiceImpl implements DriverService {

    @Value(value = "${driverService.kafka.topics.addDriverTopic}")
    private String addDriverTopic;

    @Value(value = "${driverService.kafka.topics.addDriverReplyTopic}")
    private String addDriverReplyTopic;

    @Value(value = "${driverService.kafka.topics.getDriversPageTopic}")
    private String getDriversPageTopic;

    @Value(value = "${driverService.kafka.topics.getDriversPageReplyTopic}")
    private String getDriversPageReplyTopic;

    @Autowired
    private ReplyingKafkaTemplate<String, DriverAddDTO, DriverResponseDTO> addDriverReplyKafkaTemplate;

    @Autowired
    private ReplyingKafkaTemplate<String, PageRequestDTO, DriverResponseDTOPage> replyDriverResponseDTOListListenerContainer;


    @Override
    public RestPageImpl<DriverResponseDTO> getDriversPage(int page, int count) throws DriverServiceException, ExternalServiceException {
        if (page < 0) {
            throw new DriverServiceException("Page must be greater than or equal 0");
        }
        if (count <= 0) {
            throw new DriverServiceException("Count must be greater than 0");
        }

        ProducerRecord<String, PageRequestDTO> record = new ProducerRecord<>(getDriversPageTopic, new PageRequestDTO(page, count));
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, getDriversPageReplyTopic.getBytes()));

        try {
            return replyDriverResponseDTOListListenerContainer.sendAndReceive(record).get().value().getUserResponseDTORestPage();
        } catch (InterruptedException | ExecutionException e) {
            throw new ExternalServiceException("Cannot send object \"" + e.getMessage() + "\"");
        }
    }

    @Override
    public DriverResponseDTO addDriver(DriverAddDTO driverAddDTO) throws ExternalServiceException {
        ProducerRecord<String, DriverAddDTO> record = new ProducerRecord<>(addDriverTopic, driverAddDTO);
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, addDriverReplyTopic.getBytes()));

        try {
            return addDriverReplyKafkaTemplate.sendAndReceive(record).get().value();
        } catch (InterruptedException | ExecutionException e) {
            throw new ExternalServiceException("Cannot send object \"" + e.getMessage() + "\"");
        }
    }

}
