package org.portfolio.domain.customer;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class CustomerBuilder {

    private int idCustomer;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private String email;
    private String address;
    private LocalDate createdAt = LocalDate.now();

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^(?!\\s*$)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private static final Pattern PHONE_NUMBER_PATTERN =
            Pattern.compile("^(?:\\+\\d{1,3})?[-.\\s]?\\(?(\\d{1,4})\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{4}$");

    private static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private static boolean isValidPhone(String phone) {
        return PHONE_NUMBER_PATTERN.matcher(phone).matches();
    }

    public CustomerBuilder setId(int idCustomer) {
        if(idCustomer <= 0) {
            throw new IllegalArgumentException("id cannot be zero or negative");
        }
        else this.idCustomer = idCustomer;
        return this;
    }

    public CustomerBuilder setFirstName(String firstName) {
        if(firstName == null) {
            throw new IllegalArgumentException("firstName cannot be null");
        }
        else this.firstName = firstName;
        return this;
    }
    public CustomerBuilder setLastName(String lastName) {
        if(lastName == null) {
            throw new IllegalArgumentException("lastName cannot be null");
        }
        else this.lastName = lastName;
        return this;
    }

    public CustomerBuilder setAge(int age) {
        if(age < 15) {
            throw new IllegalArgumentException("age cannot be minor than 15 years old");
        }
        else this.age = age;
        return this;
    }

    public CustomerBuilder setPhoneNumber(String phoneNumber) {
        if(!isValidPhone(phoneNumber)) {
            throw new IllegalArgumentException("phoneNumber cannot be minor than 0 or negative");
        }
        else this.phoneNumber = phoneNumber;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        if(!isValidEmail(email)) {
            throw new IllegalArgumentException("email cannot be empty");
        }
        else this.email = email;
        return this;
    }

    public CustomerBuilder setAddress(String address) {
        if(address == null) {
            throw new IllegalArgumentException("address cannot be empty");
        }
        else this.address = address;
        return this;
    }

    public Customer Build() {
        if (idCustomer <= 0) {
            throw new IllegalArgumentException("id cannot be zero or negative");
        }
        Objects.requireNonNull(firstName, "firstName cannot be null");
        Objects.requireNonNull(lastName, "lastName cannot be null");
        if (age < 15) {
            throw new IllegalArgumentException("age cannot be minor than 15 years old");
        }
        Objects.requireNonNull(phoneNumber, "phoneNumber cannot be null");
        Objects.requireNonNull(email, "email cannot be null");
        Objects.requireNonNull(address, "address cannot be null");

        return new Customer(
            idCustomer,
            firstName,
            lastName,
            age,
            phoneNumber,
            email,
            address,
            createdAt
        );
    }
}
