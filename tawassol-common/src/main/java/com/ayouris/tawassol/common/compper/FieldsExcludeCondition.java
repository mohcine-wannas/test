package com.ayouris.tawassol.common.mapper;

import java.util.List;

import org.modelmapper.AbstractCondition;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.PropertyInfo;

public class FieldsExcludeCondition<S, D> extends AbstractCondition<S, D>{
    
    private List<String> properties;
    
    
    public FieldsExcludeCondition(List<String> properties){
        this.properties = properties;
    }
    
    @Override
    public boolean applies(MappingContext<S, D> context) {
        
        boolean applyMapping = true;
        
        for (PropertyInfo propertyInfo : context.getMapping().getDestinationProperties()) {
//            if("id".equalsIgnoreCase(propertyInfo.getName()))
//                    return true;
            
            if(properties.contains(propertyInfo.getName())){
                applyMapping = false;
                break;
            }else
                continue;
        }
        return applyMapping;
    }
}