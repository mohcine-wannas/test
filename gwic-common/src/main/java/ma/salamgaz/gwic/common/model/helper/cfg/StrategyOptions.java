package ma.salamgaz.gwic.common.model.helper.cfg;

import static com.github.fluent.hibernate.internal.util.InternalUtils.StringUtils.*;

/**
 * Created by chamakh on 21/10/2016.
 */
public class StrategyOptions {
    private static final String TABLE_PREFIX = "tbl_";

    private static final String COLUMN_PREFIX = "";

    private static final String FOREIGN_KEY_COLUMN_PREFIX = "";

    private static final String FOREIGN_KEY_CONSTRAINT_PREFIX = "FK_";

    private static final String UNIQUE_KEY_CONSTRAINT_PREFIX = "UQ_";
    public boolean autodetectMaxLength = true;
    private String tablePrefix = TABLE_PREFIX;
    private String columnPrefix = COLUMN_PREFIX;
    private String foreignKeyColumnPrefix = FOREIGN_KEY_COLUMN_PREFIX;
    private String foreignKeyConstraintPrefix = FOREIGN_KEY_CONSTRAINT_PREFIX;
    private String uniqueKeyConstraintPrefix = UNIQUE_KEY_CONSTRAINT_PREFIX;
    private int maxLength;
    private boolean restrictTableNames = true;
    private boolean restrictColumnNames = true;
    private boolean restrictEmbeddedColumnNames = true;
    private boolean restrictJoinTableNames = true;
    private boolean restrictConstraintNames = true;

    public static StrategyOptions.Builder builder() {
        return new StrategyOptions.Builder();
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = addSuffixIfNot(tablePrefix, NamingStrategyUtils.NAME_PARTS_SEPARATOR);
    }

    boolean hasTablePrefix() {
        return !isEmpty(tablePrefix);
    }

    public String getColumnPrefix() {
        return columnPrefix;
    }

    public void setColumnPrefix(String columnPrefix) {
        this.columnPrefix = columnPrefix;
    }

    int getColumnPrefixLength() {
        return length(columnPrefix);
    }

    boolean hasColumnPrefix() {
        return !isEmpty(columnPrefix);
    }

    public String getForeignKeyColumnPrefix() {
        return foreignKeyColumnPrefix;
    }

    public void setForeignKeyColumnPrefix(String foreignKeyColumnPrefix) {
        this.foreignKeyColumnPrefix = foreignKeyColumnPrefix;
    }

    boolean hasForeignKeyColumnPrefix() {
        return !isEmpty(foreignKeyColumnPrefix);
    }

    public String getForeignKeyConstraintPrefix() {
        return foreignKeyConstraintPrefix;
    }

    public void setForeignKeyConstraintPrefix(String foreignKeyConstraintPrefix) {
        this.foreignKeyConstraintPrefix = foreignKeyConstraintPrefix;
    }

    boolean hasForeignKeyConstraintPrefix() {
        return !isEmpty(foreignKeyConstraintPrefix);
    }

    public String getUniqueKeyConstraintPrefix() {
        return uniqueKeyConstraintPrefix;
    }

    public void setUniqueKeyConstraintPrefix(String uniqueKeyConstraintPrefix) {
        this.uniqueKeyConstraintPrefix = uniqueKeyConstraintPrefix;
    }

    boolean hasUniqueKeyConstraintPrefix() {
        return !isEmpty(uniqueKeyConstraintPrefix);
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isRestrictTableNames() {
        return restrictTableNames;
    }

    public void setRestrictTableNames(boolean restrictTableNames) {
        this.restrictTableNames = restrictTableNames;
    }

    public boolean isRestrictColumnNames() {
        return restrictColumnNames;
    }

    public void setRestrictColumnNames(boolean restrictColumnNames) {
        this.restrictColumnNames = restrictColumnNames;
    }

    public boolean isRestrictEmbeddedColumnNames() {
        return restrictEmbeddedColumnNames;
    }

    public void setRestrictEmbeddedColumnNames(boolean restrictEmbeddedColumnNames) {
        this.restrictEmbeddedColumnNames = restrictEmbeddedColumnNames;
    }

    public boolean isRestrictJoinTableNames() {
        return restrictJoinTableNames;
    }

    public void setRestrictJoinTableNames(boolean restrictJoinTableNames) {
        this.restrictJoinTableNames = restrictJoinTableNames;
    }

    public boolean isRestrictConstraintNames() {
        return restrictConstraintNames;
    }

    public void setRestrictConstraintNames(boolean restrictConstraintNames) {
        this.restrictConstraintNames = restrictConstraintNames;
    }

    public boolean isAutodetectMaxLength() {
        return autodetectMaxLength;
    }

    public void setAutodetectMaxLength(boolean autodetectMaxLength) {
        this.autodetectMaxLength = autodetectMaxLength;
    }

    public static class Builder {

        private final StrategyOptions result = new StrategyOptions();

        public StrategyOptions.Builder tablePrefix(String tablePrefix) {
            result.setTablePrefix(tablePrefix);
            return this;
        }

        public StrategyOptions.Builder dontRestrictLength() {
            return restrictLength(0);
        }

        public StrategyOptions.Builder restrictLength(int maxLength) {
            result.maxLength = maxLength;
            return this;
        }

        public StrategyOptions.Builder columnPrefix(String columnPrefix) {
            result.columnPrefix = columnPrefix;
            return this;
        }

        /**
         * Sets a foreign key column prefix.
         */
        public StrategyOptions.Builder foreignKeyColumnPrefix(String foreignKeyColumnPrefix) {
            result.foreignKeyColumnPrefix = foreignKeyColumnPrefix;
            return this;
        }

        /**
         * Sets a foreign key constraint prefix.
         */
        public StrategyOptions.Builder foreignKeyConstraintPrefix(String foreignKeyConstraintPrefix) {
            result.foreignKeyConstraintPrefix = foreignKeyConstraintPrefix;
            return this;
        }

        public StrategyOptions.Builder uniqueKeyConstraintPrefix(String uniqueKeyConstraintPrefix) {
            result.uniqueKeyConstraintPrefix = uniqueKeyConstraintPrefix;
            return this;
        }

        public StrategyOptions.Builder restrictConstraintNames(boolean restrictConstraintNames) {
            result.setRestrictConstraintNames(restrictConstraintNames);
            return this;
        }

        public StrategyOptions.Builder restrictTableNames(boolean restrictTableNames) {
            result.restrictTableNames = restrictTableNames;
            return this;
        }

        public StrategyOptions.Builder restrictColumnNames(boolean restrictColumnNames) {
            result.restrictColumnNames = restrictColumnNames;
            return this;
        }

        public StrategyOptions.Builder restrictEmbeddedColumnNames(boolean restrictEmbeddedColumnNames) {
            result.restrictEmbeddedColumnNames = restrictEmbeddedColumnNames;
            return this;
        }

        public StrategyOptions.Builder restrictJoinTableNames(boolean restrictJoinTableNames) {
            result.restrictJoinTableNames = restrictJoinTableNames;
            return this;
        }

        /**
         * Don't use any prefixes like a table name prefix.
         */
        public StrategyOptions.Builder withoutPrefixes() {
            result.tablePrefix = null;
            result.columnPrefix = null;
            result.foreignKeyColumnPrefix = null;
            result.foreignKeyConstraintPrefix = null;
            result.uniqueKeyConstraintPrefix = null;
            return this;
        }

        /**
         * Autodetect a maximum name length for a database. Can be used only with a dialect in the
         * hibernate.properties.
         * <p>
         * TODO It shoul work with a dialect in the hibernate.cfg.xml.
         */
        public StrategyOptions.Builder autodetectMaxLength() {
            result.autodetectMaxLength = true;
            return this;
        }

        public StrategyOptions build() {
            return result;
        }

    }

}
