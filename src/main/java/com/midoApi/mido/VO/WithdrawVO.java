package com.midoApi.mido.VO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
public class WithdrawVO extends TransactionFormVO {

    public Long numeroConta;

}
