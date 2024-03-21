package com.bookexchange.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ExchangeTypeConverter implements AttributeConverter<ExchangeType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ExchangeType attribute) {
        return attribute.getType();
    }

    @Override
    public ExchangeType convertToEntityAttribute(Integer dbData) {
        for(ExchangeType et : ExchangeType.values())
            if(et.getType() == dbData)
                return et;
        return null;
    }
}
