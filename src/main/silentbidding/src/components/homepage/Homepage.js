/**
 * react component that renders the home page
 */
import React, { Component } from 'react'//import react to use jsx and component
import Card from '../Card'
import axios from 'axios';
import Auxi from '../../hos/Auxi';
import EventCard from '../EventCard';
import Modal from '../Modal'
import PlaceBid from '../PlaceBid';
import NewsCard from '../NewsCard'
/**
 * styling used in home page
 */
const main={
    width:'95vw',
    textAlign:'center',
}
const mainTitle={
    width:'95vw',
    textAlign:'center',
    marginTop:'20vh',
    fontSize:'3em',
    color:'white'
}
const EventsBody={
    
}
class Homepage extends Component {
    constructor(props){/**
        default constructor for react class component */
        super(props);
        /**
         * states used within the class
         */
        this.state={
            items:[],//stores response from request
            show:false,
            header:"Place Bid",
            footer:"PlaceBid",
            desc:"",
            news:[],//stores news from news api
            events:[]//stores events from event api
            
            
        };
    }
    /**
     * funtion that executes to show the modal box if requested
     */
     showModal=(id)=>{
         
        this.setState({show:true,desc:<PlaceBid id={id}/>});//sets the state of the modal as a place bid
     } 
     /**
      * method that hides the modal box
      */
     hideModal=()=>{
         
         this.setState({show:false});
     }
     /**
      * first method that is called when component is rendered
      */
    componentDidMount() {
        /**
         * get request to the rest api to find all products
         */
        axios({
            method: 'get',//request method type
  url: 'http://127.0.0.1:8080/all-products',//url for request
  headers: { 
    'Authorization': 'Basic YWRtaW46YWRtaW4=', 
    /**
     * authorization since the api uses basic auth
     */
    'Content-Type': 'application/json'
  }
        })
        .then(res=>{
            /**
             * if api call is successfull add response to state
             */
            const items=res.data
            
            this.setState({items})//save response data to state
        })
        .catch(error=>{/**
            incase of error alert an error occured */
            alert("an error occured while fetching products...")
        })
        /**
         * api call to get news from the new67 api
         */
        /**
         * request body
         */
        var options = {
            method: 'GET',
            url: 'https://news67.p.rapidapi.com/v2/topic-search',
            params: {languages: 'en', search: 'museum',batchSize:'4'},
            headers: {
              'x-rapidapi-host': 'news67.p.rapidapi.com',
              'x-rapidapi-key': 'e56261b2e2msha48fb697c1e185dp18d4ffjsn3656a9b1fd85'
            }
            
          };
          axios.request(options).then(response=>{
              /**
               * if request is accepted save response to news state
               */
                const news=response.data.news
                this.setState({news:news})
          }).catch(function (error) {
            console.error(error);
        });
        /**
         * api call to get events from the newscatcher api
         */
        var options1 = {
            method: 'GET',
            url: 'https://newscatcher.p.rapidapi.com/v1/search',
            params: {q: 'Auction Event', lang: 'en', sort_by: 'relevancy', page: '1',page_size:'4', media: 'True'},
            headers: {
              'x-rapidapi-host': 'newscatcher.p.rapidapi.com',
              'x-rapidapi-key': 'e56261b2e2msha48fb697c1e185dp18d4ffjsn3656a9b1fd85'
            }
          };
          axios.request(options1).then(response1=> {
              this.setState({events:response1.data.articles})
              console.log(this.state.events)
        }).catch(function (error) {
            console.error(error);
        });
        
      }
     
    render(){
        /**
         * renders the homepage
         */
    return(
     <Auxi>
         <div className="intro">
            <h2 style={mainTitle}>Welcome to silentbidding the online auction for everyone</h2>
         </div>
        <div className='wrapper'>
            <h1 style={main}>Sample of products Available</h1>
            
           {/**
            * maps all cards and display them as card components
            */
           this.state.items.map(item=>(
               <Card key={item.productid} id={item.productid} name={item.pname} expiry={item.expiry} price={item.minimum_price} poster={item.imageUrl} desc={item.productDesc} showModal={()=>this.showModal(item.productid)}/>
           ))
           }
         
      
        </div> 
        <Modal show={this.state.show} handleClose={this.hideModal} header={this.state.header} footer={this.state.footer} desc={this.state.desc} id={this.state.id}></Modal>
        <div style={EventsBody} id="events">

        <h1 style={main}>Upcoming Events</h1>
          {/**
           * maps events from the event api to the event component
           */
              this.state.events.map(event=>(
                  <EventCard eventName={event.title} desc={event.summary} date={event.published_date} link={event.link}/>
    ))
          }
        </div>
        <div>
        <h1 style={main}>News Articles</h1>
        {
            /**
             * maps news from the news api to the news component
             */
           this.state.news.map(newss=>(
               <NewsCard title={newss.Title} description={newss.Summary} background={newss.Image} category={newss.Categories.label} subtitle={newss.Source} url={newss.Url} month={newss.PublishedOn}/>
           ))
           }
        </div>
     </Auxi>   
    )
}
}
export default Homepage;