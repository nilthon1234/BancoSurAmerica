package com.banco.tarjeta.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cards")
@NamedStoredProcedureQuery(
        name = "sp_InsertCard",
        procedureName = "sp_InsertCard",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "dni", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "cardNumber", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "expirationDate", type = Date.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "creationDate", type = Date.class)
        }
)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int dni;
    @Column(name = "cardNumber")
    private String cardNumber;
    @Column(name = "creationDate")
    private Date creationDate;
    @Column(name = "expirationDate")
    private Date expirationDate;

}
