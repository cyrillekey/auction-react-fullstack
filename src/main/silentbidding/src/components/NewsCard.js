import React from "react";
import Auxi from "../hos/Auxi";
import './NewsCard.css'
const NewsCard=(props)=>{
  var date=new Date(props.month)
  const month=["January","February","March","April","May","June","July","August","September","October","November","December"]
  const monthDay=month[date.getMonth()]
    return(
        <Auxi>
            
  
  <div className="column">
    <div className="post-module hover">
      
      <div className="thumbnail">
        <div className="date">
          <div className="day">{date.getDay()}</div>
          <div className="month">{monthDay}</div>
        </div><img src={props.background} alt="news and events"/>
      </div>
      
      <div className="post-content">
        <div className="category">{props.category}</div>
        <h1 className="title">{props.title}</h1>
        <p className="description">{props.description}</p>
        <div className="post-meta"><span className="timestamp"><i className="fa fa-clock-o"></i>{date.getDate()}</span><span className="comments"><i className="fa fa-comments"></i><a href={props.url} target="_blank"  rel="noreferrer"> Read Details</a></span></div>
      </div>
    </div>
  </div>

        </Auxi>
    );
};

export default NewsCard;