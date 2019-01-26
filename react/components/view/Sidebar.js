import React from 'react';
import {Link} from 'react-router-dom'
import {Menu, Icon} from 'antd';

const SubMenu = Menu.SubMenu;

export default class Sidebar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            activityIndex: props.index,
            collapsed: false,
        }
        this.toggleCollapsed = this.toggleCollapsed.bind(this);
    }

    componentDidMount() {
    }

    componentWillReceiveProps(nextProps) {
        this.setState({
            activityIndex: nextProps.index
        })
    }

    toggleCollapsed() {
        this.setState({
            collapsed: !this.state.collapsed,
        });
    }

    render() {
        const {user, type, side} = this.props;
        return (
            <div
                style={{
                width: 256,
                // height:"100%",
                "float": "left",
                // "background": side?"linear-gradient(to bottom, #2cbbe6 0%, rgba(19, 96, 119, 0.7) 100%)":""
            }}>
                <Menu
                    // style={{"background-color":"rgba(0, 0, 0, 0)"}}
                    mode="inline"
                    theme="blue"
                    className={side && "collapse navbar-collapse"}
                    inlineCollapsed={this.state.collapsed}
                >
                    <div style={{"padding": "18px 0px", "margin": "10px 20px", "text-align": "center"}}>
                        <a className="simple-text overDefault">
                            侨联信息系统
                        </a>
                    </div>

                    <SubMenu key="sub1" title={<span><Icon type="search"/><span>信息查询</span></span>}>
                        <Menu.Item key="sub1_5"><Link to="/manager/0/hq">查询华侨</Link></Menu.Item>
                        <Menu.Item key="sub1_6"><Link to="/manager/0/lx">查询留学生</Link></Menu.Item>
                        <Menu.Item key="sub1_7"><Link to="/manager/0/qj_hq">查询侨眷</Link></Menu.Item>
                        <Menu.Item key="sub1_8"><Link to="/manager/0/qj_lx">查询留学生家属</Link></Menu.Item>
                    </SubMenu>
                    {(user && user.power !== 'user') &&
                    <SubMenu key="sub2" title={<span><Icon type="form"/><span>信息录入</span></span>}>
                        <Menu.Item key="sub2_5"><Link to="/manager/1/hq">录入华侨</Link></Menu.Item>
                        <Menu.Item key="sub2_6"><Link to="/manager/1/lx">录入留学生</Link></Menu.Item>
                        <Menu.Item key="sub2_7"><Link to="/manager/1/qj_hq">录入侨眷</Link></Menu.Item>
                        <Menu.Item key="sub2_8"><Link to="/manager/1/qj_lx">录入留学生家属</Link></Menu.Item>
                    </SubMenu>}
                    {(user && user.power !== 'user') &&
                    <SubMenu key="sub3" title={<span><Icon type="user"/><span>用户管理</span></span>}>
                        <Menu.Item key="sub3_5"><Link to="/manager/4">创建用户</Link></Menu.Item>
                        {user.power === 'root' && <Menu.Item key="sub3_6"><Link to="/manager/5">管理用户</Link></Menu.Item>}
                    </SubMenu>}
                    < SubMenu key="sub4" title={<span><Icon type="pie-chart"/><span>统计</span></span>}>
                        <Menu.Item key="sub4_5"><Link to="/manager/7">数据统计</Link></Menu.Item>
                    </SubMenu>

                    <SubMenu key="sub5" title={<span><Icon type="profile"/><span>用户中心</span></span>}>
                        <Menu.Item key="sub5_5"><Link to="/manager/3">个人信息</Link></Menu.Item>
                        <Menu.Item key="sub5_6"><Link to="/manager/6">修改密码</Link></Menu.Item>
                    </SubMenu>
                </Menu>
            </div>

            // <div className="sidebar" data-active-color="white">
            //
            //     <div className="sidebar-wrapper" id="accordion">
            //         <div className="logo">
            //             <a className="simple-text overDefault">
            //                 FOC
            //             </a>
            //         </div>
            //         <ul className="nav">
            //             <li>
            //                 <a data-toggle="collapse" data-parent="#accordion" href="#search">
            //                     <i className="ti-search"/>
            //                     <p>信息查询</p>
            //                 </a>
            //                 <div className="collapse" id="search">
            //                     <ul className="list">
            //                         <li className={activityIndex === 0 && type === 'hq' ? "active" : ""}>
            //                             <Link to="/manager/0/hq">查询华侨</Link>
            //                         </li>
            //                         <li className={activityIndex === 0 && type === 'lx' ? "active" : ""}>
            //                             <Link to="/manager/0/lx">查询留学生</Link>
            //                         </li>
            //                         <li className={activityIndex === 0 && type === 'qj_hq' ? "active" : ""}>
            //                             <Link to="/manager/0/qj_hq">查询侨眷</Link>
            //                         </li>
            //                         <li className={activityIndex === 0 && type === 'qj_lx' ? "active" : ""}>
            //                             <Link to="/manager/0/qj_lx">查询留学生家属</Link>
            //                         </li>
            //                     </ul>
            //                 </div>
            //             </li>
            //             <li>
            //                 <a data-toggle="collapse" data-parent="#accordion" href="#addInfo">
            //                     <i className="ti-panel"/>
            //                     <p>信息录入</p>
            //                 </a>
            //                 <div className="collapse" id="addInfo">
            //                     <ul className="list">
            //                         <li className={activityIndex === 1 && type === 'hq' ? "active" : ""}>
            //                             <Link to="/manager/1/hq">录入华侨</Link>
            //                         </li>
            //                         <li className={activityIndex === 1 && type === 'lx' ? "active" : ""}>
            //                             <Link to="/manager/1/lx">录入留学生</Link>
            //                         </li>
            //                         <li className={activityIndex === 1 && type === 'qj_hq' ? "active" : ""}>
            //                             <Link to="/manager/1/qj_hq">录入侨眷</Link>
            //                         </li>
            //                         <li className={activityIndex === 1 && type === 'qj_lx' ? "active" : ""}>
            //                             <Link to="/manager/1/qj_lx">录入留学生家属</Link>
            //                         </li>
            //                     </ul>
            //                 </div>
            //             </li>
            //             {(user && user.power !== 'user') &&
            //             <li>
            //                 <a data-toggle="collapse" data-parent="#accordion" href="#userManager">
            //                     <i className="ti-user"/>
            //                     <p>用户管理</p>
            //                 </a>
            //                 <div className="collapse" id="userManager">
            //                     <ul className="list">
            //                         <li className={activityIndex === 4 ? "active" : ""}>
            //                             <Link to="/manager/4">创建用户</Link>
            //                         </li>
            //                         {user.power === 'root' &&
            //                         <li className={activityIndex === 5 ? "active" : ""}>
            //                             <Link to="/manager/5">管理用户</Link>
            //                         </li>}
            //                     </ul>
            //                 </div>
            //             </li>}
            //             <li>
            //                 <Link to="/manager/7">
            //                     <i className="ti-pie-chart"/>
            //                     <p>数据统计</p>
            //                 </Link>
            //             </li>
            //             <li>
            //                 <a data-toggle="collapse" data-parent="#accordion" href="#userCenter">
            //                     <i className="ti-id-badge"/>
            //                     <p>用户中心</p>
            //                 </a>
            //                 <div className="collapse" id="userCenter">
            //                     <ul className="list">
            //                         <li className={activityIndex === 3 ? "active" : ""}>
            //                             <Link to="/manager/3">个人信息</Link>
            //                         </li>
            //                         <li className={activityIndex === 6 ? "active" : ""}>
            //                             <Link to="/manager/6">修改密码</Link>
            //                         </li>
            //
            //                     </ul>
            //                 </div>
            //             </li>
            //         </ul>
            //     </div>
            // </div>
        )
    }
}