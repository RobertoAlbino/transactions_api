package com.roberto.pismo.transactions.business.validator;

import com.roberto.pismo.transactions.business.enums.OperationTypeEnum;
import com.roberto.pismo.transactions.business.model.TransactionModel;
import com.roberto.pismo.transactions.exceptions.BusinessException;
import com.roberto.pismo.transactions.persistence.OperationTypePersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class CreateTransactionValidatorTest {

    @InjectMocks
    private CreateTransactionValidator createTransactionValidator;

    @Mock
    private OperationTypePersistence operationTypePersistence;

    TransactionModel transactionModel(long accountID,
                                      int operationTypeID,
                                      BigDecimal amount,
                                      LocalDateTime eventDate) {
        return TransactionModel
                .builder()
                .accountID(accountID)
                .operationTypeID(operationTypeID)
                .amount(amount)
                .eventDate(eventDate)
                .build();
    }

    TransactionModel transactionModelNullable(boolean setToNullAccountID,
                                              boolean setToNullOperationTypeID,
                                              boolean setToNullAmount,
                                              boolean setToNullEventDate) {
        return TransactionModel
                .builder()
                    .accountID(setToNullAccountID ? null : 1L)
                    .operationTypeID(setToNullOperationTypeID ? null : OperationTypeEnum.COMPRA_A_VISTA.operationTypeID)
                    .amount(setToNullAmount ? null : BigDecimal.ONE)
                    .eventDate(setToNullEventDate ? null : LocalDateTime.now())
                .build();
    }

    private String buildNullFieldErrorMessage(String fieldName) {
        return String.format("O campo %s não pode ser nulo", fieldName);
    }

    @Test
    void assertValidateCreateThrowsWhenHasNullField() {
        Assertions.assertThrows(BusinessException.class,
                () -> createTransactionValidator.validateCreate(transactionModelNullable(true, false, false, false)),
                buildNullFieldErrorMessage("accountID"));
        Assertions.assertThrows(BusinessException.class,
                () -> createTransactionValidator.validateCreate(transactionModelNullable(false, true, false, false)),
                buildNullFieldErrorMessage("operationTypeID"));
        Assertions.assertThrows(BusinessException.class,
                () -> createTransactionValidator.validateCreate(transactionModelNullable(false, false, true, false)),
                buildNullFieldErrorMessage("amount"));
        Assertions.assertThrows(BusinessException.class,
                () -> createTransactionValidator.validateCreate(transactionModelNullable(false, false, false, true)),
                buildNullFieldErrorMessage("eventDate"));
    }

    @Test
    void testOperationTypeNotExists() {
        var exceptionMessage = "Tipo de operação não encontrada";
        var transaction = transactionModel(1L, OperationTypeEnum.COMPRA_A_VISTA.operationTypeID, new BigDecimal(-1), LocalDateTime.now());
        doThrow(new BusinessException(exceptionMessage)).when(operationTypePersistence).exists(any());
        Assertions.assertThrows(BusinessException.class,
                () -> createTransactionValidator.validateCreate(transaction),
                exceptionMessage);
    }

    @Test
    void testAmountZeroThrows() {
        var transaction = transactionModel(1L, OperationTypeEnum.COMPRA_A_VISTA.operationTypeID, BigDecimal.ZERO, LocalDateTime.now());
        Assertions.assertThrows(BusinessException.class,
                () -> createTransactionValidator.validateCreate(transaction),
                createTransactionValidator.NOT_ALLOWED_ZERO);
    }

    @Test
    void assertDebitOperationAmountNeedToBeNegative() {
        var firstTransaction = transactionModel(1L, OperationTypeEnum.COMPRA_A_VISTA.operationTypeID, BigDecimal.ONE, LocalDateTime.now());
        Assertions.assertThrows(BusinessException.class,
                () -> createTransactionValidator.validateCreate(firstTransaction),
                createTransactionValidator.DEBIT_VALUE_NEED_BE_NEGATIVE);

        var secondTransaction = transactionModel(1L, OperationTypeEnum.COMPRA_PARCELADA.operationTypeID, BigDecimal.ONE, LocalDateTime.now());
        Assertions.assertThrows(BusinessException.class,
                () -> createTransactionValidator.validateCreate(secondTransaction),
                createTransactionValidator.DEBIT_VALUE_NEED_BE_NEGATIVE);

        var thirdTransaction = transactionModel(1L, OperationTypeEnum.SAQUE.operationTypeID, BigDecimal.ONE, LocalDateTime.now());
        Assertions.assertThrows(BusinessException.class,
                () -> createTransactionValidator.validateCreate(thirdTransaction),
                createTransactionValidator.DEBIT_VALUE_NEED_BE_NEGATIVE);
    }

    @Test
    void assertCreditOperationAmountNeedToBePositive() {
        var transaction = transactionModel(1L, OperationTypeEnum.PAGAMENTO.operationTypeID, new BigDecimal(-1), LocalDateTime.now());
        Assertions.assertThrows(BusinessException.class,
                () -> createTransactionValidator.validateCreate(transaction),
                createTransactionValidator.CREDIT_VALUE_NEED_BE_POSITIVE);
    }
}