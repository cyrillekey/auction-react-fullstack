/**
 * maps to request body that wil be sent during the login process
 */
package com.bidding.auction.user;

public class LoginDetails {
    private String email;//email of user loging in
    private String password;//password of user loging in

    //constructor when object is created
    public LoginDetails(String email,String password){
        this.email=email;
        this.password=password;
    }
    /**
     * setters and getters for the login class
     * @return
     */
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
    /**
     * to string method used by spring boot
     */
    @Override
    public String toString() {
        return String.format("[password:%s,email:%s]", password,email);
    }
}
