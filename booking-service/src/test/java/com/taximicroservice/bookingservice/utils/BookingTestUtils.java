package com.taximicroservice.bookingservice.utils;

import com.taximicroservice.bookingservice.model.dto.BookingStatusResponseDTO;
import com.taximicroservice.bookingservice.model.entity.BookingStatusEntity;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import org.locationtech.jts.geom.GeometryFactory;

import java.time.format.DateTimeFormatter;

public abstract class BookingTestUtils {

    protected final static GeometryFactory GEOMETRY_FACTORY;

    protected final static DateTimeFormatter DATE_TIME_FORMATTER;

    protected final static BookingStatusResponseDTO STATUS_CREATED_DTO;

    protected final static BookingStatusResponseDTO STATUS_ASSIGNED_DTO;

    protected final static BookingStatusResponseDTO STATUS_CANCELED_DTO;

    protected final static BookingStatusResponseDTO STATUS_FINISHED_DTO;

    protected final static BookingStatusResponseDTO STATUS_ABORTED_DTO;

    protected final static BookingStatusResponseDTO STATUS_IN_PROGRESS_DTO;

    protected final static BookingStatusEntity STATUS_CREATED;

    protected final static BookingStatusEntity STATUS_ASSIGNED;

    protected final static BookingStatusEntity STATUS_CANCELED;

    protected final static BookingStatusEntity STATUS_FINISHED;

    protected final static BookingStatusEntity STATUS_ABORTED;

    protected final static BookingStatusEntity STATUS_IN_PROGRESS;

    static {
        GEOMETRY_FACTORY = new GeometryFactory();

        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        STATUS_CREATED_DTO = new BookingStatusResponseDTO();
        STATUS_CREATED = new BookingStatusEntity();
        STATUS_CREATED_DTO.setId("CREATED");
        STATUS_CREATED.setId("CREATED");
        STATUS_CREATED_DTO.setDescription("Booking created");
        STATUS_CREATED.setDescription("Booking created");

        STATUS_ASSIGNED_DTO = new BookingStatusResponseDTO();
        STATUS_ASSIGNED = new BookingStatusEntity();
        STATUS_ASSIGNED_DTO.setId("ASSIGNED");
        STATUS_ASSIGNED.setId("ASSIGNED");
        STATUS_ASSIGNED_DTO.setDescription("Booking assigned to driver");
        STATUS_ASSIGNED.setDescription("Booking assigned to driver");

        STATUS_CANCELED_DTO = new BookingStatusResponseDTO();
        STATUS_CANCELED = new BookingStatusEntity();
        STATUS_CANCELED_DTO.setId("CANCELED");
        STATUS_CANCELED.setId("CANCELED");
        STATUS_CANCELED_DTO.setDescription("Booking canceled");
        STATUS_CANCELED.setDescription("Booking canceled");

        STATUS_FINISHED_DTO = new BookingStatusResponseDTO();
        STATUS_FINISHED = new BookingStatusEntity();
        STATUS_FINISHED_DTO.setId("FINISHED");
        STATUS_FINISHED.setId("FINISHED");
        STATUS_FINISHED_DTO.setDescription("Booking finished");
        STATUS_FINISHED.setDescription("Booking finished");

        STATUS_ABORTED_DTO = new BookingStatusResponseDTO();
        STATUS_ABORTED = new BookingStatusEntity();
        STATUS_ABORTED_DTO.setId("ABORTED");
        STATUS_ABORTED.setId("ABORTED");
        STATUS_ABORTED_DTO.setDescription("Booking aborted");
        STATUS_ABORTED.setDescription("Booking aborted");

        STATUS_IN_PROGRESS_DTO = new BookingStatusResponseDTO();
        STATUS_IN_PROGRESS = new BookingStatusEntity();
        STATUS_IN_PROGRESS_DTO.setId("IN_PROGRESS");
        STATUS_IN_PROGRESS.setId("IN_PROGRESS");
        STATUS_IN_PROGRESS_DTO.setDescription("Booking in progress");
        STATUS_IN_PROGRESS.setDescription("Booking in progress");
    }

    protected static BookingStatusResponseDTO getBookingStatusResponseDTO(BookingStatusEnum bookingStatusEnum) {
        switch (bookingStatusEnum) {
            case CREATED:
                return STATUS_CREATED_DTO;
            case ASSIGNED:
                return STATUS_ASSIGNED_DTO;
            case CANCELED:
                return STATUS_CANCELED_DTO;
            case FINISHED:
                return STATUS_FINISHED_DTO;
            case ABORTED:
                return STATUS_ABORTED_DTO;
            case IN_PROGRESS:
                return STATUS_IN_PROGRESS_DTO;
            default:
                throw new IllegalArgumentException("Booking status \"" + bookingStatusEnum.getName() + "\" is not supported");
        }
    }

    protected static BookingStatusEntity getBookingStatusEntity(BookingStatusEnum bookingStatusEnum) {
        switch (bookingStatusEnum) {
            case CREATED:
                return STATUS_CREATED;
            case ASSIGNED:
                return STATUS_ASSIGNED;
            case CANCELED:
                return STATUS_CANCELED;
            case FINISHED:
                return STATUS_FINISHED;
            case ABORTED:
                return STATUS_ABORTED;
            case IN_PROGRESS:
                return STATUS_IN_PROGRESS;
            default:
                throw new IllegalArgumentException("Booking status \"" + bookingStatusEnum.getName() + "\" is not supported");
        }
    }

}
