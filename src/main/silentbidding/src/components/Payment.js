import React from "react";
import $ from 'jquery';
import './Payment.css'
let swicth=()=>{
    $('.pay-select__item').on('click', function(){
        $('.pay-select__item').removeClass('is-active');
        $(this).addClass('is-active');
        
        if($(this).hasClass('pay-select--card')) {
          $('.select-body__content').removeClass('is-active');
          $('.select-body--card').addClass('is-active');
        } else {
          $('.select-body__content').removeClass('is-active');
          $('.select-body--paypal').addClass('is-active');
        }
      })
}
var Payment=()=>{
    return(
        <div className="sub-body">
        <header className="header">
          <h1 className="price"><span className="price__dollar">$</span>15.00<span className="price__time">/ mo</span></h1>
          <p className="desc">Chicharrón Subscription</p>
        </header>
      
        <div className="pay-select">
          <div className="pay-select__item pay-select--card is-active">
            <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/346994/Card%20Icon.svg" alt="" />
            <p>Debit/Credit Card</p>
          </div>
      
          <div className="separator"></div>
      
          <div className="pay-select__item pay-select--paypal">
            <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/346994/Pp%20Icon.svg" alt="" />
            <p>PayPal</p>
          </div>
        </div>
      
        <div className="select-body">
          <div className="select-body__content select-body--card is-active">
            <form action="/" method="post" id="cardForm">
              <label className="form__label" for="card-number">Card Number</label>
              <div className="card-input" id="card-number"></div>
      
              <label className="form__label" for="expiration-month">Expiration Date</label>
              <div className="date__container">
                <div className="card-input" id="expiration-month"></div>
      
                <div className="card-input" id="expiration-year"></div>
              </div>
      
              <label className="form__label" for="cvv">CVV</label>
              <div className="card-input" id="cvv"></div>
      
              <label className="form__label" for="cvv">Billing Zip Code</label>
              <div className="card-input" id="postal-code"></div>
              
              <input type="submit" value="Subscribe" id="submit" />
            </form>
          </div>
          
          <div className="select-body__content select-body--paypal">
            
          </div>
          
          
        </div>
      </div> 
    );
}
export default Payment;