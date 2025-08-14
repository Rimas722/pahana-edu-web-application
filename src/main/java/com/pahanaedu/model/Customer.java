package com.pahanaedu.model;

public class Customer {

    private int accountNo;
    private String name;
    private String address;
    private String phone;
    private int unitsUsed;
    private String username;
    private String password;

    public Customer() {
    }

    public Customer(int accountNo, String name, String address, String phone, int unitsUsed, String username, String password) {
        this.accountNo = accountNo;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.unitsUsed = unitsUsed;
        this.username = username;
        this.password = password;
    }


    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUnitsUsed() {
        return unitsUsed;
    }

    public void setUnitsUsed(int unitsUsed) {
        this.unitsUsed = unitsUsed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDetails() {
        return "Account No: " + accountNo + "\nName: " + name + "\nAddress: " + address + "\nPhone: " + phone;
    }
}
