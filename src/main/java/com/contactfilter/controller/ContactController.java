package com.contactfilter.controller;

import com.contactfilter.domain.Contact;
import com.contactfilter.service.ContactFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hello/contacts")
public class ContactController {

    @Autowired
    private ContactFilterService contactFilterService;

    @PostMapping(consumes = "application/json")
    public List<Contact> getAllContactsWithFilter(@RequestBody Map<String, String> requestMap) {
        return contactFilterService.getAllContactsWithFilter(requestMap.get("nameFilter"));
    }
}
