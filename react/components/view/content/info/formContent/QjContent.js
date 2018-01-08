import React from 'react';
import 'react-datetime/css/react-datetime.css'

export default class QjContent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            pinyin: '',
            used_name: '',
            sex: '男',
            nationality: '',
            passport: '',
            passport_date: '',
        };
        this.update = this.update.bind(this);

    }
    update(){

        this.props.getContent();
    }
    render(){
        const {name,pinyin,used_name,sex,nationality,passport,passport_date} = this.state;
        return(
            <div>
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
                            <label>身份证号*</label>
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
                            <label>名族*</label>
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
                    <div className="col-md-2">
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
                <hr/>
                <div className="row">
                    <div className="col-md-3">
                        <div className="form-group">
                            <label>与华侨关系</label>
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
                            <label>联系电话</label>
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
                            <label>联系电话1</label>
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
                <hr/>
                <div className="text-center">
                    <button type="button" className="btn btn-info btn-fill btn-wd"
                            onClick={e => {
                                this.update()
                            }}
                    >录入
                    </button>
                </div>
            </div>
        )
    }
}