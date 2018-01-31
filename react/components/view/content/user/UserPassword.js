import React from 'react';
import 'whatwg-fetch';
import {message, Input, Form} from 'antd';

const FormItem = Form.Item;

class UserPasswordForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            confirmDirty: false,
            loading: false
        };
        this.changePassword = this.changePassword.bind(this);
        this.checkPassword = this.checkPassword.bind(this);
        this.checkConfirm = this.checkConfirm.bind(this);
        this.handleConfirmBlur = this.handleConfirmBlur.bind(this);
    }

    handleConfirmBlur(e) {
        const value = e.target.value;
        this.setState({confirmDirty: this.state.confirmDirty || !!value});
    }

    checkPassword(rule, value, callback) {
        const form = this.props.form;
        if (value && value !== form.getFieldValue('password')) {
            callback('两次输入的密码不相同');
        } else {
            callback();
        }
    }

    checkConfirm(rule, value, callback) {
        const form = this.props.form;
        if (value && this.state.confirmDirty) {
            form.validateFields(['password2'], {force: true});
        }
        callback();
    }

    changePassword() {
        this.props.form.validateFieldsAndScroll((err, values) => {
            if(!err){
                this.setState({loading: true});
                let formData = new FormData();
                formData.append("password_old",values.password_old);
                formData.append("password",values.password);
                fetch("/changePwd",{
                    method: 'post',
                    credentials: 'include',
                    body: formData
                }).then(response => response.json())
                    .then(result=>{
                        if(result.status>=0){
                            message.success(result.info, 5);
                        }else{
                            message.error(result.info, 5);
                        }
                        this.setState({loading:false})
                        this.props.form.resetFields();
                    })
            }
        })
    }

    render() {
        const {display} = this.props;
        const {getFieldDecorator} = this.props.form;
        const {loading} = this.state;

        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">修改密码</h4>
                        </div>
                        <div className="content">
                            <Form>
                                <div className="row">
                                    <div className="col-md-offset-4 col-md-4">
                                        <FormItem className="form-group" label="原始密码">
                                            {getFieldDecorator('password_old', {
                                                rules: [{
                                                    required: true,
                                                    message: '请输入密码'
                                                }],
                                            })(
                                                <Input
                                                    placeholder="密码"
                                                    className="form-control border-input"
                                                />
                                            )}
                                        </FormItem>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-offset-4 col-md-4">
                                        <FormItem className="form-group" label="新密码">
                                            {getFieldDecorator('password', {
                                                rules: [{
                                                    required: true,
                                                    message: '请输入新密码'
                                                }],
                                            })(
                                                <Input
                                                    placeholder="新密码"
                                                    className="form-control border-input"
                                                />
                                            )}
                                        </FormItem>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-offset-4 col-md-4">
                                        <FormItem className="form-group" label="确认密码">
                                            {getFieldDecorator('password2', {
                                                rules: [{
                                                    required: true,
                                                    message: '请输入新密码'
                                                }],
                                            })(
                                                <Input
                                                    placeholder="新密码"
                                                    className="form-control border-input"
                                                />
                                            )}
                                        </FormItem>
                                    </div>
                                </div>
                            </Form>
                            <hr/>
                            <div className="text-center">
                                <button type="button"
                                        className="btn btn-info btn-fill btn-wd"
                                        onClick={e => {
                                            this.changePassword()
                                        }}
                                        disabled={loading}
                                >{loading &&
                                <i style={{marginRight: 5}} className="anticon anticon-spin anticon-loading"/>}
                                    修改密码
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

const UserPassword = Form.create()(UserPasswordForm);
export default UserPassword;