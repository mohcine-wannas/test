package ma.salamgaz.tawassol.admin.model.enums;

public enum PermissionType {

    READ,
    WRITE,
    NONE;

    /**
     * Get permission type relative to the given operations number
     * 
     * @param operations operations number
     * @return Permission type
     */
    public static PermissionType calculatePermissionType(Long operations) {

        if (operations == null || operations.equals(0L)) {
            return NONE;
        } else if (operations > 1L) {
            return WRITE;
        }
        return READ;
    }
}
