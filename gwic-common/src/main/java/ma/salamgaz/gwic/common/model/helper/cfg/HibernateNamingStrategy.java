package ma.salamgaz.gwic.common.model.helper.cfg;

import com.github.fluent.hibernate.internal.util.InternalUtils;

import static com.github.fluent.hibernate.internal.util.InternalUtils.StringUtils.joinWithSpace;

/**
 * Created by chamakh on 21/10/2016.
 */
public class HibernateNamingStrategy {
    private StrategyOptions options;

    public HibernateNamingStrategy(StrategyOptions options) {
        this.options = options;
    }

    private static String embeddedColumnName(String prefix, String property, int maxLength) {
        final boolean dontTouchFirst = false;

        maxLength--;// concat

        int maxPrefixlength = maxLength - property.length();
        String prefixShorted = new NameShorter(maxPrefixlength, dontTouchFirst)
                .embeddedColumnName(prefix);

        int maxPropertyLength = maxLength - prefixShorted.length();
        String propertyShorted = new NameShorter(maxPropertyLength, dontTouchFirst)
                .embeddedColumnName(property);

        return NamingStrategyUtils.concat(prefixShorted, propertyShorted).toUpperCase();
    }

    public StrategyOptions getOptions() {
        return options;
    }

    public void setOptions(StrategyOptions options) {
        this.options = options;
    }

    public String classToTableName(String className) {
        String result = InternalUtils.StringUtils.join(options.getTablePrefix(), NamingStrategyUtils.classToPluralizedName(className));

        if (needRestrict(options.isRestrictTableNames())) {
            return assertName(new NameShorter(options.getMaxLength(), options.hasTablePrefix())
                    .tableName(result), className, "@Table(name=\"prefix_table_name\")");
        }

        return result.toUpperCase();
    }

    public String propertyToColumnName(String propertyName) {
        String result = InternalUtils.StringUtils.join(options.getColumnPrefix(), NamingStrategyUtils.propertyToName(propertyName));

        if (needRestrict(options.isRestrictColumnNames())) {
            return assertName(new NameShorter(options.getMaxLength(), options.hasColumnPrefix())
                    .columnName(result), propertyName, "@Column(name=\"f_column_name\")");
        }

        return result.toUpperCase();
    }

    public String embeddedPropertyToColumnName(String prefix, String embeddedPropertyName,
                                               boolean dontTouchPrefix) {
        String columnPrefix = dontTouchPrefix ? prefix : NamingStrategyUtils.propertyToName(prefix);
        String columnPostfix = NamingStrategyUtils.propertyToName(embeddedPropertyName);
        String fullColumnPrefix = InternalUtils.StringUtils.join(options.getColumnPrefix(), columnPrefix);

        if (!needRestrict(options.isRestrictEmbeddedColumnNames())) {
            return NamingStrategyUtils.concat(fullColumnPrefix, columnPostfix);
        }

        String result = null;
        if (dontTouchPrefix) {
            final boolean dontTouchFirst = false;
            int maxPostfixLength = options.getMaxLength() - (fullColumnPrefix.length() + 1);
            result = NamingStrategyUtils.concat(fullColumnPrefix, new NameShorter(maxPostfixLength, dontTouchFirst)
                    .embeddedColumnName(columnPostfix));
        } else {
            result = InternalUtils.StringUtils.join(options.getColumnPrefix(), embeddedColumnName(columnPrefix, columnPostfix,
                    options.getMaxLength() - options.getColumnPrefixLength()));
        }

        return assertName(result.toUpperCase(), joinWithSpace(prefix, embeddedPropertyName),
                "@AttributeOverrides({@AttributeOverride(name=\"propertyName\", "
                        + "column=@Column(\"f_column_name\"))");
    }

    public String joinTableName(String ownerEntityTable, String associatedEntityTable) {
        return joinTableName(ownerEntityTable, associatedEntityTable, null).toUpperCase();
    }

    public String joinTableName(String ownerEntityTable, String associatedEntityTable,
                                String ownerProperty) {
        String ownerTable = NamingStrategyUtils.classToPluralizedName(ownerEntityTable);
        String associatedTable = NamingStrategyUtils.classToPluralizedName(associatedEntityTable);

        String result = ownerProperty == null ? NamingStrategyUtils.concat(ownerTable, associatedTable)
                : NamingStrategyUtils.concat(NamingStrategyUtils.concat(ownerTable, associatedTable), NamingStrategyUtils.propertyToName(ownerProperty));

        result = InternalUtils.StringUtils.join(options.getTablePrefix(), result);

        if (needRestrict(options.isRestrictJoinTableNames())) {
            return assertName(
                    new NameShorter(options.getMaxLength(), options.hasTablePrefix())
                            .joinTableName(result),
                    joinWithSpace(ownerEntityTable, associatedEntityTable, ownerProperty),
                    "@JoinTable(name=\"prefix_join_table_name\")");
        }

        return result.toUpperCase();
    }

    /**
     * For Hibernate 4 only.
     */
    public String joinKeyColumnName(String joinedColumn, String joinedTable) {
        // TODO check
        // return propertyToColumnName(joinedColumn) + "_id";
        return NamingStrategyUtils.addUnderscores(joinedColumn) + "_id";
    }

    public String foreignKeyColumnName(String propertyName, String propertyTableName) {
        // a property name is null for join tables for an owner table foreign key
        String header = propertyName != null ? NamingStrategyUtils.unqualify(propertyName) : propertyTableName;
        String result = InternalUtils.StringUtils.join(header, NamingStrategyUtils.addUnderscores("_id"));

        if (needRestrict(options.isRestrictColumnNames())) {
            return assertName(
                    new NameShorter(options.getMaxLength(), options.hasForeignKeyColumnPrefix())
                            .columnName(result),
                    joinWithSpace(propertyTableName, propertyName),
                    "@JoinColumn(name=\"fk_name\")");
        }

        return result.toUpperCase();
    }

    /**
     * Generates a name for a foreign key constraint.
     */
    public String foreignKeyConstraintName(String tableName, String columnName) {
        String result = InternalUtils.StringUtils.join(options.getForeignKeyConstraintPrefix(),
                NamingStrategyUtils.concat(tableName, columnName));

        if (needRestrict(options.isRestrictConstraintNames())) {
            return assertName(
                    new NameShorter(options.getMaxLength(), options.hasForeignKeyConstraintPrefix())
                            .constraintName(result),
                    "a foreign key constraint for " + joinWithSpace(tableName, columnName),
                    "@ForeignKey(name=\"fk_name\")");
        }

        return result.toUpperCase();

    }

    public String uniqueKeyConstraintName(String tableName, String columnName) {
        String result = InternalUtils.StringUtils.join(options.getUniqueKeyConstraintPrefix(), NamingStrategyUtils.concat(tableName, columnName));

        if (needRestrict(options.isRestrictConstraintNames())) {
            return assertName(
                    new NameShorter(options.getMaxLength(), options.hasUniqueKeyConstraintPrefix())
                            .constraintName(result),
                    joinWithSpace(tableName, columnName),
                    "@UniqueConstraint (if it is appropriate)");
        }

        return result.toUpperCase();
    }

    private String assertName(String name, String object, String annotation) {
        int currentLength = name.length();
        int maxLength = options.getMaxLength();
        if (currentLength > maxLength) {
            InternalUtils.Asserts.fail(String.format(
                    "Can't restrict name of '%s'. Result '%s' has the length %d, max length is %d. "
                            + "Use '%s' to hardcode the name",
                    object, name, currentLength, maxLength, annotation));
        }

        return name;
    }

    private boolean needRestrict(boolean toCheck) {
        return options.getMaxLength() > 0 && toCheck;
    }

}
