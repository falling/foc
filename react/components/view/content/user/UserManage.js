import React from 'react';

export default class UserManage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user_name: '',
            name: '',
            power: '',
            reg_date: ''
        };
        this.search = this.search.bind(this);
        this.update = this.update.bind(this);
        this.delete = this.delete.bind(this);
    }

    search() {

    }

    update() {

    }

    delete() {

    }

    componentDidMount() {
        //get user info
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {display} = this.props;
        const {user_name, name, power, reg_date} = this.state;
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">更改权限</h4>
                        </div>
                        <div className="content">

                            <div className="row">
                                <div className="col-md-12">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-search"/>                                </span>
                                        <input
                                            type="text"
                                            value={user_name}
                                            onChange={e => {
                                                this.setState({user_name: e.target.value})
                                            }}
                                            onKeyDown={e => {
                                                e.keyCode === 13 && this.search()
                                            }}
                                            className="form-control"
                                            placeholder="请输入用户名"
                                        />
                                        <span className="input-group-btn"><button className="btn btn-default" type="button">搜索</button></span>
                                    </div>
                                </div>
                            </div>

                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <label>用户名</label>
                                        <input type="text"
                                               className="form-control border-input"
                                               onChange={e => {
                                                   this.setState({username: e.target.value})
                                               }}
                                               disabled
                                               placeholder="登录名" value="falling"
                                        />
                                    </div>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <label>姓名</label>
                                        <input type="text"
                                               onChange={e => {
                                                   this.setState({name: e.target.value})
                                               }}
                                               className="form-control border-input"
                                               placeholder=""
                                               value={name}
                                        />
                                    </div>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <label>权限</label>
                                        <input type="text"
                                               value={power}
                                               disabled
                                               className="form-control border-input"
                                        />
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="form-group">
                                        <label>注册日期</label>
                                        <input type="text"
                                               value={reg_date}
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
                                >更新用户权限
                                </button>
                                <button type="button" className="btn btn-danger btn-fill btn-wd"
                                        onClick={e => {
                                            confirm('确定要删除该用户吗?') && this.delete()
                                        }}
                                >删除用户
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        )
    }
}