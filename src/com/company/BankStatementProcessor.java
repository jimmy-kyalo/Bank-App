package com.company;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0;
        for (final BankTransaction bankTransaction :
                bankTransactions) {
            bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public double calculateTotalAmount() {
        double total = 0;
        for (final BankTransaction bankTransaction :
                bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month) {
        return summarizeTransactions((acc, bankTransaction) -> bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDescription().equals(category)) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction :
                bankTransactions) {
            if (bankTransaction.getAmount() >= amount) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactionsInMonth(final Month month) {
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction :
                bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactionsInMonthAndGreater(final Month month, final int amount) {
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction :
                bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() >= amount) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction :
                bankTransactions) {
            if (bankTransactionFilter.test(bankTransaction)) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> FindTransactionsGreaterThanEqual(final int amount) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }
}


//class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter{
//    @Override
//    public boolean test(final BankTransaction bankTransaction){
//        return bankTransaction.getDate().getMonth() == Month.FEBRUARY && bankTransaction.getAmount() >= 1_000;
//    }
//}

