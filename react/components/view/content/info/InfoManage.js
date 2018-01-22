import React from 'react';
import FormContent from "./formContent/FormContent";
import {Input, Select, Form} from 'antd';

const Search = Input.Search;
const InputGroup = Input.Group;
const Option = Select.Option;
const FormItem = Form.Item;

export default class InfoManage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            type: 'hq',
            user_name: ''
        };
        this.search = this.search.bind(this);
    }

    search(value){
        console.log(value);
    }
    componentDidMount() {
        //get user info
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {display} = this.props;
        const {user_name, type} = this.state;
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">录入管理</h4>
                        </div>
                        <div className="content">
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <Search addonBefore={<Select defaultValue="华侨">
                                            <Option value="华侨">华侨</Option>
                                            <Option value="留学">留学</Option>
                                            <Option value="侨眷">侨眷</Option>
                                        </Select>}
                                                placeholder="请输入护照号码"
                                                onSearch={value => this.search(value)}
                                                enterButton
                                        />
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <FormContent
                                type={type}
                            />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}