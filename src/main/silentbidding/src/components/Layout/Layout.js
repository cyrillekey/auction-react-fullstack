import React from "react"
import Footer from "./Footer";
import Nav from "./Nav";
import Auxi from "../../hos/Auxi";
import { Router } from "react-router-dom";

const Layout=(props)=>(
    
    <Auxi>
    <Nav/>
    <main className="wrapper">
        {props.children}
    </main>
    <Footer/>
    </Auxi>
    

);
export default Layout;