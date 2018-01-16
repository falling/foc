import React from 'react';
import {Form, Input, Select} from 'antd';

const FormItem = Form.Item;
const Option = Select.Option;

class UserCreateForm extends React.Component {
    constructor(props) {
        super(props);
        this.create = this.create.bind(this);
    }

    create(e) {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    }

    componentDidMount() {
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {display} = this.props;
        const {getFieldDecorator} = this.props.form;
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">创建用户</h4>
                        </div>
                        <div className="content">
                            <Form onSubmit={e=>{this.create(e)}}>
                                <div className="row">
                                    <div className="col-md-12">
                                        <FormItem
                                            className="form-group">
                                            <label>用户名*</label>
                                            {getFieldDecorator('userName', {
                                                rules: [{required: true, message: '请输入用户名'}],
                                            })(
                                                <Input
                                                    placeholder="用户名"
                                                    className="form-control border-input"
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
                                    <button type="submit" className="btn btn-info btn-fill btn-wd"
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
