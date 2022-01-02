import React, { Component } from "react";
import Auxi from "../../hos/Auxi";
import { Link } from "react-router-dom";
import axios from "axios";


class  Nav extends Component{
   constructor(props){
      super(props)
      this.state={
         search:'',
         
      }
      
   }
   /**
    * 
    * @param {e} e 
    * responsible for performing search from the search bar
    */
  
   handleSearch=(e)=>{
    
      this.setState({search:e.target.value})
      
   }/**
    * 
    * @param {e} e
    * logout the user 
    */
   logouthandle=(e)=>{
      e.preventDefault()
      window.localStorage.removeItem('email')//unsets the user from localstorage
      window.location.href='index.html#/login'//redirect to login page after logout
   }/**
    * 
    * @param {*} e
    * function to handle search 
    */
   handleSearchButton=(e)=>{
      e.preventDefault()
      sessionStorage.setItem("searchName",this.state.search)
      if(window.location.pathname=="/search"){
         window.location.reload()
      }else{
      window.location.href="#/search"
      }
   }
   render(){
      /**
       * check if user is signed in 
       */
   let signedId=window.localStorage.getItem("email");
   let signup=null;
   if(signedId){
      signedId=false
   }else{
      signedId=true
   }
      switch (signedId) {
         case true:
         signup=<div className="hide-600 hide-414 hide-300" id="tsaMyAccount">
         <p> <Link to="/login">Login or Signup</Link> </p>
      </div>
            break;
            case false:
               signup=<div><div className="hide-600 hide-414 hide-300" id="tsaMyAccount">
               <Link to="/account">My Account</Link>
               
            </div>   
            <div id="tsaCart" className="hide-414 hide-300">
            <p><a onClick={this.logouthandle}>Logout</a></p>
            
         </div></div>
               break;
      
         default:
            break;
      }
    
        return(
                
                 <Auxi>
                <div id="Header">
      <div id="tsaHeader">
         <div id="tsaHeaderRow1">
            <div id="tsaMobileNav" className="show-414 show-300">
               <div id="tsaMobileMenu">
                
               </div>
           
               <div id="tsaCloseMobile"></div>
            </div>
            <div id="tsaHeaderTextPromo" className="hide-768 hide-600 hide-414 hide-300">
               <Link to="/login">Sign up now to Start Bidding and Winning</Link>
            </div>
            <div id="tsaTopMenu1" className="hide-414 hide-300">
               <ul>
                  <li><Link to="/all-products">Products</Link></li>
                  <li><Link to="/upload">Sell a Product</Link></li>
                  
               </ul>
            </div>
            <div id="tsaTopMenu2" className="hide-414 hide-300">
               <ul>
               
                  <li className="hide-600 hide-414 hide-300"><Link id="tsaWeeklyAd" to="/all-products">Click here to see <span>weekly</span> Mystery Bundle</Link></li>
               </ul>
            </div>
         </div>
         <div id="tsaHeaderRow2">
            <div id="tsaLogo" className="hide-414 hide-300">
               <Link to="/" className="noStyle">Silent Bidding</Link>
            </div>
            <div id="tsaSearch">
               <form action="" name="search" method="get" id="headerSearchForm" >
                  <input type="search" autoComplete="off" placeholder="Search for shoes, clothing, interests, brands, etc." name="search" id="sli_search_1" value={this.state.search} onChange={this.handleSearch} />
                  <input type="submit" value="Search" id="search-button" onClick={this.handleSearchButton}/>
               </form>
            </div>
            {signup}
            
         </div>
         
      </div>
      
   </div>
            </Auxi>
     
            
        );
   }
    }

export default Nav;