package com.contactfilter.controller;

import com.contactfilter.domain.Contact;
import com.contactfilter.service.ContactFilterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ContactController.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactFilterService contactFilterService;

    private static final String REGEXP = "^a.*$";

    private List<Contact> contactList = new ArrayList<>();

    @Before
    public void initTestData() {
        Contact contact0 = new Contact();
        Contact contact1 = new Contact();
        Contact contact2 = new Contact();
        Contact contact3 = new Contact();
        Contact contact4 = new Contact();
        contact0.setName("vasya");
        contact1.setName("petya");
        contact2.setName("anya");
        contact3.setName("sasha");
        contact4.setName("anton");
        contactList.add(0, contact0);
        contactList.add(1, contact1);
        contactList.add(2, contact2);
        contactList.add(3, contact3);
        contactList.add(4, contact4);
    }

    @Test
    public void getAllContactsWithFilter() throws Exception {
        Mockito.when(contactFilterService.getAllContactsWithFilter(REGEXP)).thenReturn(contactList);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/hello/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nameFilter\":\"^a.*$\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].name").value("vasya"))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].name").value("petya"))
                .andExpect(MockMvcResultMatchers.jsonPath("[2].name").value("anya"))
                .andExpect(MockMvcResultMatchers.jsonPath("[3].name").value("sasha"))
                .andExpect(MockMvcResultMatchers.jsonPath("[4].name").value("anton"));
    }
}