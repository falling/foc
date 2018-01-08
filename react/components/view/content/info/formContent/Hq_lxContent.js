import React from 'react';
import DateTime from 'react-datetime'
import 'react-datetime/css/react-datetime.css'

export default class Hq_lxContent extends React.Component {
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

    render() {
        const {type} = this.props;
        const {name,pinyin,used_name,sex,nationality,passport,passport_date} = this.state;
        return (
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
                                className="border-input"
                                viewMode="days"
                                dateFormat='YYYY-MM-DD'
                                timeFormat={false}
                                closeOnSelect={true}
                                closeOnTab={true}
                            />
                        </div>
                    </div>
                    <div className="col-md-3">
                        <div className="form-group">
                            <label>出生日日期</label>
                            <DateTime
                                className="border-input"
                                viewMode="days"
                                dateFormat='YYYY-MM-DD'
                                timeFormat={false}
                                closeOnSelect={true}
                                closeOnTab={true}
                            />
                        </div>
                    </div>
                    <div className="col-md-3">
                        <div className="form-group">
                            <label>身份证号</label>
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
                </div>
                <hr/>
                <div className="row">
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>海外联系电话*</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>中国联系电话1</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>中国联系电话2</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>微信</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>邮箱</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>QQ</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                </div>
                <hr/>
                <div className="row">
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>现国籍*</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>旅居地*</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>中国居住地</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                </div>

                <hr/>
                <div className="row">
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>所从事行业</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>公司/单位名称</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>职务</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>文化程度</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>健康状态</label>
                            <input type="text"
                                   onChange={e => {
                                       this.setState({passport: e.target.value})
                                   }}
                                   className="form-control border-input"
                                   placeholder=""
                                   value={passport}
                            />
                            <div/>
                        </div>
                    </div>
                </div>

                {type === 'lx' &&
                < div>
                    <hr/>
                    < div className="row">
                        <div className="col-md-3">
                            <div className="form-group">
                                <label>毕业院校英文名*</label>
                                <input type="text"
                                       onChange={e => {
                                           this.setState({passport: e.target.value})
                                       }}
                                       className="form-control border-input"
                                       placeholder=""
                                       value={passport}
                                />
                                <div/>
                            </div>
                        </div>
                        <div className="col-md-3">
                            <div className="form-group">
                                <label>毕业院校中文名*</label>
                                <input type="text"
                                       onChange={e => {
                                           this.setState({passport: e.target.value})
                                       }}
                                       className="form-control border-input"
                                       placeholder=""
                                       value={passport}
                                />
                                <div/>
                            </div>
                        </div>
                        <div className="col-md-3">
                            <div className="form-group">
                                <label>学位*</label>
                                <input type="text"
                                       onChange={e => {
                                           this.setState({passport: e.target.value})
                                       }}
                                       className="form-control border-input"
                                       placeholder=""
                                       value={passport}
                                />
                                <div/>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="form-group">
                                <label>毕业时间*</label>
                                <DateTime
                                    className="border-input"
                                    viewMode="days"
                                    dateFormat='YYYY-MM-DD'
                                    timeFormat={false}
                                    closeOnSelect={true}
                                    closeOnTab={true}
                                />
                                <div/>
                            </div>
                        </div>
                    </div>
                </div>
                }
                <hr/>
                <div className="row">
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>照片</label>
                            <input type="file"
                                   className="form-control border-input"
                                   placeholder=""
                            />
                            <div/>
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