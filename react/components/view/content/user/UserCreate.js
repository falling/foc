import React from 'react';
import {Form, Input, Select, message} from 'antd';
import 'whatwg-fetch';


const FormItem = Form.Item;
const Option = Select.Option;

class UserCreateForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: false,
            userNameError: '',
        }
        this.create = this.create.bind(this);
        this.checkName = this.checkName.bind(this);
    }

    checkName(name) {
        let formData = new FormData();
        formData.append("username", name);
        fetch('/checkName', {
            method: 'post',
            credentials: 'include',
            body: formData
        }).then(response => response.json())
            .then(json => {
                if (json.status < 0) {
                    this.props.form.setFields({
                        username: {
                            value: name,
                            errors: [new Error(json.info)],
                        },
                    });
                }
            })
    }

    create(e) {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                this.setState({loading: true});//loading
                fetch('/createUser', {
                    method: 'post',
                    credentials: 'include',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(values)
                }).then(response => response.json()
                ).then(user => {
                    this.setState({loading: false});
                    if (user.status >= 0) {
                        message.success(user.info, 5);
                    } else {
                        message.error(user.info, 5);
                    }
                    this.props.form.resetFields();
                })
            }
        });
    }

    componentDidMount() {
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {display} = this.props;
        const {getFieldDecorator, getFieldError} = this.props.form;
        const {userNameError} = this.state;
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">创建用户</h4>
                        </div>
                        <div className="content">
                            <Form>
                                <div className="row">
                                    <div className="col-md-12">
                                        <FormItem
                                            className="form-group">
                                            <label>用户名*</label>
                                            {getFieldDecorator('username', {
                                                rules: [{required: true, message: '请输入用户名'}],
                                            })(
                                                <Input
                                                    placeholder="用户名"
                                                    className="form-control border-input"
                                                    onBlur={e => {
                                                        this.checkName(e.target.value)
                                                    }}
                                                />
                                            )}
                                        </FormItem>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-12">
                                        <FormItem
                                            className="form-group">
                                            <label>姓名*</label>
                                            {getFieldDecorator('name', {
                                                rules: [{required: true, message: '请输入姓名'}],
                                            })(
                                                <Input
                                                    placeholder="姓名"
                                                    className="form-control border-input"
                                                />
                                            )}
                                        </FormItem>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-12">
                                        <div className="form-group">
                                            <label>权限</label>
                                            {getFieldDecorator('power', {
                                                initialValue: 'user',
                                            })(
                                                <Select>
                                                    <Option value="user">普通用户</Option>
                                                    <Option value="admin">管理员</Option>
                                                </Select>
                                            )}
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-12">
                                        <div className="form-group">
                                            <label>初始密码为:123456</label>
                                        </div>
                                    </div>
                                </div>

                                <div className="text-center">
                                    <button
                                        type="button"
                                        className="btn btn-info btn-fill btn-wd"
                                        onClick={e => {
                                            this.create(e)
                                        }}
                                    >创建用户
                                    </button>
                                </div>
                            </Form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

const UserCreate = Form.create()(UserCreateForm);
export default UserCreate;
