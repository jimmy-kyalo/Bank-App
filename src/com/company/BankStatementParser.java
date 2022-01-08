package com.company;

import java.util.List;

public interface BankStatementParser {
    BankTransaction parseFromCSV(String line);
    List<BankTransaction> parseLinesFromCSV(List<String> lines);
}
