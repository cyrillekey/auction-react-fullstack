import React, { Component } from "react";
import Auxi from '../hos/Auxi'
import './EventCard.css'

class EventCard extends Component{
  constructor(props){
    super(props)
  }  
  render(){
        return(
            <Auxi>
                <div className="event_container">
  <div className="event_bg" ></div>
  <div className="event_info">
    <div className="event_title">
      <h4>{this.props.eventName}</h4>
    </div>
    <div className="event_desc">
      <p>{this.props.desc}</p>
    </div>
    <div className="event_footer">
      <div className="event_date">
        <p>{this.props.date}</p>
      </div>
      <div className="event_more">
        <a href={this.props.link} target="_blank" rel="noreferrer" className="btn_more">
          Join event
        </a>
      </div>
    </div>
  </div>
</div>
            </Auxi>
        );
    }
}

export default EventCard;