import React from 'react';
import 'whatwg-fetch';
import {Form, Input, Select, message} from 'antd';
import LogTable from "components/uiCompoment/LogTable";
import ManagerArea from "components/uiCompoment/ManagerArea"
const FormItem = Form.Item;
const Option = Select.Option;

class QjContentForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: false,
            deleteLoading: false,
            fresh: 0,
        };
        this.add = this.add.bind(this);
        this.update = this.update.bind(this);
        this.delete = this.delete.bind(this);
    }


    componentWillReceiveProps(nextProps) {
        if (nextProps.info && nextProps.fresh !== this.props.fresh) {
            let info = nextProps.info;
            delete info.status;
            delete info.info;
            let value = info;
            const {fresh} = this.state;
            this.setState({fresh: fresh + 1});
            this.props.form.setFieldsValue(value);
        }
    }

    componentDidMount() {
        if (this.props.info) {
            let value = this.props.info;
            delete value.info;
            delete value.status;
            this.props.form.setFieldsValue(value);
        }
    }

    delete() {
        const {getFieldValue} = this.props.form;
        if (getFieldValue('passport_no') === undefined) {
            message.error("请先搜索需要修改的记录", 5);
            return;
        }
        if (!confirm('确定要删除吗?')) return;
        this.setState({deleteLoading: true});
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                let formData = new FormData();
                formData.append("id", this.props.form.getFieldValue("qj_id"))
                formData.append("type", "qj")
                fetch("/deleteInfo", {
                    method: 'post',
                    credentials: 'include',
                    body: formData
                }).then(response => response.json())
                    .then(result => {
                        this.setState({deleteLoading: false});
                        if (result.status >= 0) {
                            message.success(result.info, 5);
                        } else {
                            message.error(result.info, 5);
                        }
                        this.props.form.resetFields();
                    })
            }
        })
    }

    add() {
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                this.setState({loading: true});
                fetch('/addQjInfo', {
                    method: 'post',
                    credentials: 'include',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(values)
                }).then(response => response.json()
                ).then(json => {
                    this.setState({loading: false});
                    if (json.status >= 0) {
                        message.success(json.info, 5);
                        this.props.form.resetFields();
                    } else {
                        message.error(json.info, 5);
                    }
                })
            } else {
            }
        })
    }

    update() {
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                this.setState({loading: true});
                fetch('/updateQjInfo', {
                    method: 'post',
                    credentials: 'include',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(values)
                }).then(response => response.json()
                ).then(json => {
                    this.setState({loading: false});
                    if (json.status >= 0) {
                        message.success(json.info, 5);
                        // this.props.form.resetFields();
                    } else {
                        message.error(json.info, 5);
                    }
                })
            }
        })
    }

    render() {
        const {loading, deleteLoading, fresh} = this.state;
        const {mode,type} = this.props;
        const {getFieldDecorator, getFieldValue} = this.props.form;
        let o_id = getFieldValue(`qj_id`);
        return (
            <Form>
                {getFieldDecorator('qj_id')(
                    <Input
                        style={{display: 'none'}}
                        disabled
                    />)
                }
                {getFieldDecorator('type', {
                    initialValue: type,
                })(
                    <Input
                        style={{display: 'none'}}
                        disabled
                    />)
                }
                <div className="row">
                    <div className="col-md-3">
                        <FormItem className="form-group" label="中文名">
                            {getFieldDecorator('ch_name', {
                                rules: [{
                                    required: true,
                                    message:"请输入中文名",
                                }],
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="中文名"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="护照号码">
                            {getFieldDecorator('passport_no', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="护照号码"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="身份证号">
                            {getFieldDecorator('id_num', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="身份证号"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="民族">
                            {getFieldDecorator('ethnicity', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="民族"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-2">
                        <FormItem className="form-group" label="性别">
                            {getFieldDecorator('sex', {
                                initialValue: '',
                            })(
                                <Select disabled={mode === 'search'}>
                                    <Option value="">请选择</Option>
                                    <Option value="男">男</Option>
                                    <Option value="女">女</Option>
                                </Select>
                            )}
                        </FormItem>
                    </div>
                </div>
                <hr/>
                <div className="row">
                    <div className="col-md-3">
                        <FormItem className="form-group" label="常用电话">
                            {getFieldDecorator('o_tel', {
                                initialValue: '',
                                rules: [{
                                    required: true,
                                    message: '请输入常用电话'
                                }],
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="常用电话"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="家庭住址">
                            {getFieldDecorator('family_location', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="家庭住址"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>

                    <div className="col-md-3">
                        <FormItem className="form-group" label="管理区域">
                            {getFieldDecorator('manager_area', {
                                initialValue: "浙江省",
                            })(
                                <ManagerArea/>
                            )}
                        </FormItem>
                    </div>
                </div>
                <hr/>

                <div className="row">
                    <div className="col-md-3">
                        <FormItem className="form-group" label="海外亲属姓名">
                            {getFieldDecorator('o_name', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="海外亲属姓名"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="与海外直系亲属关系">
                            {getFieldDecorator('o_relation', {
                                initialValue: '',
                            })(
                                <Select disabled={mode === 'search'}>
                                    <Option value="">请选择</Option>
                                    <Option value="夫妻">夫妻</Option>
                                    <Option value="兄弟姐妹">兄弟姐妹</Option>
                                    <Option value="父子">父子</Option>
                                    <Option value="母子">母子</Option>
                                    <Option value="祖孙">祖孙（女）</Option>
                                    <Option value="外祖孙">外祖孙（女）</Option>
                                </Select>
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="海外直系亲属护照号">
                            {getFieldDecorator('o_passport', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="海外直系亲属护照号"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="海外直系亲属旅居国或地区">
                            {getFieldDecorator('o_residence', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="海外直系亲属旅居国或地区"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                </div>

                <hr/>
                <div className="row">
                    <div className="col-md-12">
                        <FormItem className="form-group" label="备注">
                            {getFieldDecorator('remarks', {
                                initialValue: '',
                                rules: [{
                                    max: 100,
                                    message: '最多输入100字'
                                }],
                            })(
                                <Input.TextArea
                                    disabled={mode === 'search'}
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                </div>
                <hr/>
                {mode === 'view' && <div>
                    <hr/>
                    <h5>修改记录</h5>
                    <LogTable
                        type="qj"
                        o_id={o_id}
                        fresh={fresh}
                    />
                </div>}
                <hr/>
                <div className="text-center">
                    {mode === 'add' && <button type="button"
                                               className="btn btn-info btn-fill btn-wd"
                                               onClick={e => {
                                                   this.add(e)
                                               }}
                                               disabled={loading}
                    >{loading &&
                    <i style={{marginRight: 5}} className="anticon anticon-spin anticon-loading"/>}
                        录入
                    </button>}
                    {mode === 'view' &&
                    <div>
                        <button type="button"
                                className="btn btn-info btn-fill btn-wd"
                                onClick={e => {
                                    this.update(e)
                                }}
                                disabled={loading}
                        >{loading &&
                        <i style={{marginRight: 5}} className="anticon anticon-spin anticon-loading"/>}
                            更新
                        </button>

                        <button
                            style={{marginLeft: 10}}
                            type="button"
                            className="btn btn-danger btn-fill btn-wd"
                            disabled={deleteLoading}
                            onClick={e => {
                                this.delete()
                            }}>删除
                        </button>
                    </div>
                    }
                </div>
            </Form>
        )
    }
}

const QjContent = Form.create()(QjContentForm);
export default QjContent;