import React from 'react';
import {Form, Input, Select, message, DatePicker, Cascader} from 'antd';
import 'whatwg-fetch';
import moment from 'moment';
import PicturesWall from "../../../../uiCompoment/PicturesWall";
import {City} from "../../../../config/City";

const FormItem = Form.Item;
const Option = Select.Option;
let options = [];

class Hq_lxContentForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: false,
            updating: false,
            deleteLoading: false,
            registrant: '',
        };
        this.url = '';
        this.photo = '';
        this.clean = false;
        this.add = this.add.bind(this);
        this.update = this.update.bind(this);
        this.getPhotoUrl = this.getPhotoUrl.bind(this);
        this.delete = this.delete.bind(this);
        this.getChild = this.getChild.bind(this);
        options = this.getChild(City);
    }

    getChild(e) {
        if (e) {
            return e.map(city => ({
                label: city.name,
                value: city.name,
                children: this.getChild(city.childs)
            }))
        }
    }


    getPhotoUrl(url) {
        this.url = url;
        this.photo = '';
        this.clean = false;
    }

    update() {
        const {getFieldValue} = this.props.form;
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                values.native_place = values.native_place.join("/");
                values.photo = this.url || this.photo;
                this.setState({loading: true});
                const {type} = this.props;
                fetch(type === 'lx' ? '/updateLXInfo' : '/updateHQInfo', {
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
                    this.clean = true;
                    this.props.form.resetFields();
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
        if (!confirm('确定要删除该用户吗?')) return;

        this.setState({deleteLoading: true});
        let formData = new FormData();
        const {type} = this.props;
        formData.append("id", this.props.form.getFieldValue(type === 'hq' ? 'hq_id' : 'lx_id'));
        formData.append('type', type);
        fetch('/deleteInfo', {
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
            this.clean = true;
            this.props.form.resetFields();
        })

    }

    add(e) {
        // this.props.getContent();
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                const {type} = this.props;
                this.setState({loading: true});
                values.photo = this.url;
                values.native_place = values.native_place.join("/");
                fetch(type === 'lx' ? '/addLXInfo' : '/addHQInfo', {
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
                    this.props.form.resetFields();
                })
            } else {
            }
        })

    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.type && nextProps.type !== this.props.type) {
            this.clean = true;
            this.props.form.resetFields();
            return;
        }
        if (nextProps.info && nextProps.fresh !== this.props.fresh) {
            let info = nextProps.info;
            delete info.status;
            delete info.info;
            info.date_birth = info.date_birth ? moment(info.date_birth) : '';
            info.date_expriy = info.date_expriy ? moment(info.date_expriy) : '';
            if (this.props.type === 'lx') {
                info.gra_date = info.gra_date ? moment(info.gra_date) : '';
            }
            this.photo = info.photo;

            if (info.native_place) { // 'a/b/c',[],[a,b,c],
                if (typeof (info.native_place) === "string") {
                    info.native_place = info.native_place.split("/")
                }
            } else { // undefined,''
                info.native_place = []
            }
            this.props.form.setFieldsValue(info);
        }
    }

    componentDidMount() {
        if(this.props.info){
            let info = this.props.info;
            delete info.status;
            delete info.info;
            info.date_birth = info.date_birth ? moment(info.date_birth) : '';
            info.date_expriy = info.date_expriy ? moment(info.date_expriy) : '';
            if (this.props.type === 'lx') {
                info.gra_date = info.gra_date ? moment(info.gra_date) : '';
            }
            this.photo = info.photo;

            if (info.native_place) { // 'a/b/c',[],[a,b,c],
                if (typeof (info.native_place) === "string") {
                    info.native_place = info.native_place.split("/")
                }
            } else { // undefined,''
                info.native_place = []
            }
            this.photo = info.photo;
            this.props.form.setFieldsValue(info);
        }
    }

    render() {
        const {type, mode} = this.props;
        const {getFieldDecorator} = this.props.form;
        const {loading, deleteLoading} = this.state;
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
                                    pattern: /^[\u4e00-\u9fa5]+$/,
                                    message: '请输入中文名'
                                }],
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="中文名"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="拼音">
                            {getFieldDecorator('py_name', {
                                rules: [{
                                    required: true,
                                    pattern: /^[a-z|A-Z| ]+$/,
                                    message: '请输入拼音'
                                }],
                            })(
                                <Input
                                    disabled={mode==='search'}
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
                                rules: [{
                                    pattern: /^[\u4e00-\u9fa5]+$/,
                                    message: '请输入中文'
                                }],
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="曾用名"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-2">
                        <FormItem className="form-group" label="民族">
                            {getFieldDecorator('ethnicity', {
                                rules: [{
                                    required: true,
                                    pattern: /^[\u4e00-\u9fa5]+$/,
                                    message: '请输入民族'
                                }],
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="民族"
                                    className="form-control border-input"
                                />
                            )}

                        </FormItem>
                    </div>
                    <div className="col-md-2">
                        <FormItem className="form-group" label="性别">
                            {getFieldDecorator('sex', {
                                initialValue: '男',
                            })(
                                <Select disabled={mode==='search'}>
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
                                validateTrigger: 'onBlur',
                                rules: [{
                                    required: true,
                                    pattern: /^1[45][0-9]{7}|([P|p|S|s]\d{7})|([S|s|G|g]\d{8})|([Gg|Tt|Ss|Ll|Qq|Dd|Aa|Ff]\d{8})|([H|h|M|m]\d{8,10})$/,
                                    message: '请输入护照号码'
                                }],
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="护照号码"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="护照有效期">
                            {getFieldDecorator('date_expriy', {
                                rules: [{
                                    required: true,
                                    message: '请选择护照有效期'
                                }],
                            })(
                                <DatePicker
                                    disabled={mode==='search'}
                                    disabledDate={current => current < moment().endOf('day')}
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="生日">
                            {getFieldDecorator('date_birth', {
                                rules: [{required: true, message: '请选择出生日期'}],
                            })(
                                <DatePicker
                                    disabled={mode==='search'}
                                    disabledDate={current => current > moment().endOf('day')}
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="身份证号">
                            {getFieldDecorator('id_num', {
                                initialValue: '',
                                validateTrigger: 'onBlur',
                                rules: [{
                                    pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)/,
                                    message: '请输入身份证号',
                                }],
                            })(
                                <Input
                                    disabled={mode==='search'}
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
                        <FormItem className="form-group" label="海外联系电话">
                            {getFieldDecorator('o_tel', {
                                initialValue: '',
                                rules: [{
                                    message: '请输入海外联系电话'
                                }],
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="海外联系电话"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="中国联系电话1">
                            {getFieldDecorator('cn_tel', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="中国联系电话1"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="中国联系电话2">
                            {getFieldDecorator('cn_te2', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="中国联系电话2"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
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
                                    disabled={mode==='search'}
                                    placeholder="微信"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
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
                                    disabled={mode==='search'}
                                    placeholder="邮箱"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="QQ">
                            {getFieldDecorator('qq_num', {
                                initialValue: '',
                                rules: [{
                                    pattern: /[1-9][0-9]{4,14}/,
                                    message: 'qq格式不正确'
                                }]
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="qq号"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                </div>
                <hr/>
                <div className="row">
                    <div className="col-md-4">
                        <FormItem className="form-group" label="现国籍">
                            {getFieldDecorator('nationality', {
                                rules: [{
                                    required: true,
                                    pattern: /^[\u4e00-\u9fa5]+$/,
                                    message: '请输入国籍'
                                }]
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="国籍"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="旅居地">
                            {getFieldDecorator('residence', {
                                rules: [{
                                    required: true,
                                    message: '请输入居旅地'
                                }]
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="旅居地"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="中国居住地">
                            {getFieldDecorator('cn_residence', {
                                rules: [{
                                    required: true,
                                    pattern: /^[\u4e00-\u9fa5]+$/,
                                    message: '请输入中国居住地'
                                }]
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="请输入中国居住地"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-4">
                        <FormItem className="form-group" label="籍贯">
                            {getFieldDecorator('native_place', {
                                initialValue: [],
                            })(
                                <Cascader
                                    disabled={mode==='search'}
                                    options={options}
                                    placeholder="籍贯"
                                />
                            )}
                            <div/>
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
                                    disabled={mode==='search'}
                                    placeholder="所从事行业"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="公司/单位名称">
                            {getFieldDecorator('com_name', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="公司/单位"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="职务">
                            {getFieldDecorator('position', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="职务"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-4">
                        <FormItem className="form-group" label="文化程度">
                            {getFieldDecorator('education', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="文化程度"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group" label="健康状态">
                            {getFieldDecorator('health', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode==='search'}
                                    placeholder="健康状态"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                </div>

                {type === 'lx' &&
                <div>
                    <hr/>
                    <div className="row">
                        <div className="col-md-3">
                            <FormItem className="form-group" label="毕业院校英文名">
                                {getFieldDecorator('en_cname', {
                                    rules: [{
                                        required: true,
                                        message: '请输入毕业院校英文名'
                                    }],
                                })(
                                    <Input
                                        disabled={mode==='search'}
                                        placeholder="毕业院校英文名"
                                        className="form-control border-input"
                                    />
                                )}
                                <div/>
                            </FormItem>
                        </div>
                        <div className="col-md-3">
                            <FormItem className="form-group" label="毕业院校中文名">
                                {getFieldDecorator('ch_cname', {
                                    rules: [{
                                        required: true,
                                        message: '请输入毕业院校中文名'
                                    }],
                                })(
                                    <Input
                                        disabled={mode==='search'}
                                        placeholder="毕业院校中文名"
                                        className="form-control border-input"
                                    />
                                )}
                                <div/>
                            </FormItem>
                        </div>
                        <div className="col-md-3">
                            <FormItem className="form-group" label="学位">
                                {getFieldDecorator('degree', {
                                    rules: [{
                                        required: true,
                                        message: '请输入学位'
                                    }],
                                })(
                                    <Input
                                        disabled={mode==='search'}
                                        placeholder="学位"
                                        className="form-control border-input"
                                    />
                                )}
                                <div/>
                            </FormItem>
                        </div>

                        <div className="col-md-3">
                            <FormItem className="form-group" label="毕业时间">
                                {getFieldDecorator('gra_date', {
                                    rules: [{required: true, message: '请选择毕业时间'}],
                                })(
                                    <DatePicker
                                        disabled={mode==='search'}
                                    />
                                )}
                            </FormItem>
                        </div>
                    </div>
                </div>
                }
                <hr/>

                <div className="row">
                    <div className="col-md-4">
                        <div className="form-group">
                            <label>照片</label>
                            <PicturesWall
                                clean={this.clean}
                                url={(mode === 'view'||mode==='search') ? this.photo : ''}
                                getUrl={this.getPhotoUrl}
                            />
                            <div/>
                        </div>
                    </div>
                </div>
                {(mode === 'view'||mode === 'search') &&
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

