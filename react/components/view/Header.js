import React from 'react';
import {Title} from "../config/Title";


export default class Header extends React.Component {
    render() {
        return(
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
                        <ul className="nav navbar-nav navbar-right">
                            <li>
                                <a href="#">
                                    <i className="ti-user"/>
                                    <p>falling</p>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i className="ti-settings"/>
                                    <p>注销</p>
                                </a>
                            </li>
                        </ul>

                    </div>
                </div>
            </nav>
        )
    }
}