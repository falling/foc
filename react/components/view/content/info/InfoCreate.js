import React from 'react';
import DateTime from 'react-datetime'
import 'react-datetime/css/react-datetime.css'
export default class InfoCreate extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            type: 'hq',
            name: '',
            pinyin: '',
            used_name: '',
            sex: '男',
            nationality: '',
            passport: '',
            passport_date: '',
        };

    }

    componentDidMount() {
        //get user info
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {display} = this.props;
        const {type, name, nationality, passport, passport_date} = this.state;

        return (
            <div style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">录入信息</h4>
                        </div>
                        <div className="content">

                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <label>类别</label>
                                        <select
                                            onChange={e => {
                                                this.setState({type: e.target.value})
                                            }}
                                            className="form-control border-input">
                                            <option value="hq">华侨</option>
                                            <option value="lx">留学</option>
                                            <option value="qj">侨眷</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div className="row">
                                <div className="col-md-3">
                                    <div className="form-group">
                                        <label>中文名*</label>
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
                                <div className="col-md-3">
                                    <div className="form-group">
                                        <label>拼音*</label>
                                        <input type="text"
                                               onChange={e => {
                                                   this.setState({name: e.target.value})
                                               }}
                                               className="form-control border-input"
                                               placeholder="" value={name}/>
                                    </div>
                                </div>
                                <div className="col-md-3">
                                    <div className="form-group">
                                        <label>曾用名</label>
                                        <input type="text"
                                               onChange={e => {
                                                   this.setState({name: e.target.value})
                                               }}
                                               className="form-control border-input"
                                               placeholder="" value={name}/>
                                    </div>
                                </div>
                                <div className="col-md-2">
                                    <div className="form-group">
                                        <label>民族*</label>
                                        <input type="text"
                                               onChange={e => {
                                                   this.setState({nationality: e.target.value})
                                               }}
                                               className="form-control border-input"
                                               placeholder="" value={nationality}/>
                                    </div>
                                </div>
                                <div className="col-md-1">
                                    <div className="form-group">
                                        <label>性别*</label>
                                        <select
                                            onChange={e => {
                                                this.setState({sex: e.target.value})
                                            }}
                                            className="form-control border-input"
                                        >
                                            <option>男</option>
                                            <option>女</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div className="row">
                                <div className="col-md-3">
                                    <div className="form-group">
                                        <label>护照号码*</label>
                                        <input type="text"
                                               onChange={e => {
                                                   this.setState({passport: e.target.value})
                                               }}
                                               className="form-control border-input"
                                               placeholder=""
                                               value={passport}
                                        />
                                    </div>
                                </div>
                                <div className="col-md-3">
                                    <div className="form-group">
                                        <label>护照有效期*</label>
                                        <DateTime
                                            viewMode="days"
                                            dateFormat='YYYY-MM-DD'
                                            timeFormat= {false}
                                            closeOnSelect = {true}
                                            closeOnTab = {true}
                                        />
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
