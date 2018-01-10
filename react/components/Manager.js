import React from 'react';
import 'bootstrap/dist/js/bootstrap.min'
import Sidebar from './view/Sidebar'
import Header from './view/Header'
import InfoSearch from "./view/content/info/InfoSearch";
import UserManage from "./view/content/user/UserManage";
import UserCreate from "./view/content/user/UserCreate";
import InfoCreate from "./view/content/info/InfoCreate";
import InfoManage from "./view/content/info/InfoManage";
import UserProfile from "./view/content/user/UserProfile";
import 'whatwg-fetch';

export default class Manager extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user: null,
        };
    }

    componentDidMount() {
        //get user info
        fetch('/userInfo', {
            method: 'post',
            credentials: 'include',
        }).then(response => response.json())
            .then(user => {
                this.setState({user: user})
            })
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {user} = this.state;
        let urlId = +this.props.match.params.id;
        return (
            <div>
                <div className="wrapper">
                    <Sidebar
                        index={urlId}
                    />

                    <div className="main-panel">
                        <Header
                            title ={this.props.match.params.id}
                            user = {user}
                        />

                        <div className="content">
                            <InfoSearch display={urlId===0}/>
                            <UserCreate display={urlId===1}/>
                            <UserManage display={urlId===2}/>
                            <InfoCreate display={urlId===3}/>
                            <InfoManage display={urlId===4}/>
                            <UserProfile display={urlId===5}/>
                        </div>


                        <footer className="footer">
                            <div className="container-fluid">
                                <nav className="pull-left">
                                    <ul>

                                        <li>
                                            <a href="http://www.creative-tim.com">
                                                Creative Tim
                                            </a>
                                        </li>
                                        <li>
                                            <a href="http://blog.creative-tim.com">
                                                Blog
                                            </a>
                                        </li>
                                        <li>
                                            <a href="http://www.creative-tim.com/license">
                                                Licenses
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                                <div className="copyright pull-right">
                                    &copy;
                                    <script>document.write(new Date().getFullYear())</script>
                                    , made with <i className="fa fa-heart heart"/> by <a
                                    href="http://www.creative-tim.com">Creative Tim</a>
                                </div>
                            </div>
                        </footer>

                    </div>
                </div>
            </div>
        )
    }
}