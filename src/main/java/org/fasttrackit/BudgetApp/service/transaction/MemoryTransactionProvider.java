package org.fasttrackit.BudgetApp.service.transaction;

import org.fasttrackit.BudgetApp.model.transaction.Transaction;
import org.fasttrackit.BudgetApp.model.transaction.Type;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MemoryTransactionProvider implements TransactionProvider {
    public List<Transaction> getTransactions() {
        List<Transaction> list = new LinkedList<>();
        list.add(Transaction.builder().product("Milk").type(Type.SELL).amount(6.0).build());
        list.add(Transaction.builder().product("Flour").type(Type.SELL).amount(10.0).build());
        list.add(Transaction.builder().product("Tagliatelle").type(Type.SELL).amount(8.0).build());
        list.add(Transaction.builder().product("Orange").type(Type.SELL).amount(6.0).build());
        list.add(Transaction.builder().product("Chicken Ham").type(Type.SELL).amount(12.0).build());
        list.add(Transaction.builder().product("Wine").type(Type.SELL).amount(6.0).build());
        list.add(Transaction.builder().product("Water").type(Type.SELL).amount(4.0).build());
        list.add(Transaction.builder().product("Wine").type(Type.BUY).amount(6.0).build());
        list.add(Transaction.builder().product("Rice").type(Type.BUY).amount(6.0).build());
        list.add(Transaction.builder().product("Milk").type(Type.BUY).amount(12.0).build());
        return list;
    }
}
