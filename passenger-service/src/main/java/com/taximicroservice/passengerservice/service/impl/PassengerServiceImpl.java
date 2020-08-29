package com.taximicroservice.passengerservice.service.impl;

import com.taximicroservice.passengerservice.exception.PassengerServiceException;
import com.taximicroservice.passengerservice.model.PassengerAddDTO;
import com.taximicroservice.passengerservice.model.PassengerResponseDTO;
import com.taximicroservice.passengerservice.service.PassengerService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.concurrent.ExecutionException;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Value(value = "${passengerService.kafka.topics.addPassengerTopic}")
    private String addPassengerTopic;

    @Value(value = "${passengerService.kafka.topics.addPassengerReplyTopic}")
    private String addPassengerReplyTopic;

    @Autowired
    private ReplyingKafkaTemplate<String, PassengerAddDTO, PassengerResponseDTO> addPassengerReplyKafkaTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<PassengerResponseDTO> getPassengersPage(int page, int count) throws PassengerServiceException {
        return null;
    }

    @Override
    public PassengerResponseDTO getPassengerById(Long passengerId) throws EntityNotFoundException {
        return null;
    }

    @Override
    public PassengerResponseDTO addPassenger(PassengerAddDTO passengerAddDTO) throws PassengerServiceException {
        ProducerRecord<String, PassengerAddDTO> record = new ProducerRecord<>(addPassengerTopic, passengerAddDTO);
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, addPassengerReplyTopic.getBytes()));
        try {
            return addPassengerReplyKafkaTemplate.sendAndReceive(record).get().value();
        } catch (InterruptedException | ExecutionException e) {
            throw new PassengerServiceException("Cannot send object \"" + e.getMessage() + "\"");
        }
    }

}
