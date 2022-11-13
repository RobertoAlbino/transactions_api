package com.roberto.pismo.transactions.business.validator;

import com.roberto.pismo.transactions.business.enums.OperationTypeEnum;
import com.roberto.pismo.transactions.business.model.TransactionModel;
import com.roberto.pismo.transactions.exceptions.BusinessException;
import com.roberto.pismo.transactions.persistence.OperationTypePersistence;
import com.roberto.pismo.transactions.utils.FieldValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CreateTransactionValidator {

    public final String NOT_ALLOWED_ZERO = "O valor da transação não pode ser zero";
    public final String DEBIT_VALUE_NEED_BE_NEGATIVE = "O valor debitado precisa ser negativo para esse tipo de operação";
    public final String CREDIT_VALUE_NEED_BE_POSITIVE = "O valor creditado precisa ser positivo para esse tipo de operação";

    private final OperationTypePersistence operationTypePersistence;

    private void validateFields(TransactionModel transaction) {
        FieldValidator.validateNull(transaction.getOperationTypeID(), "operationTypeID");
        FieldValidator.validateNull(transaction.getAccountID(), "accountID");
        FieldValidator.validateNull(transaction.getAmount(), "amount");
        FieldValidator.validateNull(transaction.getEventDate(), "eventDate");
    }

    private boolean compareBigDecimalToZero(BigDecimal amount, int comparator) {
        return amount.compareTo(BigDecimal.ZERO) == comparator;
    }

    private void validateOperationType(TransactionModel transaction) {
        operationTypePersistence.exists(transaction.getOperationTypeID());
        var operation = OperationTypeEnum.find(transaction.getOperationTypeID());
        var amountIsZero = compareBigDecimalToZero(transaction.getAmount(), 0);
        if (amountIsZero) {
            throw new BusinessException(NOT_ALLOWED_ZERO);
        }
        if (OperationTypeEnum.isDebitOperation(operation)) {
            var positive = compareBigDecimalToZero(transaction.getAmount(), 1);
            if (positive) {
                throw new BusinessException(DEBIT_VALUE_NEED_BE_NEGATIVE);
            }
        }
        if (OperationTypeEnum.isCreditOperation(operation)) {
            var negative = compareBigDecimalToZero(transaction.getAmount(), -1);
            if (negative) {
                throw new BusinessException(CREDIT_VALUE_NEED_BE_POSITIVE);
            }
        }
    }

    public void validateCreate(TransactionModel transaction) {
        validateFields(transaction);
        validateOperationType(transaction);
    }
}
