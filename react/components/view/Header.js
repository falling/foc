import React from 'react';
import {Title} from "../config/Title";
import 'whatwg-fetch';
import {message, Menu, Dropdown, Icon, Upload} from 'antd';
import {Link} from 'react-router-dom'

export default class Header extends React.Component {
    constructor() {
        super();
        this.state = {};
        this.sighOff = this.sighOff.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.menu = (
            <Menu>
                <Menu.Item key="0">
                    <Link to="/manager/6">修改密码</Link>
                </Menu.Item>
            </Menu>
        );
    }

    sighOff() {
        fetch("/sighOff", {
            method: 'post',
            credentials: 'include',
        }).then(response => response.json())
            .then(json => {
                if (json.status >= 0) {
                    message.success(json.info, 5);
                    window.location.href = "/";
                } else {
                    message.error(json.info, 5);
                }
            })
    }

    handleChange({fileList}) {
        let file = fileList[0];
        if (file) {
            if (file.status === 'done' && file.response.status > 0) {
                message.success("上传成功");
                if (this.props.onChange) {
                    this.props.onChange(file.response.info);
                }
            } else if (file.status === 'error') {
                message.error("上传失败");
            } else if (file.response && file.response.status < 0) {
                message.error(file.response.info)
            }
        }
        this.setState({fileList})
    }

    componentDidMount() {

    }

    render() {
        const {user} = this.props;
        const {fileList} = this.state;
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
                        {(user !== null && user.name !== null) ?
                            <ul className="nav navbar-nav navbar-right">
                                <li>
                                    <a>
                                        <Upload
                                            action="/excelUpload"
                                            accept="application/vnd.ms-excel"
                                            onChange={this.handleChange}
                                            showUploadList={false}
                                            fileList={fileList}
                                        >
                                            <Icon type="upload"/> 导入数据
                                        </Upload>
                                    </a>
                                </li>
                                <li>
                                    <Dropdown overlay={this.menu}>
                                        <a>
                                            <i className="ti-user paddingRight"/>
                                            <p>{user.name}<Icon type="down"/></p>
                                        </a>
                                    </Dropdown>
                                </li>
                                <li>
                                    <a onClick={e => this.sighOff()}>
                                        <i className="ti-settings paddingRight"/>
                                        <p>注销</p>
                                    </a>
                                </li>
                            </ul>
                            :
                            <ul className="nav navbar-nav navbar-right">
                                <li>
                                    <Link to="/">
                                        <i className="ti-settings paddingRight"/>
                                        <p>登录</p>
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