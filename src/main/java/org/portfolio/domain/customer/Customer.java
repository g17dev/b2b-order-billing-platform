package org.portfolio.domain.customer;

import java.time.LocalDate;

public class Customer {
    private int idCustomer;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private String email;
    private String address;
    private LocalDate createdAt;

    public Customer(
            int idCustomer,
            String firstName,
            String lastName,
            int age,
            String phoneNumber,
            String email,
            String address,
            LocalDate createdAt) {
        this.idCustomer = idCustomer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
    }


    // Getters
    public int getId() { return  idCustomer; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public LocalDate getCreatedAt() { return createdAt; }
}
