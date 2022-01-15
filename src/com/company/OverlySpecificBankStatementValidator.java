package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class OverlySpecificBankStatementValidator {
    private String date;
    private String amount;
    private String description;

    public OverlySpecificBankStatementValidator(final String date, final String amount, final String description) {
        this.date = Objects.requireNonNull(description);
        this.amount = Objects.requireNonNull(description);
        this.description = Objects.requireNonNull(description);
    }

    public boolean validate() throws IllegalArgumentException {
        if (this.description.length() > 100) {
            throw new IllegalArgumentException("The description is too long.");
        }
        final LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(this.date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format", e);
        }
        if (parsedDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the future");
        }
        try {
            Double.parseDouble(this.amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid format for amount", e);
        }
        return true;
    }
}
