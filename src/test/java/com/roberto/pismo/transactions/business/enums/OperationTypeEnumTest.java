package com.roberto.pismo.transactions.business.enums;

import com.roberto.pismo.transactions.exceptions.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OperationTypeEnumTest {

    @Test
    void assertFindReturnCorrectsValues() {
        var operation = OperationTypeEnum.find(1);
        Assertions.assertEquals(OperationTypeEnum.COMPRA_A_VISTA, operation);

        operation = OperationTypeEnum.find(2);
        Assertions.assertEquals(OperationTypeEnum.COMPRA_PARCELADA, operation);

        operation = OperationTypeEnum.find(3);
        Assertions.assertEquals(OperationTypeEnum.SAQUE, operation);

        operation = OperationTypeEnum.find(4);
        Assertions.assertEquals(OperationTypeEnum.PAGAMENTO, operation);

        Assertions.assertThrows(BusinessException.class,
                                () -> OperationTypeEnum.find(5),
                                OperationTypeEnum.TYPE_OPERATION_NOT_FOUND);
    }

    @Test
    void assertIsDebitOperation() {
        Assertions.assertTrue(OperationTypeEnum.isDebitOperation(OperationTypeEnum.COMPRA_A_VISTA));
        Assertions.assertTrue(OperationTypeEnum.isDebitOperation(OperationTypeEnum.COMPRA_PARCELADA));
        Assertions.assertTrue(OperationTypeEnum.isDebitOperation(OperationTypeEnum.SAQUE));
    }

    @Test
    void assertIsCreditOperation() {
        Assertions.assertTrue(OperationTypeEnum.isCreditOperation(OperationTypeEnum.PAGAMENTO));
    }
}