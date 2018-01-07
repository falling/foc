import React from 'react';
import {Link} from 'react-router-dom'
import {Title} from '../config/Title'
export default class Sidebar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            activityIndex: props.index
        }
    }
    componentDidMount(){
    }
    componentWillReceiveProps(nextProps){
        this.setState({
            activityIndex:nextProps.index
        })
    }
    render() {
        let activityIndex = +this.props.index;
        return (
            <div className="sidebar" data-background-color="white" data-active-color="danger">

                <div className="sidebar-wrapper" id="accordion">
                    <div className="sidebar-wrapper">
                        <div className="logo">
                            <a className="simple-text overDefault">
                                FOC
                            </a>
                        </div>
                        <ul className="nav">
                            <li className={activityIndex === 0 ? "active" : ""}>
                                <Link to="/manager/0">
                                    <i className="ti-panel"/>
                                    <p>查询</p>
                                </Link>
                            </li>
                            <li className={activityIndex === 1 || activityIndex === 2 ? "active" : ""}>
                                <a data-toggle="collapse" data-parent="#accordion" href="#userManager">
                                    <i className="ti-view-list-alt"/>
                                    <p>用户管理</p>
                                </a>
                                <div className="collapse" id="userManager">
                                    <ul className="list">
                                        <li className={activityIndex === 1 ? "active" : ""}>
                                            <Link to="/manager/1">创建用户</Link>
                                        </li>
                                        <li className={activityIndex === 2 ? "active" : ""}>
                                            <Link to="/manager/2">管理用户</Link>
                                        </li>
                                    </ul>
                                </div>

                            </li>
                            <li className={activityIndex === 3 || activityIndex === 4 ? "active" : ""}>
                                <a data-toggle="collapse" data-parent="#accordion" href="#infoManager">
                                    <i className="ti-text"/>
                                    <p>录入信息管理</p>
                                </a>
                                <div className="collapse" id="infoManager">
                                    <ul className="list">
                                        <li className={activityIndex === 3 ? "active" : ""}>
                                            <Link to="/manager/3">录入信息</Link>
                                        </li>
                                        <li className={activityIndex === 4 ? "active" : ""}>
                                            <Link to="/manager/4">查询录入</Link>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li className={activityIndex === 5 ? "active" : ""}>
                                <Link to="/manager/5">
                                    <i className="ti-user"/>
                                    <p>修改个人信息</p>
                                </Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        )
    }
}