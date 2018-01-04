import React from 'react';
import 'bootstrap/dist/js/bootstrap.min'
import ReactDOM from 'react-dom';

export default class Manager extends React.Component {

    constructor() {
        super();
        this.state = {
            activityIndex: 0,
        };
    }

    componentDidMount() {
    }

    render() {
        const {activityIndex} = this.state;
        return (
            <div>
                <div className="wrapper">
                    <div className="sidebar" data-background-color="white" data-active-color="danger">

                        <div className="sidebar-wrapper" id="accordion">
                            <div className="sidebar-wrapper">
                                <div className="logo">
                                    <a className="simple-text">
                                        FOC
                                    </a>
                                </div>
                                <ul className="nav">
                                    <li className={activityIndex === 0? "active":""} onClick={e=>this.setState({activityIndex:0})}>
                                        <a href="#search">
                                            <i className="ti-panel"/>
                                            <p>查询</p>
                                        </a>
                                    </li>
                                    <li className={activityIndex === 1 ||activityIndex===2? "active":""} >
                                        <a data-toggle="collapse" data-parent="#accordion" href="#userManager">
                                            <i className="ti-view-list-alt"/>
                                            <p>用户管理</p>
                                        </a>
                                        <div className="collapse" id="userManager">
                                            <ul className="list">
                                                <li className={activityIndex === 1? "active":""} onClick={e=>this.setState({activityIndex:1})}><a>创建用户</a></li>
                                                <li className={activityIndex === 2? "active":""} onClick={e=>this.setState({activityIndex:2})}><a>查询用户</a></li>
                                            </ul>
                                        </div>

                                    </li>
                                    <li className={activityIndex === 3 || activityIndex===4 ? "active":""} >
                                        <a data-toggle="collapse" data-parent="#accordion" href="#infoManager">
                                            <i className="ti-text"/>
                                            <p>录入信息管理</p>
                                        </a>
                                        <div className="collapse" id="infoManager">
                                            <ul className="list">
                                                <li className={activityIndex === 3? "active":""} onClick={e=>this.setState({activityIndex:3})}><a>录入信息</a></li>
                                                <li className={activityIndex === 4? "active":""} onClick={e=>this.setState({activityIndex:4})}><a>查询录入</a></li>
                                            </ul>
                                        </div>
                                    </li>
                                    <li className={activityIndex === 5? "active":""} onClick={e=>this.setState({activityIndex:5})}>
                                        <a>
                                            <i className="ti-user"/>
                                            <p>修改个人信息</p>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div className="main-panel">
                        <nav className="navbar navbar-default">
                            <div className="container-fluid">
                                <div className="navbar-header">
                                    <button type="button" className="navbar-toggle">
                                        <span className="sr-only">Toggle navigation</span>
                                        <span className="icon-bar bar1"/>
                                        <span className="icon-bar bar2"/>
                                        <span className="icon-bar bar3"/>
                                    </button>
                                    <a className="navbar-brand" href="#">Dashboard</a>
                                </div>
                                <div className="collapse navbar-collapse">
                                    <ul className="nav navbar-nav navbar-right">
                                        <li>
                                            <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                                                <i className="ti-panel"/>
                                                <p>Stats</p>
                                            </a>
                                        </li>
                                        <li className="dropdown">
                                            <a href="#" className="dropdown-toggle" data-toggle="dropdown">
                                                <i className="ti-bell"/>
                                                <p className="notification">5</p>
                                                <p>Notifications</p>
                                                <b className="caret"/>
                                            </a>
                                            <ul className="dropdown-menu">
                                                <li><a href="#">Notification 1</a></li>
                                                <li><a href="#">Notification 2</a></li>
                                                <li><a href="#">Notification 3</a></li>
                                                <li><a href="#">Notification 4</a></li>
                                                <li><a href="#">Another notification</a></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <i className="ti-settings"/>
                                                <p>Settings</p>
                                            </a>
                                        </li>
                                    </ul>

                                </div>
                            </div>
                        </nav>

                        <div className="content">
                            <div className="container-fluid">
                                <div className="row">
                                    <div className="col-lg-3 col-sm-6">
                                        <div className="card">
                                            <div className="content">
                                                <div className="row">
                                                    <div className="col-xs-5">
                                                        <div className="icon-big icon-warning text-center">
                                                            <i className="ti-server"/>
                                                        </div>
                                                    </div>
                                                    <div className="col-xs-7">
                                                        <div className="numbers">
                                                            <p>Capacity</p>
                                                            105GB
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="footer">
                                                    <hr/>
                                                    <div className="stats">
                                                        <i className="ti-reload"/> Updated now
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-lg-3 col-sm-6">
                                        <div className="card">
                                            <div className="content">
                                                <div className="row">
                                                    <div className="col-xs-5">
                                                        <div className="icon-big icon-success text-center">
                                                            <i className="ti-wallet"/>
                                                        </div>
                                                    </div>
                                                    <div className="col-xs-7">
                                                        <div className="numbers">
                                                            <p>Revenue</p>
                                                            $1,345
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="footer">
                                                    <hr/>
                                                    <div className="stats">
                                                        <i className="ti-calendar"/> Last day
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-lg-3 col-sm-6">
                                        <div className="card">
                                            <div className="content">
                                                <div className="row">
                                                    <div className="col-xs-5">
                                                        <div className="icon-big icon-danger text-center">
                                                            <i className="ti-pulse"/>
                                                        </div>
                                                    </div>
                                                    <div className="col-xs-7">
                                                        <div className="numbers">
                                                            <p>Errors</p>
                                                            23
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="footer">
                                                    <hr/>
                                                    <div className="stats">
                                                        <i className="ti-timer"/> In the last hour
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-lg-3 col-sm-6">
                                        <div className="card">
                                            <div className="content">
                                                <div className="row">
                                                    <div className="col-xs-5">
                                                        <div className="icon-big icon-info text-center">
                                                            <i className="ti-twitter-alt"/>
                                                        </div>
                                                    </div>
                                                    <div className="col-xs-7">
                                                        <div className="numbers">
                                                            <p>Followers</p>
                                                            +45
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="footer">
                                                    <hr/>
                                                    <div className="stats">
                                                        <i className="ti-reload"/> Updated now
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="row">

                                    <div className="col-md-12">

                                        <div className="card">
                                            <div className="header">
                                                <h4 className="title">Users Behavior</h4>
                                                <p className="category">24 Hours performance</p>
                                            </div>
                                            <div className="content">
                                                <div id="chartHours" className="ct-chart"/>
                                                <div className="footer">
                                                    <div className="chart-legend">
                                                        <i className="fa fa-circle text-info"/> Open
                                                        <i className="fa fa-circle text-danger"/> Click
                                                        <i className="fa fa-circle text-warning"/> Click Second Time
                                                    </div>
                                                    <hr/>
                                                    <div className="stats">
                                                        <i className="ti-reload"/> Updated 3 minutes ago
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-6">
                                        <div className="card">
                                            <div className="header">
                                                <h4 className="title">Email Statistics</h4>
                                                <p className="category">Last Campaign Performance</p>
                                            </div>
                                            <div className="content">
                                                <div id="chartPreferences" className="ct-chart ct-perfect-fourth"/>

                                                <div className="footer">
                                                    <div className="chart-legend">
                                                        <i className="fa fa-circle text-info"/> Open
                                                        <i className="fa fa-circle text-danger"/> Bounce
                                                        <i className="fa fa-circle text-warning"/> Unsubscribe
                                                    </div>
                                                    <hr/>
                                                    <div className="stats">
                                                        <i className="ti-timer"/> Campaign sent 2 days ago
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="card ">
                                            <div className="header">
                                                <h4 className="title">2015 Sales</h4>
                                                <p className="category">All products including Taxes</p>
                                            </div>
                                            <div className="content">
                                                <div id="chartActivity" className="ct-chart"/>

                                                <div className="footer">
                                                    <div className="chart-legend">
                                                        <i className="fa fa-circle text-info"/> Tesla Model S
                                                        <i className="fa fa-circle text-warning"/> BMW 5 Series
                                                    </div>
                                                    <hr/>
                                                    <div className="stats">
                                                        <i className="ti-check"/> Data information certified
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <footer className="footer">
                            <div className="container-fluid">
                                <nav className="pull-left">
                                    <ul>

                                        <li>
                                            <a href="http://www.creative-tim.com">
                                                Creative Tim
                                            </a>
                                        </li>
                                        <li>
                                            <a href="http://blog.creative-tim.com">
                                                Blog
                                            </a>
                                        </li>
                                        <li>
                                            <a href="http://www.creative-tim.com/license">
                                                Licenses
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                                <div className="copyright pull-right">
                                    &copy;
                                    <script>document.write(new Date().getFullYear())</script>
                                    , made with <i className="fa fa-heart heart"/> by <a
                                    href="http://www.creative-tim.com">Creative Tim</a>
                                </div>
                            </div>
                        </footer>

                    </div>
                </div>
            </div>
        )
    }
}