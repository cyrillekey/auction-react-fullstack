package com.bidding.auction.user;

public class LoginDetails {
    private String email;
    private String password;

    public LoginDetails(String email,String password){
        this.email=email;
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return String.format("[password:%s,email:%s]", password,email);
    }
}
