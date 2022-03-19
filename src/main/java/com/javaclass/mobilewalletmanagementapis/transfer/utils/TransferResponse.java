package com.javaclass.mobilewalletmanagementapis.transfer.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransferResponse {
    private String code;
    private String message;
    private String corebankingResponseId;
    private String otherDetails;
}
