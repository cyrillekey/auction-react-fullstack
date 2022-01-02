/**
 * class component used to render the login page
 */
import axios from "axios";
import React, { Component } from "react";
import { Link } from "react-router-dom";
import Modal from "../components/Modal";
import { FormErrors } from "./FormErrors";
import './Login.css';
class Login extends Component{
  /**
   * default constructor that initializes the states used in the login page
   * @param {*} props 
   */
  constructor(props){
    super(props)
    this.state={
      
      email:'',
      password:'',
      formValid:false,
      formErrors:{email:'',password:''},
      emailValid:false,
      passwordValid:false,
      usernameValid:false,
      show:false,
      desc:"Desc goes here",
      status:'Login'
      
      
    }

  }
   /**
    * method similar to signup page that validates the user input during login process
    * @param {*} fieldName 
    * @param {*} value 
    */
  validateField(fieldName,value){
    let fieldValidationErrors=this.state.formErrors;
    let emailValid=this.state.emailValid;
    let passwordValid=this.state.passwordValid;
    switch (fieldName) {
      case 'email':
        /**
         * regex to check if email entered by user is formed correctly
         */
        emailValid=value.match(/^([\w.%+-])+@([\w-]+\.)+([\w]{2,})$/i);
        fieldValidationErrors.email=emailValid ? '':'is invalid';
        break;
      case 'password':
        passwordValid=value.length >= 6;
        fieldValidationErrors.password=passwordValid ? '':'is to short';  
        break;
      default:
        break;
    }
    /**
     * 
     * updates the state and sets any errors that my have occured
     */
    this.setState({
      formErrors:fieldValidationErrors,
      emailValid:emailValid,
      passwordValid:passwordValid
    },this.validateForm);
  }/**
   * method that executes when the user preses the login button
   * @param {*} e 
   */
  handleLogin=(e)=>{
    e.preventDefault()
    this.setState({status:'Loging in ...'})
    /**
     * request body for the login request
     */
    var bodyFormData=JSON.stringify({
      "email":this.state.email,
      "password":this.state.password
    })
    /**
     * get request to the login controller that checks if the user has registered to be able to login
     */
    axios({
      method:"post",
      url:"https://silentbiddingapp.herokuapp.com/login-user",
      data:bodyFormData,
      headers:{
        "Content-Type":"application/json"
      }
    }).then(function(response){
        /**
         * if password and email are correct response status will be 200 
         * set localstorage with the user id and redirect to home page
         */
      if(response.status=="200"){
        
        window.localStorage.setItem("email",response.data.userId)
          window.location.href="/"
      }
    }).catch(response=>{
      if(response.response){
      console.log(response.response)
      if(response.response.status=="404"){
        /**
         * if error is catched display a modal box with the resulting error message
         */
      this.setState({show:true,desc:"User does not Exist",status:'Login'})
        
      }
      else if(response.response.status=="400"){
      this.setState({show:true,desc:"password does not exist",status:'Login'})
        
      }}
    })
    
  }
  handleUserInput=(e)=>{
    const name=e.target.name
    const value=e.target.value

    this.setState({[name]:value},()=>{this.validateField(name,value)})
  }
  validateForm(){
    this.setState({formValid:this.state.emailValid && this.state.passwordValid});
  } 
  hideModal=()=>{
    this.setState({show:false});
  }
  render(){  
  return(
     <div>
         <div className="container">
    <div className="info">
      <h1>Silent Bidding</h1><span>World of Products<i className="fa fa-heart"></i>awaits</span>
    </div>
  </div>
  <div className="form">
    <div className="thumbnail"><img src="https://img.icons8.com/external-soft-fill-juicy-fish/60/000000/external-investment-investing-soft-fill-soft-fill-juicy-fish-9.png" alt="logo"/></div>
    <form className="login-form" method="post">
      <FormErrors formErrors={this.state.formErrors}/>
      <Modal show={this.state.show} handleClose={this.hideModal} header="Login Error" footer="Login Error" desc={this.state.desc}></Modal>
      <input type="text" placeholder="Email" name="email" value={this.state.email} onChange={this.handleUserInput}/>
      <input type="password" placeholder="password" name="password" value={this.state.password} onChange={this.handleUserInput}/>
      <button onClick={this.handleLogin} disabled={!this.state.formValid}>{this.state.status}</button>
      <p className="message">Not registered? <Link to='/signup'> Create an account</Link> </p>
    </form>
  </div>
     </div>   
    );
}}
export default Login;