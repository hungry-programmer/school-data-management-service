package com.softgen.schooldms.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }

    public static class EntryNotFoundException extends ApplicationException {
        public EntryNotFoundException(String entryType) {
            super("""
                    No %s entry found with provided argument.
                    """.formatted(entryType));
        }
    }

    public static class InvalidFieldException extends ApplicationException {
        public InvalidFieldException(String fieldName) {
            super("Invalid value for field: " + fieldName);
        }
    }
}
