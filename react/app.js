import React from 'react';
import ReactDOM from 'react-dom';
import zh_CN from 'antd/lib/locale-provider/zh_CN';
import { LocaleProvider } from 'antd';

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
    <LocaleProvider locale={zh_CN}>
        <Router history={newHistory}>
            <Switch>
                <Route exact path="/" component={Login}/>
                <Route exact path="/manager/:id/:type" component={Manager}/>
                <Route path="/manager/:id" component={Manager}/>
            </Switch>
        </Router>
    </LocaleProvider>,
    document.getElementById('app')
);
