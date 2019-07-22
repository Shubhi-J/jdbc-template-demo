package com.stackroute;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        // get bean by using applicationContext
        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
        ContactImpl contact=context.getBean("contactImpl",ContactImpl.class);

        // invoke this method to create a new table for contact
        // contact.createContactTable();

        // invoke this method to insert a new contact
         // contact.insertContact(new Contact("shubhi@gmail.com","Shubhi","8092898288","koramangala"));

        // invoke this method to delete a contact
        // contact.deleteContact("Shubhi");

        // invoke this method to read the records
        Contact contact1=contact.getContacts("Shubhi");
         System.out.println("Email is: "+contact1.getEmail()+" Name is: "+contact1.getName()+" Phone is: "+contact1.getPhone()
         +" Address is: "+contact1.getAddress());

        // invoke this method to update the contact
        // contact.updateContact("Shubhijain0606@gmail.com","Shubhi");
    }

}
