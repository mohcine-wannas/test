package ma.salamgaz.tawassol.common.util.csv;

public enum CsvContentKey {

    STATUS_ENABLED(
            "csv.list.status.enabled"),
    STATUS_DISABLED(
            "csv.list.status.disabled"),

    ORGANIZATION_HEADER(
            "csv.list.organization.header"),

    USER_HEADER(
            "csv.list.user.header"),

    ROLE_HEADER(
            "csv.list.role.header");

    private String key;

    private CsvContentKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
