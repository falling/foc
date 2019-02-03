import React from 'react';
import 'whatwg-fetch';
import {Form, Input, message, Select} from 'antd';
import ManagerArea from "components/uiCompoment/ManagerArea";

const Search = Input.Search;
const FormItem = Form.Item;
const Option = Select.Option;


class UserManageForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            updateLoading: false,
            deleteLoading: false
        };
        this.search = this.search.bind(this);
        this.update = this.update.bind(this);
        this.delete = this.delete.bind(this);
    }

    search(username) {
        let formData = new FormData();
        formData.append("username", username);
        fetch("/api/searchUser", {
            method: 'post',
            credentials: 'include',
            body: formData
        }).then(response => response.json())
            .then(json => {
                if (json.status > 0) {
                    delete json.status;
                    delete json.info;
                    delete json.pwd;
                    delete json.remarks;
                    this.props.form.setFieldsValue(json);
                } else {
                    message.error(json.info, 5);
                }
            })
    }

    update() {
        const {getFieldValue} = this.props.form;
        if (getFieldValue('id') === undefined) {
            message.error("请先搜索需要修改的用户", 5);
            return;
        }
        this.setState({updateLoading: true});
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                fetch('/api/updateUserManager', {
                    method: 'post',
                    credentials: 'include',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(values)
                }).then(response => response.json()
                ).then(user => {
                    this.setState({updateLoading: false});
                    if (user.status >= 0) {
                        message.success(user.info, 5);
                    } else {
                        message.error(user.info, 5);
                    }
                    this.props.form.resetFields();
                })
            } else {
                this.setState({updateLoading: false});
            }
        });
    }

    delete() {
        const {getFieldValue} = this.props.form;
        if (getFieldValue('id') === undefined) {
            message.error("请先搜索需要修改的用户", 5);
            return;
        }
        let formData = new FormData();
        formData.append("id", getFieldValue('id'));
        if (!confirm('确定要删除该用户吗?')) return;

        this.setState({deleteLoading: true});
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                fetch('/api/deleteUser', {
                    method: 'post',
                    credentials: 'include',
                    body: formData
                }).then(response => response.json()
                ).then(user => {
                    this.setState({deleteLoading: false});
                    if (user.status >= 0) {
                        message.success(user.info, 5);
                    } else {
                        message.error(user.info, 5);
                    }
                    this.props.form.resetFields();
                })
            } else {
                this.setState({deleteLoading: false});
            }
        });
    }

    componentDidMount() {
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {display, user} = this.props;
        const {getFieldDecorator} = this.props.form;
        const {updateLoading, deleteLoading} = this.state;
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">管理用户</h4>
                        </div>
                        <div className="content">
                            <Form>
                                {getFieldDecorator('id')(
                                    <Input
                                        style={{display: 'none'}}
                                        disabled
                                    />
                                )}
                                <div className="row">
                                    <div className="col-md-12">
                                        <div className="form-group">
                                            <Search
                                                placeholder="请输入用户名"
                                                onSearch={value => this.search(value)}
                                                enterButton
                                            />
                                        </div>
                                    </div>
                                </div>
                            </Form>
                            <Form>
                                <div className="row">
                                    <div className="col-md-6">
                                        <FormItem className="form-group" label="用户名">
                                            {getFieldDecorator('username')(
                                                <Input
                                                    className="form-control border-input"
                                                    disabled
                                                />
                                            )}
                                        </FormItem>
                                    </div>
                                    <div className="col-md-6">
                                        <FormItem className="form-group" label="姓名">
                                            {getFieldDecorator('name', {
                                                rules: [{required: true, message: '请输入姓名'}],
                                            })(
                                                <Input
                                                    className="form-control border-input"
                                                />
                                            )}
                                        </FormItem>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-6">
                                        <FormItem className="form-group">
                                            <label>权限</label>
                                            {getFieldDecorator('power', {
                                                initialValue: 'user',
                                            })(
                                                <Select disabled={!(user && user.power === 'root')}>
                                                    <Option value="user">普通用户</Option>
                                                    <Option value="admin">管理员</Option>
                                                </Select>
                                            )}
                                        </FormItem>
                                    </div>
                                    <div className="col-md-6">
                                        <FormItem className="form-group" label="所属侨联">
                                            {getFieldDecorator('manager_area', {
                                            })(
                                                <ManagerArea/>
                                            )}
                                        </FormItem>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-6">
                                        <FormItem className="form-group">
                                            <label>注册日期</label>
                                            {getFieldDecorator('reg_date')(
                                                <Input
                                                    className="form-control border-input"
                                                    disabled
                                                />
                                            )}
                                        </FormItem>
                                    </div>
                                </div>

                                <div className="text-center">
                                    <button className="btn btn-info btn-fill btn-wd"
                                            style={{marginRight: 10}}
                                            onClick={e => {
                                                this.update()
                                            }}
                                            disabled={updateLoading}
                                    >
                                        {updateLoading &&
                                        <i style={{marginRight: 5}} className="anticon anticon-spin anticon-loading"/>}
                                        更新用户信息
                                    </button>
                                    <button
                                        style={{marginLeft: 10}}
                                        type="button"
                                        disabled={deleteLoading}
                                        className="btn btn-danger btn-fill btn-wd"
                                        onClick={e => {
                                            this.delete()
                                        }}
                                    >删除用户
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

const UserManage = Form.create()(UserManageForm);
export default UserManage;
