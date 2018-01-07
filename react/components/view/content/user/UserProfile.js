import React from 'react';

export default class UserProfile extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user_name:'',
            name:'',
            power:'',
            reg_date:''
        };
        this.update = this.update.bind(this);
    }

    update(){

    }

    componentDidMount() {
        //get user info
        //loading
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {display} = this.props;
        const {user_name,name,power,reg_date} = this.state;
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">修改个人信息</h4>
                        </div>
                        <div className="content">
                            <form>
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