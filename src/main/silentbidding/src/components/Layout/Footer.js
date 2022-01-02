/**
 * class component to render the footer
 */
import React, { Component } from "react";
import Auxi from "../../hos/Auxi";
import './Footer.css'
import {Link} from 'react-router-dom';
class Footer extends Component{
    render(){
        return(
            <Auxi>
              <div id="Footer">
  
  <div id="tsaPromoContainer" className="clearAll">
   <div id="footerSignup">
     <Link to="/signup"> <p><strong>Save 5%</strong> Sign Up to be the first to save!</p></Link>
        
     <div>
          <a className="tsa-footer-cta" href="">Enter Email Address</a>
     </div>
  </div>
      <div id="footerGiftCard">
         <Link to="/all-products">Find Mystery Bundles<br/></Link>
      </div>
      <div id="footerLeague">
         <div id="footerLeagueIMG"></div>
         <div id="leagueTitle">
         <Link to="/login">Join us <br/>Today for <strong>Free</strong></Link>
         </div>
      </div>
   </div>
  
      <div id="tsaFooter">
         
         <div id="containerWrap">
            <div id="tsaContactUsLarge" className="tsa-footer-help">
               <div>
                  <p className="tsa-footer-heading">Need Help Online?</p>
                  <p className="tsa-phone-number">
                     <a href="tel:+18888019164">123-456-789</a>
                  </p>
               </div>
               <div>
                  <p className="tsa-footer-heading">Visit us</p>
                  <p className="tsa-phone-number">
                     <a href="tel:+18003608721">Dublin,ireland</a>
                  </p>
               </div>
               <div className="tsa-feedback-button mobileSwitch">
                  <a href="mailto: 12@mail.com">Contact Us</a>
               </div>
               <div className="tsa-feedback-button largeSwitch">
                  <a href="mailto: 12@mail.com">Contact Us / Send Feedback</a>
               </div>
            </div>
            
            <div id="tsaQuickLinks" className="tsa-footer-column mobileSwitch" >
               <p className="tsa-footer-heading" >Quick Links</p>
               <ul>
                  <li>
                     <Link to="/login">Login</Link>
                  </li>
                  <li>
                     <Link to="/signup">Signup</Link>
                  </li>
                  <li>
                     <Link to="/all-products">Find products</Link>
                  </li>
                  <li>
                     <Link to="/account">My Account</Link>
                  </li>
                  
               </ul>
            </div>
            
            
            
            <div id="tsaContactUs" className="tsa-footer-help">
               <div>
                  <p className="tsa-footer-heading">Need Help Online?</p>
                  <p className="tsa-phone-number">
                     <a href="tel:+18888019164">1-888-801-9164</a>
                  </p>
               </div>
               <div>
                  <p className="tsa-footer-heading">Need Help In Store?</p>
                  <p className="tsa-phone-number">
                     <a href="tel:+18003608721">1-800-360-8721</a>
                  </p>
               </div>
               <div className="tsa-feedback-button mobileSwitch">
                  <a href="">Contact Us<br/> Send Feedback</a>
               </div>
               <div className="tsa-feedback-button largeSwitch">
                  <a href="">Contact Us / Send Feedback</a>
               </div>
            </div>
         </div>
         <div id="tsaFooterSocial" className="clearAll">
            <ul>
               <li>
                  <a href="https://instagram.com/" target="_blank" id="tsaInstagram" className="tsa-social-icon">
                  </a>
               </li>
               <li>
                  <a href="https://twitter.com/" target="_blank" id="tsaTwitter" className="tsa-social-icon">
                  </a>
               </li>
               <li>
                  <a href="https://www.facebook.com/" target="_blank" id="tsaFacebook" className="tsa-social-icon">
                  </a>
               </li>
               <li className="largeSwitch">
                  <div id="tsaField"></div>
                  <div id="tsaStadium"></div>
               </li>
               <li>
                  <a href="https://www.pinterest.com/" target="_blank" id="tsaPinterest" className="tsa-social-icon">
                  </a>
               </li>
               <li>
                  <a href="https://www.youtube.com/user/" target="_blank" id="tsaYouTube" className="tsa-social-icon">
                  </a>
               </li>
               <li>
                  <a href="https://plus.google.com/" target="_blank" id="tsaGooglePlus" className="tsa-social-icon">
                  </a>
               </li>
            </ul>
         </div>
         <div className="legal">
            <div className="left">
            </div>
            
         </div>
         <div className="legalBig">
            <p>&copy; 2021 Silent bidding.</p>
         </div>
      </div>
     </div>
            </Auxi>
        );

    }
}

export default Footer;