package com.quangduong.orderservice.mapper;

import org.mapstruct.Mapper;

import com.quangduong.orderservice.dto.OrderLineItemDto;
import com.quangduong.orderservice.model.OrderLineItem;

@Mapper(componentModel = "spring")
public interface OrderLineItemMapper {
    OrderLineItem toOrderLineItem(OrderLineItemDto dto);
}
