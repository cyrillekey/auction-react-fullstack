import React,{ Component } from "react";
import axios from "axios";
class PlaceBid extends Component{
    constructor(props){
        super(props)
        this.state={
            bidamount:"",
            status:'',
            type:'error',
            pass:false
        }
        if(localStorage.getItem("email")==null){
            console.log("is null")
            window.location.href="index.html#/login"
        }
    }
    handleBid=(e)=>{
        e.preventDefault()
        if(this.state.pass){
            this.setState({status:""})
            var formData=JSON.stringify({
                "bid_price":this.state.bidamount,
                "time_placed":Date.now()
            })
            axios({
                method:'post',
                url:"http://localhost:8080/add-new-bid/"+localStorage.getItem('email')+"/product/"+this.props.id,
                data:formData,
                headers:{
                    "Content-Type":"application/json"
                  }
            })
            .then(response=>{
                if(response.status=="201"){
                    this.setState({status:"Bid placed successfully",type:'success'})
                }
            }).catch(response=>{
                if(response.response.status=="404"){
                    this.setState({status:"an error occured",type:'error'})
                }
            })
        }else{
            this.setState({status:"Amount must be and integer",type:'error'})
        }
    }
    handleNumber=(e)=>{
        if(Number.isInteger(Number(e.target.value))){
            console.log(Number(e.target.value))
        this.setState({bidamount:e.target.value,pass:true})
        }else{
        this.setState({bidamount:e.target.value,pass:false})
            console.log(e.target.value)
        }
        
    }
    render() {
        
        return (
             <form>
                 <label>Bid Amount</label>
                 <input type='number' value={this.state.bidamount} onChange={this.handleNumber}/>
                 <button onClick={this.handleBid}>PlaceBid</button>
                 <p className={this.state.type}>{this.state.status}</p>
             </form>
        );
    }
}

export default PlaceBid;