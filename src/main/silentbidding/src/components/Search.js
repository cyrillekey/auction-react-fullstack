import axios from "axios";

import React,{Component} from "react";
import Card from "./Card";
const main={
    width:'95vw',
    textAlign:'center'
}
class Search extends Component{
    constructor(props){
        super(props)
        this.state={
            items:[],
            null:''
        }
        
    }
 
    componentDidMount(){
        axios({
            method:'get',
            url:'http://localhost:8080/find-product/'+sessionStorage.getItem("searchName"),
            headers:{
            "Content-Type":"application/json"
            }
        }).then(response=>{
            if(response.data.length>0){
            this.setState({items:response.data})
            console.log(this.state.items)}
            else{
                this.setState({items:[],null:true})
            }
        }).catch(response=>{
            console.log("error happens here")
        })
    }
    render() { 
        var message=this.state.null ? <p className="center result">Not Product Found</p>: this.state.items.map(item=>(
            <Card key={item.productid} id={item.productid} name={item.pname} expiry={item.expiry} price={item.minimum_price} poster={item.imageUrl} showModal={()=>this.showModal(item.productid)}/>
        ))

        return (
            <div className='wrapper'>
            <h1 style={main}>Search Result</h1>
            
           {
           message
           /* {
            
           this.state.items.map(item=>(
               <Card key={item.productid} id={item.productid} name={item.pname} expiry={item.expiry} price={item.minimum_price} poster={item.imageUrl} showModal={()=>this.showModal(item.productid)}/>
           ))
           } */}

         
      
        </div> 
        );
    }
}
export default Search;