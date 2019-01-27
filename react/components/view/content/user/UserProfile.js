import React from 'react';
import 'whatwg-fetch';
import {message} from 'antd';
export default class UserProfile extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user:{
                reg_date:"",
                name : "",
                power : "",
                username :""
            },
        };
        this.update = this.update.bind(this);
    }

    update(){
        fetch('/api/updateUser',{
            method: 'post',
            credentials: 'include',
            headers: {'Content-Type': 'application/json'},
            body:JSON.stringify(this.state.user)
        }).then(response=>response.json())
            .then(json=>{
                if(json.status>0){
                    message.success(json.info);
                }else{
                    message.warning(json.info);
                }
            })
    }

    componentDidMount() {
        fetch('/api/userInfo', {
            method: 'post',
            credentials: 'include',
        }).then(response => response.json())
            .then(user => {
                if (user.username!==null){
                    this.setState({user: user})
                }
            })
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {display} = this.props;
        const {user} = this.state;
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">个人信息</h4>
                        </div>
                        <div className="content">
                            <form>
                                <div className="row">
                                    <div className="col-md-6">
                                        <div className="form-group">
                                            <label>用户名</label>
                                            <input type="text"
                                                   className="form-control border-input"
                                                   disabled
                                                   value={user.username}
                                            />
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="form-group">
                                            <label>姓名</label>
                                            <input type="text"
                                                   onChange={e => {
                                                       user.name = e.target.value;
                                                       this.setState({user: user})
                                                   }}
                                                   className="form-control border-input"
                                                   placeholder=""
                                                   value={user.name}
                                            />
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-6">
                                        <div className="form-group">
                                            <label>权限</label>
                                            <input type="text"
                                                   value={user.power}
                                                   disabled
                                                   className="form-control border-input"
                                            />
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="form-group">
                                            <label>所属侨联</label>
                                            <input type="text"
                                                   value={user.manager_area}
                                                   disabled
                                                   className="form-control border-input"
                                            />
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-6">
                                        <div className="form-group">
                                            <label>注册日期</label>
                                            <input type="text"
                                                   value={user.reg_date}
                                                   disabled
                                                   className="form-control border-input"
                                            />
                                        </div>
                                    </div>
                                </div>

                                <div className="text-center">
                                    <button type="button" className="btn btn-info btn-fill btn-wd"
                                            onClick={e => {
                                                this.update()
                                            }}
                                    >更新信息
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}