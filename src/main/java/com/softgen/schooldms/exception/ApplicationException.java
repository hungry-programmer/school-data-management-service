package com.softgen.schooldms.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }

    public static class EntryNotFoundException extends ApplicationException {
        public EntryNotFoundException(String entryType, int entryId) {
            super("""
                    No %s entry found with number %d.
                    """.formatted(entryType, entryId));
        }
    }

    public static class InvalidFieldException extends ApplicationException {
        public InvalidFieldException(String fieldName) {
            super("Invalid value for field: " + fieldName);
        }
    }
}
