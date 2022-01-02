import React,{ Component } from "react";
import Auxi from "../hos/Auxi";
import Card from "./Card";
import axios from "axios";
class AllProducts extends Component{
    constructor(props){
        super(props)
        this.state={
            items:[]
        }
    }
    componentDidMount() {
        /**
         * get request to get all products from the rest web service
         */
        axios.get("http://localhost:8080/all-products")
        .then(res=>{
            const items=res.data
            /**
             * if request is successfull add response data to the response
             */
            this.setState({items})
        })
        .catch(error=>{
            console.log(error.config)
        })
        
      }
    render() {
        return (
             <Auxi>
                 <h1 className="center">All products</h1>
                 <div className="product-view" >
                 {/**
                  * maps each product saved in the items state to the product component.
                  */
           this.state.items.map(item=>(
               <Card key={item.productid} id={item.productid} name={item.pname} expiry={item.expiry} price={item.minimum_price} poster={item.imageUrl} showModal={()=>this.showModal(item.productid)}/>
           ))
           }
         
                 </div>
             </Auxi>
        );
    }
}
export default AllProducts;