import React from 'react';
import {Form, Input, Select, message, DatePicker, Cascader} from 'antd';
import 'whatwg-fetch';
import moment from 'moment';
import PicturesWall from "components/uiCompoment/PicturesWall";
import {pc_code} from "components/config/pc_code";
import LogTable from "components/uiCompoment/LogTable";
import ManagerArea from "components/uiCompoment/ManagerArea";
const FormItem = Form.Item;
const Option = Select.Option;
class Hq_lxContentForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: false,
            deleteLoading: false,
            registrant: '',
            fresh: 0,
        };
        this.add = this.add.bind(this);
        this.update = this.update.bind(this);
        this.delete = this.delete.bind(this);
        this.setValue = this.setValue.bind(this);
    }

    update() {
        const {getFieldValue} = this.props.form;
        if (getFieldValue('passport_no') === undefined) {
            message.error("请先搜索需要修改的记录", 5);
            return;
        }
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                values.native_place = values.native_place.join("/");
                this.setState({loading: true});
                const {type} = this.props;
                fetch(type === 'lx' ? '/api/updateLXInfo' : '/api/updateHQInfo', {
                    method: 'post',
                    credentials: 'include',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(values)
                }).then(response => response.json()
                ).then(json => {
                    this.setState({loading: false});
                    if (json.status >= 0) {
                        message.success(json.info, 5);
                    } else {
                        message.error(json.info, 5);
                    }
                })
            }
        })
    }

    delete() {
        const {getFieldValue} = this.props.form;
        if (getFieldValue('passport_no') === undefined) {
            message.error("请先搜索需要修改的记录", 5);
            return;
        }
        if (!confirm('确定要删除吗?')) return;

        this.setState({deleteLoading: true});
        let formData = new FormData();
        const {type} = this.props;
        formData.append("id", this.props.form.getFieldValue(type === 'hq' ? 'hq_id' : 'lx_id'));
        formData.append('type', type);
        fetch('/api/deleteInfo', {
            method: 'post',
            credentials: 'include',
            body: formData
        }).then(response => response.json()
        ).then(result => {
            this.setState({deleteLoading: false});
            if (result.status >= 0) {
                message.success(result.info, 5);
            } else {
                message.error(result.info, 5);
            }
        })

    }

    add(e) {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                const {type} = this.props;
                this.setState({loading: true});
                values.native_place = values.native_place.join("/");

                fetch(type === 'lx' ? '/api/addLXInfo' : '/api/addHQInfo', {
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

    componentWillReceiveProps(nextProps) {
        if (nextProps.type && nextProps.type !== this.props.type) {
            this.props.form.resetFields();
            return;
        }
        if (nextProps.info && nextProps.fresh !== this.props.fresh) {
            this.setValue(nextProps);
            const {fresh} = this.state;
            this.setState({fresh: fresh + 1})
        }
    }

    setValue(props) {
        let value = props.info;
        delete value.info;
        delete value.status;
        value.date_birth = value.date_birth ? moment(value.date_birth) : null;

        if (value.native_place) { // 'a/b',[],[a,b],
            if (typeof (value.native_place) === "string") {
                value.native_place = value.native_place.split("/")
            }
        } else { // undefined,''
            value.native_place = []
        }
        this.props.form.setFieldsValue(value);

    }

    componentDidMount() {
        if (this.props.info) {
            this.setValue(this.props);
        }
    }

    render() {
        const {type, mode} = this.props;
        const {getFieldDecorator, getFieldValue} = this.props.form;
        const {loading, deleteLoading, fresh} = this.state;
        let o_id = getFieldValue(`${type}_id`);
        return (
            <Form>
                {getFieldDecorator('lx_id')(
                    <Input
                        style={{display: 'none'}}
                        disabled
                    />)
                }
                {getFieldDecorator('hq_id')(
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
                                    message:'请输入中文名',
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
                        <FormItem className="form-group" label="拼音">
                            {getFieldDecorator('py_name', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="拼音"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-2">
                        <FormItem className="form-group" label="曾用名">
                            {getFieldDecorator('used_name', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="曾用名"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-2">
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
                    <div className="col-md-2">
                        <FormItem className="form-group" label="性别">
                            {getFieldDecorator('sex', {
                                initialValue: ''
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

                <div className="row">
                    <div className="col-md-3">
                        <FormItem className="form-group" label="护照号码">
                            {getFieldDecorator('passport_no', {
                                initialValue: ''
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
                        <FormItem className="form-group" label="出生年月日">
                            {getFieldDecorator('date_birth', {
                                initialValue: null,
                                rules: [{required: false, message: '请选择出生日期'}],
                            })(
                                <DatePicker
                                    disabled={mode === 'search'}
                                    disabledDate={current => current > moment().endOf('day')}
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="身份证号">
                            {getFieldDecorator('id_num', {
                                initialValue: ''
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="身份证号"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                </div>
                <hr/>
                <div className="row">
                    <div className="col-md-4">
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
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="中国联系电话">
                            {getFieldDecorator('cn_tel', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="中国联系电话"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-4">
                        <FormItem className="form-group" label="微信">
                            {getFieldDecorator('wechat', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="微信"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="邮箱">
                            {getFieldDecorator('mail', {
                                initialValue: '',
                                rules: [{
                                    type: 'email',
                                    required: false,
                                    message: '邮箱格式不正确'
                                }],
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="邮箱"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="QQ">
                            {getFieldDecorator('qq_num', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="qq号"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                </div>
                <hr/>
                <div className="row">
                    <div className="col-md-4">
                        <FormItem className="form-group" label="现国籍">
                            {getFieldDecorator('nationality', {
                                initialValue: ''
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="国籍"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="现居住国或地区">
                            {getFieldDecorator('residence', {
                                initialValue: ''
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="现居住国或地区"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="中国居住地详细地址">
                            {getFieldDecorator('cn_residence', {
                                initialValue: ''
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="请输入中国居住地"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-4">
                        <FormItem className="form-group" label="现旅居地详细地址">
                            {getFieldDecorator('residenceDetail', {
                                initialValue: ''
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="现旅居地详细地址"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>

                    <div className="col-md-4">
                        <FormItem className="form-group" label="籍贯">
                            {getFieldDecorator('native_place', {
                                initialValue: [],
                            })(
                                <Cascader
                                    disabled={mode === 'search'}
                                    options={pc_code}
                                    placeholder="籍贯"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="所属侨联">
                            {getFieldDecorator('manager_area', {
                                initialValue: "浙江省",
                            })(
                                <ManagerArea
                                    disabled={mode === 'search'}
                                />
                            )}
                        </FormItem>
                    </div>
                </div>

                <hr/>
                <div className="row">
                    <div className="col-md-4">
                        <FormItem className="form-group" label="所从事行业">
                            {getFieldDecorator('present_industry', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="所从事行业"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="公司/单位名称">
                            {getFieldDecorator('com_name', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="公司/单位"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="职务">
                            {getFieldDecorator('position', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="职务"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-4">
                        <FormItem className="form-group" label="社会任职">
                            {getFieldDecorator('social_services', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="社会任职"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                </div>
                <hr/>
                {type === 'lx' &&
                <div>
                    <hr/>
                    <div className="row">
                        <div className="col-md-3">
                            <FormItem className="form-group" label="毕业院校英文名">
                                {getFieldDecorator('en_cname', {
                                    initialValue: '',
                                })(
                                    <Input
                                        disabled={mode === 'search'}
                                        placeholder="毕业院校英文名"
                                        className="form-control border-input"
                                    />
                                )}
                            </FormItem>
                        </div>
                        <div className="col-md-3">
                            <FormItem className="form-group" label="毕业院校中文名">
                                {getFieldDecorator('ch_cname', {
                                    initialValue: '',
                                })(
                                    <Input
                                        disabled={mode === 'search'}
                                        placeholder="毕业院校中文名"
                                        className="form-control border-input"
                                    />
                                )}
                            </FormItem>
                        </div>
                        <div className="col-md-3">
                            <FormItem className="form-group" label="学位">
                                {getFieldDecorator('degree', {
                                    initialValue: '',
                                })(
                                    <Input
                                        disabled={mode === 'search'}
                                        placeholder="学位"
                                        className="form-control border-input"
                                    />
                                )}
                            </FormItem>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-4">
                            <FormItem className="form-group" label="国内直系亲属名字">
                                {getFieldDecorator('family_name', {
                                    initialValue: '',
                                })(
                                    <Input
                                        disabled={mode === 'search'}
                                        placeholder="国内直系亲属名字"
                                        className="form-control border-input"
                                    />
                                )}
                            </FormItem>
                        </div>

                        <div className="col-md-4">
                            <FormItem className="form-group" label="国内直系亲属联系方式">
                                {getFieldDecorator('family_tel', {
                                    initialValue: '',
                                })(
                                    <Input
                                        disabled={mode === 'search'}
                                        placeholder="国内直系亲属联系方式"
                                        className="form-control border-input"
                                    />
                                )}
                            </FormItem>
                        </div>
                    </div>
                </div>
                }
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
                        </FormItem>
                    </div>
                </div>

                <hr/>

                <div className="row">
                    <div className="col-md-4">
                        <FormItem className="form-group" label="照片">
                            {getFieldDecorator('photo', {
                                initialValue: '',
                            })(
                                <PicturesWall/>
                            )}
                        </FormItem>
                    </div>
                </div>
                <hr/>
                {(mode === 'view' || mode === 'search') &&
                <div>
                    <hr/>
                    <div className="row">
                        <div className="col-md-4">
                            <FormItem className="form-group" label="登记人">
                                {getFieldDecorator('registrant_name', {})(
                                    <Input
                                        className="form-control border-input"
                                        disabled
                                    />
                                )}
                                <div/>
                            </FormItem>
                        </div>
                        <div className="col-md-4">
                            <FormItem className="form-group" label="登记时间">
                                {getFieldDecorator('reg_date', {})(
                                    <Input
                                        disabled
                                        className="form-control border-input"
                                    />
                                )}
                                <div/>
                            </FormItem>
                        </div>
                    </div>
                </div>}

                {mode==='view'&&<div>
                    <hr/>
                    <h5>修改记录</h5>
                    <LogTable
                        type={type}
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

const Hq_lxContent = Form.create()(Hq_lxContentForm);
export default Hq_lxContent;

