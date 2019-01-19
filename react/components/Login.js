import React from 'react';
import 'whatwg-fetch';
import {message} from 'antd';


export default class Login extends React.Component {

    constructor() {
        super();
        this.state = {
            username: '',
            password: '',
            verifyCode: '',
            imagSrc: '',
            isLoading: false
        };
        this.login = this.login.bind(this);
        this.loadVerifyCode = this.loadVerifyCode.bind(this);
    }

    componentDidMount() {
        this.loadVerifyCode();
    }

    loadVerifyCode() {
        fetch('/verifyCode', {
            method: 'post',
            credentials: 'include',
        }).then(response => response.blob())
            .then(blob => {
                this.setState({imagSrc: window.URL.createObjectURL(blob)});
            });
    }

    login() {
        this.setState({isLoading: true});
        const {username, password, verifyCode} = this.state;
        if (username === '' || password === '' || verifyCode === '') {
            this.setState({isLoading: false});
            return;
        }
        let formData = new FormData();
        formData.append("username", username);
        formData.append("password", password);
        formData.append("verifyCode", verifyCode);
        fetch('/userLogin', {
            method: 'post',
            credentials: 'include',
            body: formData
        }).then(response => response.json()
        ).then(user => {
            this.setState({isLoading: false})
            if (user.status >= 0) {
                this.props.history.push("/manager/0");
            } else {
                message.error(user.info, 5);
                this.loadVerifyCode();
            }
        })
    }

    render() {
        const {username, password, isLoading} = this.state;
        return (
            <div className="with-iconav">
                <div className="container">
                    <div className="login">
                        <div className="login-container">
                            <img src={require('../image/logo.png')}/>
                            <img src={require('../image/name.png')}/>
                            <div className="label">v.1.0.1</div>
                            <div className="login-form">
                                <div id="login_modal">
                                    <div className="input-with-icon wfull">
                                        <input type="text"
                                               onKeyDown={e => {
                                                   e.keyCode === 13 && this.login()
                                               }}
                                               onChange={e => this.setState({username: e.target.value})}
                                               className="form-control"
                                               placeholder="账号"/>
                                        <i className="fa fa-user"/>
                                    </div>
                                    <div className="input-with-icon wfull login-password">
                                        <input type="password"
                                               onKeyDown={e => {
                                                   e.keyCode === 13 && this.login()
                                               }}
                                               onChange={e => this.setState({password: e.target.value})}
                                               className="form-control"
                                               placeholder="密码"/>
                                        <i className="fa fa-lock"/>
                                    </div>
                                    <div className="input-with-icon wfull login-password">
                                        <input type="text"
                                               onChange={e => this.setState({verifyCode: e.target.value})}
                                               onKeyDown={e => {
                                                   e.keyCode === 13 && this.login()
                                               }}
                                               style={{"width": "50%","float":"left"}}
                                               className="form-control"
                                               placeholder="验证码"/>
                                        <i className="fa fa-envelope"/>

                                        <img src={this.state.imagSrc}/>
                                        <a onClick={e => {
                                            this.loadVerifyCode()
                                        }}>看不清楚</a>
                                    </div>
                                    <button className="btn btn-lg btn-primary-outline-login wfull login-button"
                                            ref="loginButton"
                                            onClick={() => this.login()}
                                            disabled={password === '' || username === '' || isLoading}
                                    >
                                        <i className="fa fa-rocket"/>
                                        {isLoading ? '登录中...' : '登录'}
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}