package com.quangduong.orderservice.mapper;

import com.quangduong.orderservice.dto.OrderLineItemDto;
import com.quangduong.orderservice.model.OrderLineItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-29T20:43:39+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class OrderLineItemMapperImpl implements OrderLineItemMapper {

    @Override
    public OrderLineItem toOrderLineItem(OrderLineItemDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrderLineItem.OrderLineItemBuilder orderLineItem = OrderLineItem.builder();

        orderLineItem.skuCode( dto.getSkuCode() );
        orderLineItem.price( dto.getPrice() );
        orderLineItem.quantity( dto.getQuantity() );

        return orderLineItem.build();
    }
}
