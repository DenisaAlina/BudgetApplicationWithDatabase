package org.fasttrackit.BudgetApp.controller.transaction;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.fasttrackit.BudgetApp.model.transaction.Transaction;
import org.fasttrackit.BudgetApp.model.transaction.Type;
import org.fasttrackit.BudgetApp.service.transaction.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    List<Transaction> getTransactions(@RequestParam(required = false) String type,
                                      @RequestParam(required = false) Double minAmount,
                                      @RequestParam(required = false) Double maxAmount) {
        return transactionService.getTransactions(type, minAmount, maxAmount);
    }

    @GetMapping("{id}")
    Transaction getTransactionById(@PathVariable Integer id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    void addTransaction(@RequestBody Transaction transaction) {
        transactionService.saveTransaction(transaction);
    }

    @PutMapping("{id}")
    void replaceTransaction(@PathVariable Integer id,
                            @RequestBody Transaction transaction) {
        transactionService.replaceTransaction(id, transaction);
    }

    @PatchMapping("{id}")
    void changingProductAndAmount(@PathVariable Integer id,
                                  @RequestParam String type,
                                  @RequestParam Double amount) {
        transactionService.changeTransaction(id, type, amount);
    }

    @DeleteMapping("{id}")
    void deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("reports/type")
    Map<Type, Double> mapFromTypeToList() {
        return transactionService.mapFromTypeToSum();
    }


    @GetMapping("reports/product")
    Map<String, Double> mapFromProductNameToSum() {
        return transactionService.mapFromProductNameToSum();
    }

}