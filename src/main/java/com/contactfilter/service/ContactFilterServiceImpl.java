package com.contactfilter.service;

import com.contactfilter.domain.Contact;
import com.contactfilter.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactFilterServiceImpl implements ContactFilterService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContactsWithFilter(String nameFilter) {
        List<Contact> allContacts = (List<Contact>) contactRepository.findAll();
        return allContacts
                .stream()
                .filter(s -> !s.getName().matches(nameFilter))
                .collect(Collectors.toList());
    }
}
