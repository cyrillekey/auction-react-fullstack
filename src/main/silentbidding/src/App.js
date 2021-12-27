
import './App.css';

//import Footer from './components/Footer';

import Layout from './components/Layout/Layout';
import { Component } from 'react/cjs/react.development';
import Login from './containers/Login'
import { BrowserRouter as Router,Route,Routes } from 'react-router-dom';
import Signup from './containers/Signup';
import Homepage from './components/homepage/Homepage';
import UploadForm from './containers/UploadForm'
import AllProducts from './components/AllProducts';
import Dashboard from './components/Dashboard';
import Search from './components/Search';
import BidsProduct from './components/BidsProduct'
//eslint-disable-next-line
class App extends Component {
  render (){
   return(
     <Router>
       <Routes>
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
