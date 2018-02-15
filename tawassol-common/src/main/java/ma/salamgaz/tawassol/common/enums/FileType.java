package ma.salamgaz.tawassol.common.enums;

public enum FileType {

    PDF("pdf"),
    DOC("doc"),
    XHTML("html");

    private final String extension;

    private FileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

}
