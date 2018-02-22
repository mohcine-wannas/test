package com.ayouris.tawassol.common.mapper;

import java.util.List;

import org.modelmapper.AbstractCondition;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.PropertyInfo;

public class FieldsIncludeCondition<S, D> extends AbstractCondition<S, D>{
    
    private List<String> properties;
    
    
    public FieldsIncludeCondition(List<String> properties){
        this.properties = properties;
    }
    
    @Override
    public boolean applies(MappingContext<S, D> context) {
        
        boolean applyMapping = false;
        
        for (PropertyInfo propertyInfo : context.getMapping().getDestinationProperties()) {
//            if("id".equalsIgnoreCase(propertyInfo.getName()))
//                    return true;
            
            if(properties.contains(propertyInfo.getName())){
                applyMapping = true;
                break;
            }else
                continue;
        }
        return applyMapping;
    }
}