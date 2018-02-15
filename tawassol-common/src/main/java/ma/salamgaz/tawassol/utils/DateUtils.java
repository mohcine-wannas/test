package ma.salamgaz.tawassol.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {

    private final String FILE_BACKUP_DATE_PATTERN = "yyyyMMdd_HHmmss";

    private DateFormat fileBackupDateFormat;

    @PostConstruct
    private void init() {
        fileBackupDateFormat = new SimpleDateFormat(FILE_BACKUP_DATE_PATTERN);
    }

    /**
     * Get date formatted as string for file backup name (ext)
     * 
     * @param date
     *            date
     * @return date formatted matching file backup pattern
     */
    public String formatDateForFileBackup(Date date) {
        return fileBackupDateFormat.format(date);
    }

}
