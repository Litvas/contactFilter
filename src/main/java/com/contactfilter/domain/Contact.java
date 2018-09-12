package com.contactfilter.domain;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="contact_id_seq")
    @SequenceGenerator(name="contact_id_seq", sequenceName="contactIdSeq")
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
