package com.contactfilter.service;

import com.contactfilter.domain.Contact;

import java.util.List;

public interface ContactFilterService {

    List<Contact>getAllContactsWithFilter(String nameFilter);

}
