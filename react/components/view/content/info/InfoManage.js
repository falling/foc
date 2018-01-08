import React from 'react';
import FormContent from "./formContent/FormContent";

export default class InfoManage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            type:'hq',
            user_name:''
        };
    }

    componentDidMount() {
        //get user info
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {display} = this.props;
        const {user_name,type} =this.state;
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">录入管理</h4>
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
                                        <span className="input-group-btn"><button className="btn btn-default"
                                                                                  type="button">搜索</button></span>
                                    </div>
                                </div>
                            </div>

                            <hr/>
                            <FormContent
                                type={type}
                            />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}