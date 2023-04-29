package com.quangduong.orderservice.mapper;

import com.quangduong.orderservice.dto.OrderLineItemDto;
import com.quangduong.orderservice.model.OrderLineItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderLineItemMapper{
    OrderLineItem toOrderLineItem(OrderLineItemDto dto);
}
