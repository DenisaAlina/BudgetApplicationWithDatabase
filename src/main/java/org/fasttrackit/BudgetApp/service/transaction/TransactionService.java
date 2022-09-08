package org.fasttrackit.BudgetApp.service.transaction;

import org.fasttrackit.BudgetApp.exception.ResourceNotFoundException;
import org.fasttrackit.BudgetApp.model.transaction.Transaction;
import org.fasttrackit.BudgetApp.model.transaction.Type;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionProvider transactionProvider, TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        transactionRepository.saveAll(transactionProvider.getTransactions());
    }

    public List<Transaction> getTransactions(String type, Double minAmount, Double maxAmount) {

        if (type == null) {
            if (minAmount == null) {
                if (maxAmount == null) {
                    return getAll();
                } else {
                    return getAllByMaxAmount(maxAmount);
                }
            } else if (maxAmount == null) {
                return getAllByMinAmount(minAmount);
            } else {
                return getAllByMinMAxAmount(minAmount, maxAmount);
            }
        } else if (minAmount == null) {
            if (maxAmount == null) {
                return getAllByType(type);
            } else {
                return getAllByTypeAndMaxAmount(type, maxAmount);
            }
        } else if (maxAmount == null) {
            return getAllByTypeAndMinAmount(type, minAmount);
        } else {
            return getAllByTypeAndMinMAxAmount(type, minAmount, maxAmount);
        }

    }

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }


    public List<Transaction> getAllByType(String type) {
        return transactionRepository
                .findByType(Type.fromStringToEnum(type))
                .orElseThrow(() -> new ResourceNotFoundException("Transaction type not found"));
    }

    public List<Transaction> getAllByMinAmount(Double minAmount) {
        return transactionRepository
                .findByAmountGreaterThan(minAmount);
    }

    public List<Transaction> getAllByMaxAmount(Double maxAmount) {
        return transactionRepository
                .findByAmountLessThan(maxAmount);
    }

    public List<Transaction> getAllByMinMAxAmount(Double min, Double max) {
        return transactionRepository
                .findByAmountBetween(min, max);
    }


    public List<Transaction> getAllByTypeAndMinAmount(String type, Double min) {

        return transactionRepository
                .findByTypeAndAmountGreaterThan(Type.fromStringToEnum(type), min)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction type not found"));
    }

    public List<Transaction> getAllByTypeAndMaxAmount(String type, Double max) {

        return transactionRepository
                .findByTypeAndAmountLessThan(Type.fromStringToEnum(type), max)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction type not found"));

    }

    public List<Transaction> getAllByTypeAndMinMAxAmount(String type, Double min, Double max) {

        return transactionRepository
                .findByTypeAndAmountBetween(Type.fromStringToEnum(type), min, max)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction type not found"));

    }

    public Transaction getTransactionById(Integer id) {
        return transactionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid id!"));
    }

    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        if (transaction == null) {
            throw new ResourceNotFoundException("Invalid input!");
        }
    }

    public void replaceTransaction(Integer id, Transaction transaction) {
        transaction.setId(id);
        transactionRepository.save(transaction);
    }

    public void changeTransaction(Integer id, String product, Double amount) {
        if (id == null || product == null || amount == null) {
            throw new ResourceNotFoundException("Invalid input!");
        }
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid id!"));
        transaction.setProduct(product);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);

    }

    public Map<Type, Double> mapFromTypeToSum() {
        return transactionRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Transaction::getType, Collectors.summingDouble(Transaction::getAmount)));
    }

    public Map<String, Double> mapFromProductNameToSum() {
        return transactionRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Transaction::getProduct, Collectors.summingDouble(Transaction::getAmount)));
    }

}
