
import './App.css';


import Layout from './components/Layout/Layout';
import { Component } from 'react';
import Login from './containers/Login'
import { HashRouter as Router,Route,Routes } from 'react-router-dom';
import Signup from './containers/Signup';
import Homepage from './components/homepage/Homepage';
import UploadForm from './containers/UploadForm'
import AllProducts from './components/AllProducts';
import Dashboard from './components/Dashboard';
import Search from './components/Search';
import BidsProduct from './components/BidsProduct'
class App extends Component {
  render (){
   return(
     /**
      * routes used in the project
      */
     <Router>
       <Routes>
         {/**
          * each route goes to a specific page defined by path
          * element refers to the component that is rendered in the route
          */}
       <Route path="/login" element={<Login/>}/> 
       <Route path="/" element={<Layout children={<Homepage/>}/>}/>
       <Route path="/signup" element={<Signup/>}/>
       <Route path="/upload" element={<Layout children={<UploadForm/>}/>}/> 
       <Route path="/all-products" element={<Layout children={<AllProducts/>}/>}/>
       <Route path="/account" element={<Layout children={<Dashboard/>}/>}/>
       <Route path="/search" element={<Layout children={<Search/>}/>}/>
       <Route path="/bidsProduct" element={<Layout children={<BidsProduct/>}/>}/>
      </Routes>
     </Router>
     
     
    );
  }

}

export default App;
