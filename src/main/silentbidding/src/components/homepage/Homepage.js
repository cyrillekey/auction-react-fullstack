import React, { Component } from 'react'
import Card from '../Card'
import axios from 'axios';
import Auxi from '../../hos/Auxi';
import EventCard from '../EventCard';
import Modal from '../Modal'
import PlaceBid from '../PlaceBid';
import NewsCard from '../NewsCard'

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
    constructor(props){
        super(props);
        this.state={
            items:[],
            show:false,
            header:"Place Bid",
            footer:"PlaceBid",
            desc:"",
            news:[],
            events:[]
            
            
        };
    }
     showModal=(id)=>{
         
        this.setState({show:true,desc:<PlaceBid id={id}/>});
     } 
     hideModal=()=>{
         
         this.setState({show:false});
     }
    componentDidMount() {
        
        axios.get("https://silentbidding.herokuapp.com/all-products")
        .then(res=>{
            const items=res.data
            
            this.setState({items})
        })
        .catch(error=>{
            console.log(error.config)
        })
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
              
                const news=response.data.news
                this.setState({news:news})
          }).catch(function (error) {
            console.error(error);
        });
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
        
    return(
     <Auxi>
         <div className="intro">
            <h2 style={mainTitle}>Welcome to silentbidding the online auction for everyone</h2>
         </div>
        <div className='wrapper'>
            <h1 style={main}>Sample of products Available</h1>
            
           {
           this.state.items.map(item=>(
               <Card key={item.productid} id={item.productid} name={item.pname} expiry={item.expiry} price={item.minimum_price} poster={item.imageUrl} showModal={()=>this.showModal(item.productid)}/>
           ))
           }
         
      
        </div> 
        <Modal show={this.state.show} handleClose={this.hideModal} header={this.state.header} footer={this.state.footer} desc={this.state.desc} id={this.state.id}></Modal>
        <div style={EventsBody}>

        <h1 style={main}>Upcoming Events</h1>
          {
              this.state.events.map(event=>(
                  <EventCard eventName={event.title} desc={event.summary} date={event.published_date} link={event.link}/>
    ))
          }
        </div>
        <div>
        <h1 style={main}>News Articles</h1>
        {
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