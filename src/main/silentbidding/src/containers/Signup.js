import React, { Component } from "react";
import { Link } from "react-router-dom";
import './Login.css';
import { FormErrors } from "./FormErrors";
import axios from "axios";
import Modal from "../components/Modal";

class Signup extends Component{
  
  constructor(props){
    super(props)
    this.state={
      email:'',
      password:'',
      username:'',
      formErrors:{email:'',password:'',username:''},
      emailValid:false,
      passwordValid:false,
      usernameValid:false,
      formValid:false,
      show:false,
      desc:"Desc goes here",
      status:'Signup'
    }
    
  }
  handleUserInput=(e)=>{
    const name=e.target.name
    const value=e.target.value
    //const password=e.target.password
    this.setState({[name]:value},()=>{this.validateField(name,value)});
  }
  validateField(fieldName,value){
    let fieldValidationErrors=this.state.formErrors;
    let emailValid=this.state.emailValid;
    let passwordValid=this.state.passwordValid;
    switch (fieldName) {
      case 'email':
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
    this.setState({
      formErrors:fieldValidationErrors,
      emailValid:emailValid,
      passwordValid:passwordValid
    },this.validateForm);
  }
  validateForm(){
    this.setState({formValid:this.state.emailValid && this.state.passwordValid});
  }
  handleSubmit=(e)=>{
    e.preventDefault()
    this.setState({status:'signing up ...'})
    if(this.state.formValid){
      var bodyFormData=JSON.stringify({
        "username":this.state.username,
        "email":this.state.email,
        "password":this.state.password
      })
      axios({
        method:"post",
        url:'https://silentbiddingapp.herokuapp.com/register-new',
        data:bodyFormData,
        headers:{
          "Content-Type":"application/json"
        }
      }).then(function(response){
        if(response.status=="201"){
          window.location.href="/"

            
        }
      })
    
      .catch(response=>{
        if(response.response.status=="405"){
          this.setState({show:true,desc:"Username already taken try a different Username",status:'Signup'})
        }
      })
    }
  }
  hideModal=()=>{
    this.setState({show:false});
  }
  render(){  
  return(
        <div>
         <div className="container">
    <div className="info">
      <h1>Silent Bidding</h1><span>World of Amazing Products <i className="fa fa-heart"></i>awaits</span>
    </div>
  </div>
  <div className="form">
    <div className="thumbnail"><img src="https://img.icons8.com/external-soft-fill-juicy-fish/60/000000/external-investment-investing-soft-fill-soft-fill-juicy-fish-9.png" alt="logo"/></div>
    <form className="register-form" method="POST">
      <FormErrors formErrors={this.state.formErrors}/>
      <Modal show={this.state.show} handleClose={this.hideModal} header="Signup Error" footer="Signup Error" desc={this.state.desc}></Modal>
      <input type="text" placeholder="name" name="username" value={this.state.username} onChange={this.handleUserInput}/>
      <input type="text" placeholder="email address" name="email" value={this.state.email} onChange={this.handleUserInput}/>
      <input type="password" placeholder="password" name="password" value={this.state.password} onChange={this.handleUserInput}/>
      <button disabled={!this.state.formValid} onClick={this.handleSubmit}>{this.state.status}</button>
      <p className="message">Already registered? <Link to="/login">Login</Link></p>
    </form>
    
  </div>
     </div>
    );
}
}

export default Signup;