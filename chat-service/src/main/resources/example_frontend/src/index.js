import Form from "./js/components/Form";
import React, { Component } from "react";
import ReactDOM from "react-dom";
import 'react-notifications-component/dist/theme.css';
import ReactNotification from 'react-notifications-component';

const wrapper = document.getElementById("container");
wrapper ? ReactDOM.render(<div><Form /><ReactNotification/></div>, wrapper) : false;