import React from 'react';
import {Input, Select,message} from 'antd';
import 'whatwg-fetch';

const Search = Input.Search;
const Option = Select.Option;

export default class InfoSearch extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
        };
        this.search=this.search.bind(this);
    }

    componentDidMount() {
        //get user info
    }

    componentWillReceiveProps(nextProps) {
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

    render(){
        const {display} =this.props;
        return(
            <div className="container-fluid" style={{display: !display&&'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">录入管理</h4>
                        </div>
                        <div className="content">
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <Search addonBefore={
                                            <Select onChange={value=>this.setState({type:value})} defaultValue="hq">
                                                <Option value="hq">华侨</Option>
                                                <Option value="lx">留学</Option>
                                                <Option value="qj">侨眷</Option>
                                            </Select>}
                                                onSearch={value => this.search(value)}
                                                enterButton
                                        />
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <div>table</div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}