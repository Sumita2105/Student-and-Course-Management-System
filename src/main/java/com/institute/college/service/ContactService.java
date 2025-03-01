package com.institute.college.service;

import com.institute.college.config.InstitueProps;
import com.institute.college.constants.ConstantsValues;
import com.institute.college.model.Contact;
import com.institute.college.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/*
@Slf4j, is a Lombok-provided annotation that will automatically generate an SLF4J
Logger static property in the class at compilation time.
* */
@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private InstitueProps eazySchoolProps;

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(ConstantsValues.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){

        int pageSize = eazySchoolProps.getPageSize();
        if(null!=eazySchoolProps.getContact() && null !=eazySchoolProps.getContact().get("pageSize"))
        {
            pageSize=Integer.parseInt(eazySchoolProps.getContact().get("pageSize").trim());
        }
        Pageable pageable = PageRequest.of(pageNum-1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());

        Page<Contact> msgPage = contactRepository.findByStatuswithQuery(ConstantsValues.OPEN, pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
       /* Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EazySchoolConstants.CLOSE);
        });*/
        int rows = contactRepository.updateMsgStatus(ConstantsValues.CLOSE, contactId);
        if(rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }



}
