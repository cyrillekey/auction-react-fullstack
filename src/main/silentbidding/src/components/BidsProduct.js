import axios from "axios";
import React,{Component} from "react";

class BidsProduct extends Component{
    constructor(props){
        super(props)
        this.state={
          bids:[],
          product:''
        }
        var user=localStorage.getItem("email")
        /**
         * check is user is loged in before rendering and redirects if not
         */
        if(user==null){
            console.log("is null")
            window.location.href="index.html#/login"
        }
    }
    componentDidMount(){
      /**
       * api request to get all winning bids by this user
       */
      axios({
        method:'GET',
        url:'https://silentbiddingapp.herokuapp.com/get-winning-bid-by-product/'+sessionStorage.getItem('prod'),
        headers:{
          "Content-Type":"application/json"
        }
      }).then(response=>{
          this.setState({bids:response.data})
      }).catch(response=>{
        alert("an error occured")
      })
      /**
       * request to get all products listed by this user
       */
      axios.get('https://silentbiddingapp.herokuapp.com/find-one-product/'+sessionStorage.getItem('prod')).then(reponse=>{
        this.setState({product:reponse.data})
      }).catch(reponse=>{
        alert("an error occured getting product info")
      })
    }
    handleWin=(id)=>{
      axios.get('https://silentbiddingapp.herokuapp.com/set-winning-bid/product/'+sessionStorage.getItem('prod')+'/bid/'+id).then(response=>{
        alert("bid won succesfully")
      }).catch(response=>{
        alert("an error occured while Updating product")
      })
    }
    
    render() {
      console.log(this.props.state);
      return (
            
<div class="l-wrapper">
  <div class="l-grid">
    <div class="l-grid__item l-grid__item--sticky">
      <div class="c-card u-bg--light-gradient u-text--dark">
        <div class="c-card__body">
          <div class="u-display--flex u-justify--space-between">
            <div class="u-text--left">
              <div class="u-text--small">Product Name</div>
              <h2>{this.state.product.pname}</h2>
            </div>
            <div class="u-text--right">
              <div class="u-text--small">Minimum Price</div>
              <h2>${this.state.product.minimum_price}</h2>
            </div>
          </div>
        </div>
      </div>
      <div class="c-card">
        <div class="c-card__body">
          <div class="u-text--center" id="winner">
          <div class="u-text-small u-text--medium u-mb--16">{this.state.product.pname}</div>
	<img class="c-avatar c-avatar--lg" src={this.state.product.imageUrl} alt=""/>
	<h3 class="u-mt--16">Date uploaded : {this.state.product.dateSaved}</h3>
	<span class="u-text--teal u-text--small"></span>
          </div>
        </div>
      </div>
    </div>
    <div class="l-grid__item">
      <div class="c-card">
        <div class="c-card__header">
          <h3>Expiry Date</h3>
          <select class="c-select">
            <option selected="selected">{this.state.product.expiry}</option>
          </select>
        </div>
        <div class="c-card__body">
          <ul class="c-list" id="list">
            <li class="c-list__item">
              <div class="c-list__grid">
                <div class="u-text--left u-text--small u-text--medium">Number</div>
                <div class="u-text--left u-text--small u-text--medium">Bid Amount</div>
                <div class="u-text--right u-text--small u-text--medium">Time placed</div>
              </div>
              {
                this.state.bids.map((bid,index)=>(
    <div class="c-list__grid">
			<div class="c-flag c-place u-bg--transparent">{index+1}</div>
			<div class="c-media">
				<div class="c-media__content">
					<p>${bid.bid_price}</p>
				</div>
			</div>
			<div class="u-text--right c-kudos">
				<div class="u-mt--8">
					<button onClick={()=>this.handleWin(bid.bid_id)}>Accept Bid</button>
				</div>
			</div>
		</div>
    ))
    }
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
        )
    }
}
export default BidsProduct;