import React from 'react';
import FormContent from "./formContent/FormContent";
import {Input, Select,message} from 'antd';
import 'whatwg-fetch';

const Search = Input.Search;
const Option = Select.Option;

export default class InfoManage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            type: 'hq',
        };
        this.search = this.search.bind(this);
    }

    search(value){
        const {type} = this.state;
        let formData = new FormData();
        formData.append("passport_no", value);
        formData.append("type", type);
        fetch('/loadByPassport',{
            method: 'post',
            credentials: 'include',
            body: formData
        }).then(response=>response.json())
            .then(json=>{
                if (json.status > 0) {
                    this.setState({info:json});
                } else {
                    message.error(json.info, 5);
                }
            })

    }
    componentDidMount() {
        //get user info
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {display} = this.props;
        const {type,info} = this.state;
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">信息修改</h4>
                        </div>
                        <div className="content">
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <Search addonBefore={
                                            <Select onChange={value=>this.setState({type:value,info:undefined})} defaultValue="hq">
                                                <Option value="hq">华侨</Option>
                                                <Option value="lx">留学</Option>
                                                <Option value="qj_hq">归侨侨眷</Option>
                                                <Option value="qj_lx">留学生家属</Option>
                                            </Select>}
                                                placeholder={type==='qj'?'请输入护照号码或身份证号码':'请输入护照号码'}
                                                onSearch={value => this.search(value)}
                                                enterButton
                                        />
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <FormContent
                                type={type}
                                info={info}
                                mode= "view"
                            />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}