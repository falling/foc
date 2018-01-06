import React from 'react';
import ReactDOM from 'react-dom';
import "./css/app.css";
import "./css/font-awesome.css"

import "bootstrap/dist/css/bootstrap.min.css"
import "./css/demo.css"
import "./css/paper-dashboard.css"
import "./css/themify-icons.css"
import "./css/animate.min.css"

import {Switch, Router, Route} from 'react-router-dom'
import Login from "./components/Login";
import Manager from "./components/Manager"
import createBrowserHistory from 'history/createBrowserHistory'
const newHistory = createBrowserHistory();

ReactDOM.render(
    <Router history={newHistory}>
        <Switch>
            <Route exact path="/" component={Login}/>
            <Route path="/manager/:id" component={Manager}/>
        </Switch>
    </Router>,
    document.getElementById('app')
);
