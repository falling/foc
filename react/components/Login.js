import React from 'react';
import 'whatwg-fetch';
import NotificationSystem from 'react-notification-system';



export default class Login extends React.Component {

    constructor() {
        super();
        this.state = {
            username: '',
            password: ''
        };
        this.login =this.login.bind(this);
    }
    componentDidMount() {
        this._notificationSystem = this.refs.notificationSystem;
        localStorage.clear();
    }

    login(){
        let formData = new FormData();
        formData.append("username",this.state.username);
        formData.append("password",this.state.password);
        fetch('/userLogin',{
            method:'post',
            credentials: 'include',
            body:formData
        }).then(response=>response.json()
         ).then(user=>{
             if (user.status>= 0){
                 //route
                 localStorage.setItem("user",JSON.stringify(user));
                 this.props.history.push("/manager/0");
             }else{
                 //pop
                 this._notificationSystem.addNotification({
                     message: user.warning,
                     level: 'error',
                     position:'tc'
                 })
             }
        })
    }

    render() {
        const {username,password} = this.state;
        return (
            <div className="with-iconav">
                <div className="container">
                    <div className="login">
                        <div className="login-container">
                            <img src={require('../image/logo.png')}/>
                            <img src={require('../image/name.png')}/>
                            <div className="label">v.1.6.2</div>
                            <div className="login-form">
                                <div id="login_modal">
                                    <div className="input-with-icon wfull">
                                        <input type="text"
                                               onChange={e=>this.setState({username:e.target.value})}
                                               className="form-control"
                                               placeholder="账号"/>
                                        <i className="fa fa-user"/>
                                    </div>
                                    <div className="input-with-icon wfull login-password">
                                        <input type="password"
                                               onChange={e=>this.setState({password:e.target.value})}
                                               className="form-control"
                                               placeholder="密码"/>
                                        <i className="fa fa-lock"/>
                                    </div>
                                    <div className="login-forgot">
                                        <a className="pointer">忘记密码?</a>
                                    </div>
                                    <button className="btn btn-lg btn-pill btn-primary-outline wfull login-button"
                                            onClick={()=>this.login(username,password)}
                                            disabled = {password ==='' || username === ''}
                                    >
                                        <i className="fa fa-rocket"/>
                                        登录
                                    </button>
                                </div>
                            </div>
                            <NotificationSystem ref="notificationSystem" />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}