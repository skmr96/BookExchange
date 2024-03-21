package com.bookexchange.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ExistingStatusConverter implements AttributeConverter<ExistingStatus,Integer>{

    @Override
    public Integer convertToDatabaseColumn(ExistingStatus attribute) {
        return attribute.getStatus();
    }

    @Override
    public ExistingStatus convertToEntityAttribute(Integer dbData) {
        for(ExistingStatus es : ExistingStatus.values())
            if(es.getStatus() == dbData)
                return es;
        return null;
    }
}
