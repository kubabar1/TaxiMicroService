import React, { Component } from "react";
import * as SockJS from "sockjs-client";
import Stomp from "stompjs";
import { store } from 'react-notifications-component';

class Form extends Component {

  constructor() {
    super();

    this.state = {
      ms: [],
      ws: null,
      token: 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGFtMTIzIiwicm9sZXMiOlsiUEFTU0VOR0VSX1JPTEUiXSwiZXhwIjo0NzU0NTA5MjYxLCJpYXQiOjE2MDA5MDkyNjF9.0ndas5eKnnGjlLDd_lDvkAEXq3hiSEuH7Katj-pGiWoYpn5OFvMRTbyySst28u-ParT2XjBsLV7MX0gdcBAHxg'
    };
  }


  connect = e => {
    e.preventDefault();
    const wsocket = new SockJS("http://localhost:8080/stomp");
    const ws = Stomp.over(wsocket)
    const authHeader = {'X-Authorization': this.state.token};
    this.setState({
      ws
    });
    ws.connect(authHeader, frame => {
      store.addNotification({
        message: "Connected",
        type: "success",
        insert: "top",
        container: "top-right",
        animationIn: ["animate__animated", "animate__fadeIn"],
        animationOut: ["animate__animated", "animate__fadeOut"],
        dismiss: {
          duration: 3000,
          onScreen: true
        }
      });
      ws.subscribe("/user/queue/errors", message => {
        console.log("Error " + message.body);
        store.addNotification({
          message: message.body,
          type: "danger",
          insert: "top",
          container: "top-right",
          animationIn: ["animate__animated", "animate__fadeIn"],
          animationOut: ["animate__animated", "animate__fadeOut"],
          dismiss: {
            duration: 3000,
            onScreen: true
          }
        });
      });
      ws.subscribe("/user/queue/reply", message => {
        console.log("Message " + message.body);
        console.log(message);
        store.addNotification({
          message: message.body,
          type:"info",
          insert: "top",
          container: "top-right",
          animationIn: ["animate__animated", "animate__fadeIn"],
          animationOut: ["animate__animated", "animate__fadeOut"],
          dismiss: {
            duration: 3000,
            onScreen: true
          }
        });
        this.setState(prevState => ({ms: [...prevState.ms, message.body]}));
      }, authHeader);
      /*ws.subscribe("/topic/news", message => {
        console.log("Message " + message.body);
        this.setState(prevState => ({ms: [...prevState.ms, message.body]}));
      }, authHeader);*/
    }, error => {
      console.log("STOMP error " + error);
      store.addNotification({
        message: error,
        type: "danger",
        insert: "top",
        container: "top-right",
        animationIn: ["animate__animated", "animate__fadeIn"],
        animationOut: ["animate__animated", "animate__fadeOut"],
        dismiss: {
          duration: 3000,
          onScreen: true
        }
      });
      this.setState({
        ws: null
      });
    });
    console.log("Connected");
  }

  disconnect = e => {
    e.preventDefault();
    const ws = this.state.ws;
    if (ws != null) {
      ws.disconnect();
    }
    this.setState({
      ws: null
    });
    store.addNotification({
      message: "Disconnected",
      type: "warning",
      insert: "top",
      container: "top-right",
      animationIn: ["animate__animated", "animate__fadeIn"],
      animationOut: ["animate__animated", "animate__fadeOut"],
      dismiss: {
        duration: 3000,
        onScreen: true
      }
    });
    console.log("Disconnected");
  }

  send = e => {
    const authHeader = {'X-Authorization': this.state.token};
    e.preventDefault();
    const ws = this.state.ws;
    ws.send("/app/message", authHeader, JSON.stringify({message: "It's working!!!!"}));
    console.log("Message sent");
  }

  /*sendPublic = e => {
    const authHeader = {'X-Authorization': this.state.token};
    e.preventDefault();
    const ws = this.state.ws;
    ws.send("/app/news", authHeader, JSON.stringify({message: "Public message."}));
    console.log("Public message sent");
  }*/

  getConnectionStatus = () => {
    return this.state.ws == null ? "Disconnected" : "Connected";
  }

  render() {
    // <button onClick={this.sendPublic} disabled={this.state.ws==null}>Send public</button>
    return (
      <form>
        <button onClick={this.connect}  disabled={this.state.ws!=null}>Connect</button>
        <button onClick={this.disconnect}  disabled={this.state.ws==null}>Disconnect</button>
        <button onClick={this.send} disabled={this.state.ws==null}>Send</button>
        <h2>Connection status: {this.getConnectionStatus()}</h2>
        <h2>Received messages:</h2>
        {this.state.ms && this.state.ms.length>0 ? this.state.ms.map((el, i) => {return (<p>{el}</p>);}) : null}
      </form>
    );
  }
}

export default Form;
