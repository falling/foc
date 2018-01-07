import React from 'react';

export default class UserCreate extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username:'',
            name:'',
            power:'user'
        };
        this.create = this.create.bind(this);
    }

    create(){
        const {username,name,power} = this.state;
        console.log("create:",username,name,power);
    }

    componentDidMount() {
        //get user info
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {display} = this.props;
        const {username,name} = this.state;
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">创建用户</h4>
                        </div>
                        <div className="content">
                            <form>
                                <div className="row">
                                    <div className="col-md-12">
                                        <div className="form-group">
                                            <label>用户名</label>
                                            <input type="text"
                                                   className="form-control border-input"
                                                   onChange={e=>{this.setState({username:e.target.value})}}
                                                   placeholder="登录名" value={username}/>
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-12">
                                        <div className="form-group">
                                            <label>姓名</label>
                                            <input type="text"
                                                   onChange={e=>{this.setState({name:e.target.value})}}
                                                   className="form-control border-input"
                                                   placeholder="" value={name}/>
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-12">
                                        <div className="form-group">
                                            <label>权限</label>
                                            <select
                                                onChange={e=>{this.setState({power:e.target.value})}}
                                                className="form-control border-input">
                                                <option value="user">普通用户</option>
                                                <option value="admin">管理员</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-12">
                                        <div className="form-group">
                                            <label>初始密码为:123456</label>
                                        </div>
                                    </div>
                                </div>

                                <div className="text-center">
                                    <button type="button" className="btn btn-info btn-fill btn-wd"
                                            onClick={e=>{this.create()}}
                                    >创建用户
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