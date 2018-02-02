package ma.salamgaz.tawassol.common.mapper;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class CustomModelMapper extends ModelMapper {

    public <S, D> void resetCondition() {

        this.getConfiguration().setPropertyCondition(new Condition<S, D>() {

            @Override
            public boolean applies(MappingContext<S, D> context) {
                // TODO Auto-generated method stub
                return true;
            }
        });
    }

    /**
     * Map by providing custom condition
     * 
     * @param condition
     * @param source
     * @param destination
     * @return the mapped destination
     */
    public synchronized <S, D> D mapWithCondition(Condition<S, D> condition, Object source, Class<D> destination) {

        D result;
        this.getConfiguration().setPropertyCondition(condition);
        result = super.map(source, destination);
        resetCondition();
        return result;
    }

    /**
     * Map and dot not erase destination properties with source null properties
     * 
     * @param source
     * @param destination
     * @return the mapped destination
     */
    public synchronized <S, D> D mapAndSkipNull(Object source, Class<D> destination) {

        D result;
        this.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        result = super.map(source, destination);
        resetCondition();
        return result;
    }

    /**
     * Map and skip provided fields.
     * 
     * @param source
     * @param destination
     * @param fieldsToInclude
     * @return
     */
    public synchronized <S, D> D mapOnlyIncludeFields(Object source, Class<D> destination,
            List<String> fieldsToInclude) {

        D result = null;
        Condition<S, D> condition = new FieldsIncludeCondition<>(fieldsToInclude);
        this.getConfiguration().setPropertyCondition(condition);
        result = super.map(source, destination);
        resetCondition();
        return result;
    }

    public synchronized <S, D> Object mapOnlyIncludeFields(Object source, Object destination,
            List<String> fieldsToInclude) {

        super.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Condition<S, D> condition = new FieldsIncludeCondition<>(fieldsToInclude);
        this.getConfiguration().setPropertyCondition(condition);
        super.map(source, destination);
        resetCondition();
        super.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        return destination;
    }

    public synchronized <S, D> Object mapAndExcludeFields(Object source, Object destination, String[] fieldsToSkip) {

        Condition<S, D> condition = new FieldsExcludeCondition<>(Arrays.asList(fieldsToSkip));
        this.getConfiguration().setPropertyCondition(condition);
        super.map(source, destination);
        resetCondition();
        return destination;
    }

    public synchronized <S, D> Object mapAndExcludeFields(Object source, Class<D> destination, String[] fieldsToSkip) {

        D result = null;
        Condition<S, D> condition = new FieldsExcludeCondition<>(Arrays.asList(fieldsToSkip));
        this.getConfiguration().setPropertyCondition(condition);
        result = super.map(source, destination);
        resetCondition();
        return result;
    }

    /**
     * 
     */
    @Override
    public synchronized <D> D map(Object source, Class<D> destinationType) {

        resetCondition();
        return super.map(source, destinationType);
    }

    /**
     * 
     */
    @Override
    public synchronized <D> D map(Object source, Type destinationType) {
        resetCondition();
        return super.map(source, destinationType);

    }

    /**
     * Map using STRICT mode
     * 
     * @param source
     *            source
     * @param destinationType
     *            destination
     * @return mapped new object
     */
    public synchronized <D> D mapStrict(Object source, Type destinationType) {
        super.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        resetCondition();
        D result = super.map(source, destinationType);

        super.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        return result;
    }

    @Override
    public synchronized <S, D> TypeMap<S, D> createTypeMap(Class<S> sourceType, Class<D> destinationType) {
        TypeMap<S, D> typeMap = super.getTypeMap(sourceType, destinationType);
        if (typeMap != null) {
            return typeMap;
        }
        return super.<S, D> createTypeMap(sourceType, destinationType);
    }

}
