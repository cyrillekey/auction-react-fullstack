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
  
   handleSearch=(e)=>{
    
      this.setState({search:e.target.value})
      
   }
   logouthandle=(e)=>{
      e.preventDefault()
      window.localStorage.removeItem('email')
      window.location.href='/login'
   }
   handleSearchButton=(e)=>{
      e.preventDefault()
      console.log(this.state.search)
      sessionStorage.setItem("searchName",this.state.search)
      window.location.href="/search"
   }
   render(){
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
               <a href="/login">Sign up now to Start Bidding and Winning</a>
            </div>
            <div id="tsaTopMenu1" className="hide-414 hide-300">
               <ul>
                  <li><Link to="/all-products">Products</Link></li>
                  <li><Link to="/upload">Sell a Product</Link></li>
                  <li><a href="/">Events</a></li>
               </ul>
            </div>
            <div id="tsaTopMenu2" className="hide-414 hide-300">
               <ul>
               
                  <li className="hide-600 hide-414 hide-300"><a id="tsaWeeklyAd" href="/all-products">Pick your product to see our <span>weekly</span> Mystery Bundle</a></li>
               </ul>
            </div>
         </div>
         <div id="tsaHeaderRow2">
            <div id="tsaLogo" className="hide-414 hide-300">
               <Link to="/" className="noStyle">Silent Bidding</Link>
            </div>
            <div id="tsaSearch">
               <form action="http://shop.sportsauthority.com/search" name="search" method="get" id="headerSearchForm" >
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