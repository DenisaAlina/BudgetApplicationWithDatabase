package org.fasttrackit.BudgetApp.service.transaction;

import org.fasttrackit.BudgetApp.model.transaction.Transaction;
import org.fasttrackit.BudgetApp.model.transaction.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<List<Transaction>> findByType (Type type);
    List<Transaction>findByAmountGreaterThan (Double minAmount);
    List<Transaction>findByAmountLessThan (Double maxAmount);

    List<Transaction>findByAmountBetween (Double min, Double max);

    Optional<List<Transaction>>findByTypeAndAmountGreaterThan (Type type, Double min);

    Optional<List<Transaction>>findByTypeAndAmountLessThan (Type type, Double max);

    Optional<List<Transaction>>findByTypeAndAmountBetween (Type type, Double min, Double max);

}
