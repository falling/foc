import React from 'react';
import {Title} from "../config/Title";
import 'whatwg-fetch';
import {message, Menu, Dropdown, Icon, Upload, Drawer} from 'antd';
import {Link} from 'react-router-dom'
import Sidebar from "./Sidebar";

export default class Header extends React.Component {
    constructor() {
        super();
        this.state = {
            loading: false,
            visible: false,
        };
        this.sighOff = this.sighOff.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.onClose = this.onClose.bind(this);
        this.openDrawer = this.openDrawer.bind(this);
        this.menu = (
            <Menu>
                <Menu.Item key="0">
                    <Link to="/manager/6">修改密码</Link>
                </Menu.Item>
            </Menu>
        );
    }

    sighOff() {
        fetch("/api/sighOff", {
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

    openDrawer() {
        this.setState({
            visible: true,
        });
    }

    onClose() {
        this.setState({
            visible: false,
        });
    }

    handleChange({fileList}) {
        let file = fileList[0];
        if (file.response) {
            if (file.response.status > 0) {
                message.success(file.response.info);
            } else {
                message.error(file.response.info);
            }
            this.setState({loading: false, fileList: []});
            return;
        }
        if (file.status === 'uploading') {
            this.setState({loading: true});
        }
        this.setState({fileList})
    }

    componentDidMount() {

    }

    render() {
        const {user} = this.props;
        const {fileList, loading} = this.state;
        return (
            <nav className="navbar navbar-default">
                <div className="container-fluid">
                    <div className="navbar-header">
                        <button onClick={this.openDrawer} type="button" className="navbar-toggle">
                            <span className="sr-only">Toggle navigation</span>
                            <span className="icon-bar bar1"/>
                            <span className="icon-bar bar2"/>
                            <span className="icon-bar bar3"/>
                        </button>
                        <div className="navbar-brand overDefault">{Title[this.props.title]}</div>
                    </div>
                    <div className="">
                        {(user !== null && user.name !== null) ?
                            <ul className="nav navbar-nav navbar-right">
                                <li>
                                    <a>
                                        <Upload
                                            action="/api/excelUpload"
                                            accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                                            onChange={this.handleChange}
                                            beforeUpload={(file)=>{
                                                if (file.size > 47085524) {
                                                    this.setState({loading:false});
                                                    message.error("上传的文件不能大于45MB");
                                                    return false;
                                                }else{
                                                    return true;
                                                }
                                            }}
                                            showUploadList={false}
                                            fileList={fileList}
                                            disabled={loading}
                                        >
                                            {!loading ?
                                                <div className="header-item">
                                                    <i className="ti-upload paddingRight"/>
                                                    <p>导入数据</p>
                                                </div>
                                                : <i>上传中...</i>
                                            }
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

                <Drawer
                    title="侨联信息系统"
                    placement="right"
                    width={256}
                    closable={false}
                    onClose={this.onClose}
                    visible={this.state.visible}
                >
                    <Sidebar
                        user={user}
                        side={false}
                    />
                </Drawer>
            </nav>
        )
    }
}