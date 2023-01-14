package org.mcmasterkboys.codenamehenryford.objects.exception;

public class DataFieldMismatchException extends Exception {

    public static final String FIELD_TYPE_MISMATCH = "Field type mismatch";
    public static final String FIELD_NAME_MISMATCH = "Field name mismatch";
    public static final String FIELD_COUNT_MISMATCH = "Field count mismatch";

    public DataFieldMismatchException(String type, String expected, String actual) {
        super("Data field mismatch: " + type + " expected: " + expected + " actual: " + actual);
    }

    public DataFieldMismatchException(Exception e) {
        super(e);
    }
}
