package com.bookexchange.enums;

import com.bookexchange.domain.Item;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ItemTypeConverter implements AttributeConverter<ItemType,Integer>{

    @Override
    public Integer convertToDatabaseColumn(ItemType attribute) {
        return attribute.getType();
    }

    @Override
    public ItemType convertToEntityAttribute(Integer dbData) {
        for(ItemType it : ItemType.values())
            if(it.getType() == dbData)
                return it;
        return null;
    }
}
