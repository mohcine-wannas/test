package com.ayouris.tawassol.common.model.helper.cfg;

import com.github.fluent.hibernate.annotations.FluentName;
import com.github.fluent.hibernate.cfg.strategy.JoinTableNames;
import com.github.fluent.hibernate.internal.util.InternalUtils;
import com.github.fluent.hibernate.internal.util.reflection.ReflectionUtils;
import org.hibernate.boot.model.naming.*;
import org.hibernate.boot.model.source.spi.AttributePath;
import org.hibernate.cfg.Ejb3Column;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by chamakh on 21/10/2016.
 */
public class CustomNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

    private static final long serialVersionUID = 3482010804082494311L;

    private final HibernateNamingStrategy strategy;

    private final JoinTableNames joinTableNames = new JoinTableNames();

    public CustomNamingStrategy() {
        this(new StrategyOptions());
    }

    public CustomNamingStrategy(StrategyOptions options) {
        strategy = new HibernateNamingStrategy(options);
    }

    private static String getFluentNamePrefix(Ejb3Column column, String propertyName) {
        Class<?> mappedClass = column.getPropertyHolder().getPersistentClass().getMappedClass();
        FluentName fluentName = ReflectionUtils.getAnnotation(mappedClass, propertyName,
                FluentName.class);
        return fluentName == null ? null : fluentName.prefix();
    }

    private static boolean isEmbedded(Ejb3Column column) {
        return column.getPropertyHolder().isComponent();
    }

    private static Ejb3Column getEjb3Column(ImplicitBasicColumnNameSource source) {
        try {
            Field ejb3ColumnField = source.getClass().getDeclaredField("this$0");
            ReflectionUtils.makePublic(ejb3ColumnField);
            return (Ejb3Column) ejb3ColumnField.get(source);
        } catch (Exception ex) {
            throw InternalUtils.toRuntimeException(ex);
        }
    }

    private static String getPropertyName(AttributePath attributePath) {
        return attributePath == null ? null : attributePath.getProperty();
    }

    private static Identifier toIdentifier(String stringForm, ImplicitNameSource source) {
        return source.getBuildingContext().getMetadataCollector().getDatabase().getJdbcEnvironment()
                .getIdentifierHelper().toIdentifier(stringForm);
    }

    public void setOptions(StrategyOptions options) {
        strategy.setOptions(options);
    }

    public void setTablePrefix(String tablePrefix) {
        strategy.getOptions().setTablePrefix(tablePrefix);
    }

    public void setMaxLength(int maxLength) {
        strategy.getOptions().setMaxLength(maxLength);
    }

    /**
     * Generates a name for a dataabse table.
     */
    @Override
    public String transformEntityName(EntityNaming entityNaming) {
        return strategy.classToTableName(entityNaming.getEntityName());
    }

    public String transformEntityName(String entityName) {
        return strategy.classToTableName(entityName);
    }

    /**
     * Generates a name for a table column.
     */
    @Override
    public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source) {
        AttributePath attributePath = source.getAttributePath();
        String propertyName = getPropertyName(attributePath);
        String parentPropertyName = getPropertyName(attributePath.getParent());

        Ejb3Column column = getEjb3Column(source);

        // generate a name for an embedded column
        if (isEmbedded(column)) {
            String fluentNamePrefix = getFluentNamePrefix(column, parentPropertyName);

            boolean hasEmbeddedPrefix = !InternalUtils.StringUtils.isEmpty(fluentNamePrefix);
            String prefix = hasEmbeddedPrefix ? fluentNamePrefix : parentPropertyName;

            boolean dontTouchPrefix = hasEmbeddedPrefix;
            return toIdentifier(
                    strategy.embeddedPropertyToColumnName(prefix, propertyName, dontTouchPrefix),
                    source);
        }

        // It is a strange behaviour for collection associations.
        // For an example for a "roles" association Hibernate uses "roles.element".
        if (!InternalUtils.StringUtils.isEmpty(parentPropertyName)
                && propertyName.equals("element")) {
            return toIdentifier(propertyName, source);
        }

        // Hibernate calls this method the first time for @Embedded column, but doesn't use a result
        return toIdentifier(strategy.propertyToColumnName(propertyName), source);
    }

    @Override
    public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source) {
        String propertyTableName = InternalUtils.ClassUtils
                .getShortName(source.getEntityNaming().getEntityName());
        // a property name is null for join tables for an owner table foreign key
        String propertyName = getPropertyName(source.getAttributePath());
        String result = strategy.foreignKeyColumnName(propertyName, propertyTableName);
        return toIdentifier(result, source);
    }

    /**
     * Generates a name for a join table.
     */
    @Override
    public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
        // TODO check String ownerEntityTable = source.getOwningPhysicalTableName();

        String ownerEntityTable = source.getOwningEntityNaming().getEntityName();
        String associatedEntityTable = source.getNonOwningEntityNaming().getEntityName();

        String propertyName = getPropertyName(source.getAssociationOwningAttributePath());

        String tableName = strategy.joinTableName(ownerEntityTable, associatedEntityTable);

        JoinTableNames.TableDescription description = new JoinTableNames.TableDescription(ownerEntityTable, associatedEntityTable,
                propertyName);

        String result = joinTableNames.hasSameNameForOtherProperty(tableName, description)
                ? strategy.joinTableName(ownerEntityTable, associatedEntityTable, propertyName)
                : tableName;

        joinTableNames.put(result, description);

        return toIdentifier(result, source);
    }

    /**
     * Generates a name for a foreign key constraint.
     */
    @Override
    public Identifier determineForeignKeyName(ImplicitForeignKeyNameSource source) {
        List<Identifier> columnNames = source.getColumnNames();

        // constraints are supported for one column only
        if (InternalUtils.CollectionUtils.size(columnNames) != 1) {
            return super.determineForeignKeyName(source);
        }

        String result = strategy.foreignKeyConstraintName(source.getTableName().getText(),
                columnNames.get(0).getText());

        return toIdentifier(result, source);
    }

    /**
     * Generates a name for a unique constraint.
     */
    @Override
    public Identifier determineUniqueKeyName(ImplicitUniqueKeyNameSource source) {
        List<Identifier> columnNames = source.getColumnNames();

        // constraints are supported for one column only
        if (InternalUtils.CollectionUtils.size(columnNames) != 1) {
            return super.determineUniqueKeyName(source);
        }

        String result = strategy.uniqueKeyConstraintName(source.getTableName().getText(),
                columnNames.get(0).getText());

        return toIdentifier(result, source);
    }
}
