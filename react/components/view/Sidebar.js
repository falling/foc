import React from 'react';
import {Link} from 'react-router-dom'

export default class Sidebar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            activityIndex: props.index
        }
    }

    componentDidMount() {
    }

    componentWillReceiveProps(nextProps) {
        this.setState({
            activityIndex: nextProps.index
        })
    }

    render() {
        const {user} = this.props;
        let activityIndex = +this.props.index;
        let type = this.props.type;
        return (
            <div className="sidebar" data-active-color="white">

                <div className="sidebar-wrapper" id="accordion">
                    <div className="logo">
                        <a className="simple-text overDefault">
                            FOC
                        </a>
                    </div>
                    <ul className="nav">
                        <li>
                            <a data-toggle="collapse" data-parent="#accordion" href="#search">
                                <i className="ti-search"/>
                                <p>信息查询</p>
                            </a>
                            <div className="collapse in" id="search">
                                <ul className="list">
                                    <li className={activityIndex === 0 && type === 'hq' ? "active" : ""}>
                                        <Link to="/manager/0/hq">查询华侨</Link>
                                    </li>
                                    <li className={activityIndex === 0 && type === 'lx' ? "active" : ""}>
                                        <Link to="/manager/0/lx">查询留学生</Link>
                                    </li>
                                    <li className={activityIndex === 0 && type === 'qj_hq' ? "active" : ""}>
                                        <Link to="/manager/0/qj_hq">查询侨眷</Link>
                                    </li>
                                    <li className={activityIndex === 0 && type === 'qj_lx' ? "active" : ""}>
                                        <Link to="/manager/0/qj_lx">查询留学生家属</Link>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <a data-toggle="collapse" data-parent="#accordion" href="#addInfo">
                                <i className="ti-panel"/>
                                <p>信息录入</p>
                            </a>
                            <div className="collapse in" id="addInfo">
                                <ul className="list">
                                    <li className={activityIndex === 1 && type === 'hq' ? "active" : ""}>
                                        <Link to="/manager/1/hq">录入华侨</Link>
                                    </li>
                                    <li className={activityIndex === 1 && type === 'lx' ? "active" : ""}>
                                        <Link to="/manager/1/lx">录入留学生</Link>
                                    </li>
                                    <li className={activityIndex === 1 && type === 'qj_hq' ? "active" : ""}>
                                        <Link to="/manager/1/qj_hq">录入侨眷</Link>
                                    </li>
                                    <li className={activityIndex === 1 && type === 'qj_lx' ? "active" : ""}>
                                        <Link to="/manager/1/qj_lx">录入留学生家属</Link>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        {(user && user.power !== 'user') &&
                        <li>
                            <a data-toggle="collapse" data-parent="#accordion" href="#userManager">
                                <i className="ti-user"/>
                                <p>用户管理</p>
                            </a>
                            <div className="collapse in" id="userManager">
                                <ul className="list">
                                    <li className={activityIndex === 4 ? "active" : ""}>
                                        <Link to="/manager/4">创建用户</Link>
                                    </li>
                                    {user.power === 'root' &&
                                    <li className={activityIndex === 5 ? "active" : ""}>
                                        <Link to="/manager/5">管理用户</Link>
                                    </li>}
                                    <li className={activityIndex === 3 ? "active" : ""}>
                                        <Link to="/manager/3">修改个人信息</Link>
                                    </li>
                                </ul>
                            </div>
                        </li>}
                        <li>
                            <Link to="/manager/6">
                                <i className="ti-settings"/>
                                <p>修改密码</p>
                            </Link>
                        </li>
                    </ul>
                </div>
            </div>
        )
    }
}