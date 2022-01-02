import React from "react"
import Footer from "./Footer";
import Nav from "./Nav";
import Auxi from "../../hos/Auxi";
import { Router } from "react-router-dom";
/**
 * 
 * main website layout with a set footer and nav bar
 * @param {} props 
 * @returns 
 */
const Layout=(props)=>(
    
    <Auxi>
    <Nav/>
    <main className="wrapper">
        {
        props.children//where components to be renered in the layout will be displayed
        }
    </main>
    <Footer/>
    </Auxi>
    

);
export default Layout;