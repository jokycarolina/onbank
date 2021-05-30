package com.onbank.onbank.model;


import javax.persistence.*;

@Entity
@Table(name="type_account")
public class TypeAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_type")
    private int idType;

    @Column(name="type_name")
    private String typeName;



    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "TypeAccount{" +
                "idType=" + idType +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
