package com.taximicroservice.passengerservice.service.impl;

import com.taximicroservice.passengerservice.exception.ExternalServiceException;
import com.taximicroservice.passengerservice.exception.PassengerServiceException;
import com.taximicroservice.passengerservice.model.PageRequestDTO;
import com.taximicroservice.passengerservice.model.PassengerAddDTO;
import com.taximicroservice.passengerservice.model.PassengerResponseDTO;
import com.taximicroservice.passengerservice.model.PassengerResponseDTOPage;
import com.taximicroservice.passengerservice.model.utils.RestPageImpl;
import com.taximicroservice.passengerservice.service.PassengerService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Value(value = "${passengerService.kafka.topics.addPassengerTopic}")
    private String addPassengerTopic;

    @Value(value = "${passengerService.kafka.topics.addPassengerReplyTopic}")
    private String addPassengerReplyTopic;

    @Value(value = "${passengerService.kafka.topics.getPassengersPageTopic}")
    private String getPassengersPageTopic;

    @Value(value = "${passengerService.kafka.topics.getPassengersPageReplyTopic}")
    private String getPassengersPageReplyTopic;

    @Autowired
    private ReplyingKafkaTemplate<String, PassengerAddDTO, PassengerResponseDTO> addPassengerReplyKafkaTemplate;

    @Autowired
    private ReplyingKafkaTemplate<String, PageRequestDTO, PassengerResponseDTOPage> replyPassengerResponseDTOListListenerContainer;


    @Override
    public RestPageImpl<PassengerResponseDTO> getPassengersPage(int page, int count) throws PassengerServiceException, ExternalServiceException {
        if (page < 0) {
            throw new PassengerServiceException("Page must be greater than or equal 0");
        }
        if (count <= 0) {
            throw new PassengerServiceException("Count must be greater than 0");
        }

        ProducerRecord<String, PageRequestDTO> record = new ProducerRecord<>(getPassengersPageTopic, new PageRequestDTO(page, count));
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, getPassengersPageReplyTopic.getBytes()));

        try {
            return replyPassengerResponseDTOListListenerContainer.sendAndReceive(record).get().value().getUserResponseDTORestPage();
        } catch (InterruptedException | ExecutionException e) {
            throw new ExternalServiceException("Cannot send object \"" + e.getMessage() + "\"");
        }
    }

    @Override
    public PassengerResponseDTO addPassenger(PassengerAddDTO passengerAddDTO) throws ExternalServiceException {
        ProducerRecord<String, PassengerAddDTO> record = new ProducerRecord<>(addPassengerTopic, passengerAddDTO);
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, addPassengerReplyTopic.getBytes()));

        try {
            return addPassengerReplyKafkaTemplate.sendAndReceive(record).get().value();
        } catch (InterruptedException | ExecutionException e) {
            throw new ExternalServiceException("Cannot send object \"" + e.getMessage() + "\"");
        }
    }

}
