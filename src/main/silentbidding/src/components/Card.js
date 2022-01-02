
import React, { Component } from "react";
import './Card.css';
class Card extends Component{
    constructor(props){
        super(props);
        this.state={
            items:[],
            show:false,
            header:"Place Bid",
            footer:"PlaceBid",
            desc:"",
            id:""
        };
    }
     showModal=(id)=>{
         
        this.setState({show:true,id:id});
        console.log(id+"hellow")
     } 
     hideModal=()=>{
         
         this.setState({show:false});
     }
    render(){
    return(
        <div className="single-product">
        <div className="product-image">
            <img src={this.props.poster} alt="#"/>
            <div className="button">
                <a id={this.props.id} className="btn add-cart" onClick={this.props.showModal}><i className="lni lni-cart"></i> Place Bid</a>
            </div>
        </div>
        <div className="product-info">
            <span className="category">Close Time: {this.props.expiry}</span>
            <h4 className="title">
                <a href="{% url 'mainpage:single' slug=product.product_slug %}">{this.props.name}</a>
            </h4>
            <p>{this.props.desc}</p>
            <div className="price">
                <span>Price: ${this.props.price}</span>
            </div>
        </div>
    </div>
    );
}
}

export default Card;