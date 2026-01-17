package com.company.transportapp.mapper;

import com.company.transportapp.dto.CargoItemRequestDTO;
import com.company.transportapp.dto.CargoItemResponseDTO;
import com.company.transportapp.model.entities.CargoItem;
import com.company.transportapp.model.entities.Container;

public class CargoItemMapper {

    public static CargoItem toEntity(CargoItemRequestDTO dto, Container container) {
        CargoItem item = new CargoItem();
        item.setItemType(dto.getItemType());
        item.setContainer(container);
        item.setQuantity(dto.getQuantity());
        item.setCargoWeight(dto.getCargoWeight());
        return item;
    }

    public static CargoItemResponseDTO toResponse(CargoItem item) {
        return new CargoItemResponseDTO(
                item.getId(),
                item.getItemType(),
                item.getContainer() != null ? item.getContainer().getId() : null,
                item.getContainer() != null ? item.getContainer().getContainerNumber() : null,
                item.getContainer() != null ? item.getContainer().getSize() : null,
                item.getContainer() != null ? item.getContainer().getSealNumber() : null,
                item.getContainer() != null ? item.getContainer().getTareWeight() : null,
                item.getQuantity(),
                item.getCargoWeight()
        );
    }
}
