import React from 'react';
import {Title} from "../config/Title";
import 'whatwg-fetch';
import {Link} from 'react-router-dom'


export default class Header extends React.Component {
    constructor() {
        super();
        this.state = {};
    }

    componentDidMount() {

    }

    render() {
        const {user} = this.props;
        return (
            <nav className="navbar navbar-default">
                <div className="container-fluid">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle">
                            <span className="sr-only">Toggle navigation</span>
                            <span className="icon-bar bar1"/>
                            <span className="icon-bar bar2"/>
                            <span className="icon-bar bar3"/>
                        </button>
                        <div className="navbar-brand overDefault">{Title[this.props.title]}</div>
                    </div>
                    <div className="collapse navbar-collapse">
                        {(user !== null && user.name !== null) ? <ul className="nav navbar-nav navbar-right">
                                <li>
                                    <a href="#">
                                        <i className="ti-user paddingRight"/>
                                        <p>{user.name}</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i className="ti-settings paddingRight"/>
                                        <p>注销</p>
                                    </a>
                                </li>
                            </ul>
                            :
                            <ul className="nav navbar-nav navbar-right">
                                <li >
                                    <Link to="/">
                                        <i className="ti-settings paddingRight"/>
                                        <p>登陆</p>
                                    </Link>
                                </li>
                            </ul>
                        }

                    </div>
                </div>
            </nav>
        )
    }
}