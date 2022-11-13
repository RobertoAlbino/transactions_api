package com.roberto.pismo.transactions.business.enums;

import com.roberto.pismo.transactions.exceptions.BusinessException;

import java.util.List;

public enum OperationTypeEnum {

    COMPRA_A_VISTA(1, "COMPRA_A_VISTA"),
    COMPRA_PARCELADA(2, "COMPRA_PARCELADA"),
    SAQUE(3, "SAQUE"),
    PAGAMENTO(4, "PAGAMENTO");

    public Integer operationTypeID;
    public String description;

    OperationTypeEnum(int operationTypeID, String description) {
        this.operationTypeID = operationTypeID;
        this.description = description;
    }

    public static final String TYPE_OPERATION_NOT_FOUND = "O tipo da operação não foi encontrado";

    public static OperationTypeEnum find(int operationTypeID){
        for(OperationTypeEnum operation : values()){
            if(operation.operationTypeID.equals(operationTypeID)){
                return operation;
            }
        }
        throw new BusinessException(TYPE_OPERATION_NOT_FOUND);
    }

    public static boolean isDebitOperation(OperationTypeEnum operation) {
        return List.of(OperationTypeEnum.COMPRA_A_VISTA, OperationTypeEnum.COMPRA_PARCELADA, OperationTypeEnum.SAQUE).contains(operation);
    }

    public static boolean isCreditOperation(OperationTypeEnum operation) {
        return List.of(OperationTypeEnum.PAGAMENTO).contains(operation);
    }

}
