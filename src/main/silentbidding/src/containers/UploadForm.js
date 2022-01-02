/**
 * 
 * class component that render component users use to list a produc for sale
 */

import axios from "axios";
import React,{Component} from "react";
import Auxi from '../hos/Auxi'
import './Upload.css'
class UploadForm extends Component{
  /**
   * default constructor when component is rendered
   * @param {*} props//passed from main component 
   */  

  constructor(props){
        super(props);
        this.state={
            productName:'',
            productDesc:'',
            productPrice:'',
            productUrl:'',
            status:'',
            message:'',
            productExpiry:''
        }
        if(localStorage.getItem("email")==null){
          console.log("is null")
          window.location.href="index.html#/login"
      }
    }
    /**
     * 
     * @param {*} e
     * method that handles the change of value in input box 
     */
    handleChange=(e)=>{
        let name=e.target.name
        let value=e.target.value
        this.setState({[name]:value})
    }
    handleUpload=(e)=>{
        e.preventDefault()
        var value=parseInt(this.state.productPrice)
        console.log(value)
        if(!value || isNaN(value) || value==0  ){
          
            this.setState({status:'error',message:'Invalid Product Price'})
        }else{

            this.setState({status:'error',message:''})
            console.log(value)
            var formData=JSON.stringify({
                "pname": this.state.productName,
                "minimum_price": value,
                "dateSaved": Date.now(),
                "imageUrl": this.state.productUrl,
                "expiry": this.state.productExpiry,
                "productDesc":this.state.productDesc
            });
            axios({
                method:'post',
                url:'http://localhost:8080/add-new-product/'+localStorage.getItem('email'),
                data:formData,
                headers:{
                    "Content-Type":"application/json"
                  }
            }).then(response=>{
                if(response.status=="201"){
                    this.setState({status:'success',message:'File uploaded successfully'})
                }
            }).catch(response=>{
              console.log(response.response)
                this.setState({status:'error',message:'An error occured try again'})
            })
        }
        console.log((this.state.productExpiry))
    }
    render() {
        return (
            <Auxi>
                <div class="container">  
  <form id="contact" action="" method="post">
    <h3>List New Product</h3>
    <h4>List new product for sale</h4>
    <fieldset>
      <input placeholder="Product Name" type="text" name="productName" required value={this.state.productName} onChange={this.handleChange}/>
    </fieldset>
    <fieldset>
      <input placeholder="Product Description" type="text" name="productDesc" required value={this.state.productDesc} onChange={this.handleChange}/>
    </fieldset>
    <fieldset>
      <input placeholder="Product Price" type="text" name="productPrice" required min="0" onChange={this.handleChange} value={this.state.productPrice}/>
    </fieldset>
    <fieldset>
      <input placeholder="Your product image url starts with http://" type="url" name="productUrl" onChange={this.handleChange} required value={this.state.productUrl}/>
    </fieldset>
    <fieldset>
      <input placeholder="Expiry Date" type="date" name="productExpiry" onChange={this.handleChange} required value={this.state.productExpiry}/>
    </fieldset>
    <p className={this.state.status}>{this.state.message}</p>
    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending" onClick={this.handleUpload}>Submit</button>
    </fieldset>
  </form>
 
  
</div>
            </Auxi>
        )
    }
}

export default UploadForm;