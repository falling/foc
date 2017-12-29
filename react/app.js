import React from 'react';
import ReactDOM from 'react-dom';
import "./css/toolkit-light.min.css";
import "./css/app.css";
import "./css/font-awesome.css"
import {Switch, Router, Route} from 'react-router-dom'
import Login from "./components/Login";
import Manager from "./components/Manager"
import createBrowserHistory from 'history/createBrowserHistory'
const newHistory = createBrowserHistory();

ReactDOM.render(
    <Router history={newHistory}>
        <Switch>
            <Route exact path="/" component={Login}/>
            <Route path="/manager" component={Manager}/>
            <Route path="/manager2" component={Login}/>
        </Switch>
    </Router>,
    document.getElementById('app')
);
