package com.company.transportapp.mapper;

import com.company.transportapp.dto.TransportResponseDTO;
import com.company.transportapp.model.entities.Transport;

import java.util.stream.Collectors;

public class TransportMapper {

    public static TransportResponseDTO toResponse(Transport t) {
        return new TransportResponseDTO(
                t.getId(),

                t.getTruck().getId(),
                t.getTruck().getPlateNumber(),

                t.getTrailer() != null ? t.getTrailer().getId() : null,
                t.getTrailer() != null ? t.getTrailer().getPlateNumber() : null,

                t.getDriver().getId(),
                t.getDriver().getFirstName() + " " + t.getDriver().getLastName(),

                t.getType(),
                t.getDirection(),

                t.getCargoWeight(),
                t.getStatus(),

                t.getCreatedAt(),
                t.getFinishedAt(),

                t.getDriverNotes(),
                t.getDispatcherNotes(),

                t.getCargoItems()
                        .stream()
                        .map(CargoItemMapper::toResponse)
                        .collect(Collectors.toList())
        );
    }
}
