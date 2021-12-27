import React, { Component } from "react";
import Auxi from "../../hos/Auxi";
import './Footer.css'
class Footer extends Component{
    render(){
        return(
            <Auxi>
              <div id="Footer">
  
  <div id="tsaPromoContainer" className="clearAll">
   <div id="footerSignup">
     <a href="/signup"> <p><strong>Save 5%</strong> Sign Up to be the first to save!</p></a>
        
     <div>
          <a className="tsa-footer-cta" href="">Enter Email Address</a>
     </div>
  </div>
      <div id="footerGiftCard">
         <a href="">Find Mystery Bundles<br/></a>
      </div>
      <div id="footerLeague">
         <div id="footerLeagueIMG"></div>
         <div id="leagueTitle">
         <a href="/login">Join us <br/>Today for <strong>Free</strong></a>
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
                  <a href="12@mail.com">Contact Us</a>
               </div>
               <div className="tsa-feedback-button largeSwitch">
                  <a href="12@mail.com">Contact Us / Send Feedback</a>
               </div>
            </div>
            
            <div id="tsaQuickLinks" className="tsa-footer-column mobileSwitch" >
               <p className="tsa-footer-heading" >Quick Links</p>
               <ul>
                  <li>
                     <a href="/login">Login</a>
                  </li>
                  <li>
                     <a href="/signup">Signup</a>
                  </li>
                  <li>
                     <a href="http://stores.sportsauthority.com/">Find a Store</a>
                  </li>
                  <li>
                     <a href="/account">My Account</a>
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
                  <a href="http://www.sportsauthority.com/helpdesk/index.jsp?stillHaveQuestion=yes&amp;subdisplay=contact&amp;display=store">Contact Us<br/> Send Feedback</a>
               </div>
               <div className="tsa-feedback-button largeSwitch">
                  <a href="http://www.sportsauthority.com/helpdesk/index.jsp?stillHaveQuestion=yes&amp;subdisplay=contact&amp;display=store">Contact Us / Send Feedback</a>
               </div>
            </div>
         </div>
         <div id="tsaFooterSocial" className="clearAll">
            <ul>
               <li>
                  <a href="https://instagram.com/sportsauthority/" target="_blank" id="tsaInstagram" className="tsa-social-icon">
                  </a>
               </li>
               <li>
                  <a href="https://twitter.com/SportsAuthority" target="_blank" id="tsaTwitter" className="tsa-social-icon">
                  </a>
               </li>
               <li>
                  <a href="https://www.facebook.com/SportsAuthority" target="_blank" id="tsaFacebook" className="tsa-social-icon">
                  </a>
               </li>
               <li className="largeSwitch">
                  <div id="tsaField"></div>
                  <div id="tsaStadium"></div>
               </li>
               <li>
                  <a href="https://www.pinterest.com/sportsauthority/" target="_blank" id="tsaPinterest" className="tsa-social-icon">
                  </a>
               </li>
               <li>
                  <a href="https://www.youtube.com/user/sportsauthority" target="_blank" id="tsaYouTube" className="tsa-social-icon">
                  </a>
               </li>
               <li>
                  <a href="https://plus.google.com/+sportsauthority/posts" target="_blank" id="tsaGooglePlus" className="tsa-social-icon">
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