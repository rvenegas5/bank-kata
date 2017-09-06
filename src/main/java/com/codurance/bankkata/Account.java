package com.codurance.bankkata;

import java.util.List;

public class Account {

    private TransactionRepository transactionRepository;
    private Clock clock;
    private StatementPrinter statementPrinter;

    public Account(TransactionRepository transactionRepository, Clock clock, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount) {
        validateAmount(amount);

        transactionRepository.add(new Transaction(clock.today(), amount));
    }


    public void withdraw(int amount) {
        validateAmount(amount);

        transactionRepository.add(new Transaction(clock.today(), -amount));
    }

    public void printStatement() {
        List<Transaction> transactions = transactionRepository.all();
        statementPrinter.print(transactions);
    }

    private void validateAmount(int amount) {
        if(amount<=0) {
            throw new IllegalArgumentException("The amount must not be greater than 0");
        }
    }
}
