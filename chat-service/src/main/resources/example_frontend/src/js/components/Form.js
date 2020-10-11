import React, { Component } from "react";
import * as SockJS from "sockjs-client";
import Stomp from "stompjs";
import { store } from 'react-notifications-component';
import './Form.css'

class Form extends Component {

  constructor() {
    super();

    this.state = {
        bookingId: null,
        messageContent: null,
        ms: [],
        ws: null,
        senderUsername: null,
        senderToken: null,
        token_adam123: 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGFtMTIzIiwicm9sZXMiOlsiUEFTU0VOR0VSX1JPTEUiXSwiZXhwIjo0NzU0NTA5MjYxLCJpYXQiOjE2MDA5MDkyNjF9.0ndas5eKnnGjlLDd_lDvkAEXq3hiSEuH7Katj-pGiWoYpn5OFvMRTbyySst28u-ParT2XjBsLV7MX0gdcBAHxg',
        token_adam1234: 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGFtMTIzNCIsInJvbGVzIjpbIlBBU1NFTkdFUl9ST0xFIl0sImV4cCI6NDc1NTE5Njk1MCwiaWF0IjoxNjAxNTk2OTUwfQ.50l5zxD062qZnLgjDdnO_Y-ho24HFe937rohYLpeP1_TJIzXXDSX7KxDGh_EeE_yFWm86acPyd5Np4OARVf2cQ',
        token_hhurring1d_driver: 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoaHVycmluZzFkIiwicm9sZXMiOlsiRFJJVkVSX1JPTEUiXSwiZXhwIjo0NzU1OTY1OTUxLCJpYXQiOjE2MDIzNjU5NTF9.yWoYbIu3_QXnHc0b16KT0MDejuI5qDLgicNel2ChOuxS6pgZRqYh-HGAId_iaAXJZS5BgKrAa062CsQ3oWjnUw'
    };
  }

  showNotification = (type, message) => {
    store.addNotification({
      message: message,
      type: type,
      insert: "top",
      container: "top-right",
      animationIn: ["animate__animated", "animate__fadeIn"],
      animationOut: ["animate__animated", "animate__fadeOut"],
      dismiss: {
        duration: 3000,
        onScreen: true
      }
    });
  }

  connect = (event, token, senderUsername) => {
    event.preventDefault();
    const authHeader = {'X-Authorization': token};
    const ws = Stomp.over(SockJS("http://localhost:8080/stomp"));

    this.setState({ws: ws, senderUsername:senderUsername, senderToken: token});

    ws.connect(authHeader, () => {
      this.showNotification("success", "Connected")
      ws.subscribe("/user/queue/errors", message => {
        console.log("Error " + message.body);
        this.showNotification("danger", message.body)
      }, authHeader);
      ws.subscribe("/user/queue/reply/bookings", message => {
        console.log("Message " + message.body);
        console.log(message);
        this.showNotification("info", message.body)
        this.setState(prevState => ({ms: [...prevState.ms, message.body]}));
      });
    }, error => {
      console.log("STOMP error " + error);
      this.showNotification("danger", error);
      this.setState({ws: null});
    });
    console.log("Connected");
  }

  disconnect = e => {
    e.preventDefault();
    const ws = this.state.ws;
    if (ws != null) {
      ws.disconnect();
      this.setState({
        ws: null
      });
    }
    this.showNotification("warning", "Disconnected")
    console.log("Disconnected");
  }

  send = e => {
    e.preventDefault();
    const message = {senderUsername: this.state.senderUsername, content: this.state.messageContent};
    const authHeader = {'X-Authorization': this.state.senderToken};
    const ws = this.state.ws;
    ws.send("/app/topic/bookings/" + this.state.bookingId, authHeader, JSON.stringify(message));
    console.log("Message sent");
  }

  getConnectionStatus = () => {
    return this.state.ws == null ? "Disconnected" : "Connected";
  }

  handleInputChange = (e) => {
      const target = e.target;
      const value = target.type === 'checkbox' ? target.checked : target.value;
      const name = target.name;
      this.setState({
          [name]: value
      });
  }

  render() {
    return (
      <div>
        <h2>Connection status: {this.getConnectionStatus()}</h2>
        <label>Booking ID (required):</label>
        <input name="bookingId" onChange={this.handleInputChange}/>
        <br/>
        <br/>
        <button
            onClick={(event) => this.connect(event, this.state.token_adam123, "adam123")}
            disabled={this.state.ws!=null || this.state.bookingId==null}
        >
          Connect as adam123 (passenger)
        </button>
        <button
            onClick={(event) => this.connect(event, this.state.token_adam1234, "adam1234")}
            disabled={this.state.ws!=null || this.state.bookingId==null}
        >
          Connect as adam1234 (passenger)
        </button>
        <button
            onClick={(event) => this.connect(event, this.state.token_hhurring1d_driver, "hhurring1d")}
            disabled={this.state.ws!=null || this.state.bookingId==null}
        >
          Connect as hhurring1d (driver)
        </button>
        <button
            onClick={this.disconnect}
            disabled={this.state.ws==null}
        >
          Disconnect
        </button>
        <br/>
        <br/>
        <label>Message:</label>
        <input name="messageContent" onChange={this.handleInputChange}/>
        <br/>
        <button
            onClick={this.send}
            disabled={this.state.ws==null}
        >
          Send
        </button>
        <h2>Chat:</h2>
        {this.state.ms && this.state.ms.length>0 ? this.state.ms.map((el, i) => {return (<p>{el}</p>);}) : null}
      </div>
    );
  }
}

export default Form;
