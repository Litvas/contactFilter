package com.contactfilter.service;

import com.contactfilter.domain.Contact;
import com.contactfilter.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ContactFilterService.class)
public class ContactFilterServiceTest {

    @MockBean
    private ContactRepository contactRepository;

    @MockBean
    private ContactFilterService contactFilterService;

    private static final String REGEXP = "^a.*$";

    private List<Contact> contactList = new ArrayList<>();
    private List<Contact> contactListForTest = new ArrayList<>();

    @Before
    public void initTestData() {
        Contact contact0 = new Contact();
        Contact contact1 = new Contact();
        Contact contact2 = new Contact();
        contact0.setName("asya");
        contact1.setName("petya");
        contact2.setName("anya");
        contactList.add(contact0);
        contactList.add(contact1);
        contactList.add(contact2);

        contactListForTest.add(contact1);
    }

    @Test
    public void getAllContactsWithFilter() {
        Mockito.when(contactRepository.findAll()).thenReturn(contactList);
        assertThat(contactFilterService.getAllContactsWithFilter(REGEXP)).isSameAs(contactListForTest);
    }
}