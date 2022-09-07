package org.fasttrackit.BudgetApp.service.transaction;

import org.fasttrackit.BudgetApp.model.transaction.Transaction;

import java.util.List;

public interface TransactionProvider {
    List<Transaction> getTransactions();
}
