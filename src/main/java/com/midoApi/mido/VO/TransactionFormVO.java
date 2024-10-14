package com.midoApi.mido.VO;

import com.midoApi.mido.models.Cliente;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Getter
@Setter
public class TransactionFormVO {

    protected BigDecimal value;

    protected String cpf;

    protected String tpMov;

    protected Date dtMov;

    protected String inOutFlag;

    public void setTedType(boolean isReciever) {
        if (isReciever) {
            this.tpMov = "Recebimento de TED";
            this.inOutFlag = "income";
        } else {
            this.inOutFlag = "outcome";
            this.tpMov = "Envio de TED";
        }
    }


    public void setPixType(boolean isReciever) {
        if (isReciever) {
            this.tpMov = "Recebimento de PIX";
            this.inOutFlag = "income";
        } else {
            this.inOutFlag = "outcome";
            this.tpMov = "Envio de PIX";
        }
    }
}
