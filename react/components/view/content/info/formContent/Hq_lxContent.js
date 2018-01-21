import React from 'react';
import {Form, Input, Select, message, DatePicker, Upload} from 'antd';
import 'whatwg-fetch';
import moment from 'moment';
import PicturesWall from "../../../../uiCompoment/PicturesWall";


const FormItem = Form.Item;
const Option = Select.Option;

class Hq_lxContentForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: false,
            updating: false
        };
        this.url = '';
        this.update = this.update.bind(this);
        this.getPhotoUrl = this.getPhotoUrl.bind(this);

    }

    getPhotoUrl(url) {
        this.url = url;
    }

    update(e) {
        // this.props.getContent();
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                this.setState({loading: true});
                values.photo = this.url;
                fetch('/addHQInfo', {
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
                    // this.props.form.resetFields();
                })
            } else {
            }
        })

    }

    render() {
        const {type} = this.props;
        const {getFieldDecorator} = this.props.form;
        const {loading} = this.state;

        return (
            <Form>
                <div className="row">
                    <div className="col-md-3">
                        <FormItem className="form-group">
                            <label>中文名*</label>
                            {getFieldDecorator('ch_name', {
                                rules: [{
                                    required: true,
                                    pattern: /^[\u4e00-\u9fa5]+$/,
                                    message: '请输入中文名'
                                }],
                            })(
                                <Input
                                    placeholder="中文名"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group">
                            <label>拼音*</label>
                            {getFieldDecorator('py_name', {
                                rules: [{
                                    required: true,
                                    pattern: /^[a-z|A-Z| ]+$/,
                                    message: '请输入拼音'
                                }],
                            })(
                                <Input
                                    placeholder="拼音"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-2">
                        <FormItem className="form-group">
                            <label>曾用名</label>
                            {getFieldDecorator('used_name', {
                                rules: [{
                                    pattern: /^[\u4e00-\u9fa5]+$/,
                                    message: '请输入中文'
                                }],
                            })(
                                <Input
                                    placeholder="曾用名"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-2">
                        <FormItem className="form-group">
                            <label>民族*</label>
                            {getFieldDecorator('ethnicity', {
                                rules: [{
                                    required: true,
                                    pattern: /^[\u4e00-\u9fa5]+$/,
                                    message: '请输入民族'
                                }],
                            })(
                                <Input
                                    placeholder="民族"
                                    className="form-control border-input"
                                />
                            )}

                        </FormItem>
                    </div>
                    <div className="col-md-2">
                        <FormItem className="form-group">
                            <label>性别*</label>
                            {getFieldDecorator('sex', {
                                initialValue: '男',
                            })(
                                <Select>
                                    <Option value="男">男</Option>
                                    <Option value="女">女</Option>
                                </Select>
                            )}
                        </FormItem>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-3">
                        <FormItem className="form-group">
                            <label>护照号码*</label>
                            {getFieldDecorator('passport_no', {
                                validateTrigger: 'onBlur',
                                rules: [{
                                    required: true,
                                    pattern: /^1[45][0-9]{7}|([P|p|S|s]\d{7})|([S|s|G|g]\d{8})|([Gg|Tt|Ss|Ll|Qq|Dd|Aa|Ff]\d{8})|([H|h|M|m]\d{8,10})$/,
                                    message: '请输入护照号码'
                                }],
                            })(
                                <Input
                                    placeholder="护照号码"
                                    className="form-control border-input"
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group">
                            <label>护照有效期*</label>
                            {getFieldDecorator('date_expriy', {
                                rules: [{
                                    required: true,
                                    message: '请选择护照有效期'
                                }],
                            })(
                                <DatePicker
                                    disabledDate={current => current < moment().endOf('day')}
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group">
                            <label>出生日日期*</label>
                            {getFieldDecorator('date_birth', {
                                rules: [{required: true, message: '请选择出生日期'}],
                            })(
                                <DatePicker
                                    disabledDate={current => current > moment().endOf('day')}
                                />
                            )}
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group">
                            <label>身份证号*</label>
                            {getFieldDecorator('id_num', {
                                validateTrigger: 'onBlur',
                                rules: [{
                                    required: true,
                                    pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)/,
                                    message: '请输入身份证号',
                                }],
                            })(
                                <Input
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
                        <FormItem className="form-group">
                            <label>海外联系电话*</label>
                            {getFieldDecorator('o_tel', {
                                rules: [{
                                    required: true,
                                    message: '请输入海外联系电话'
                                }],
                            })(
                                <Input
                                    placeholder="海外联系电话"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group">
                            <label>中国联系电话1</label>
                            {getFieldDecorator('cn_tel')(
                                <Input
                                    placeholder="中国联系电话1"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group">
                            <label>中国联系电话2</label>
                            {getFieldDecorator('cn_te2')(
                                <Input
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
                        <FormItem className="form-group">
                            <label>微信</label>
                            {getFieldDecorator('wechat')(
                                <Input
                                    placeholder="微信"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group">
                            <label>邮箱</label>
                            {getFieldDecorator('mail', {
                                rules: [{
                                    type: 'email',
                                    required: false,
                                    message: '邮箱格式不正确'
                                }],
                            })(
                                <Input
                                    placeholder="邮箱"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group">
                            <label>QQ</label>
                            {getFieldDecorator('qq_num', {
                                rules: [{
                                    required: false,
                                    pattern: /[1-9][0-9]{4,14}/,
                                    message: 'qq格式不正确'
                                }]
                            })(
                                <Input
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
                        <FormItem className="form-group">
                            <label>现国籍*</label>
                            {getFieldDecorator('nationality', {
                                rules: [{
                                    required: true,
                                    pattern: /^[\u4e00-\u9fa5]+$/,
                                    message: '请输入国籍'
                                }]
                            })(
                                <Input
                                    placeholder="国籍"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group">
                            <label>旅居地*</label>
                            {getFieldDecorator('residence', {
                                rules: [{
                                    required: true,
                                    message: '请输入居旅地'
                                }]
                            })(
                                <Input
                                    placeholder="旅居地"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group">
                            <label>中国居住地*</label>
                            {getFieldDecorator('cn_residence', {
                                rules: [{
                                    required: true,
                                    pattern: /^[\u4e00-\u9fa5]+$/,
                                    message: '请输入中国居住地'
                                }]
                            })(
                                <Input
                                    placeholder="请输入中国居住地"
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
                        <FormItem className="form-group">
                            <label>所从事行业</label>
                            {getFieldDecorator('present_industry')(
                                <Input
                                    placeholder="所从事行业"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group">
                            <label>公司/单位名称</label>
                            {getFieldDecorator('com_name')(
                                <Input
                                    placeholder="公司/单位"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group">
                            <label>职务</label>
                            {getFieldDecorator('position')(
                                <Input
                                    placeholder="公司/单位"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-4">
                        <FormItem className="form-group">
                            <label>文化程度</label>
                            {getFieldDecorator('education')(
                                <Input
                                    placeholder="文化程度"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-4">
                        <FormItem className="form-group">
                            <label>健康状态</label>
                            {getFieldDecorator('health')(
                                <Input
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
                            <div className="form-group">
                                <label>毕业院校英文名*</label>
                                <input type="text"
                                       onChange={e => {
                                           this.setState({passport: e.target.value})
                                       }}
                                       className="form-control border-input"
                                       placeholder=""
                                />
                                <div/>
                            </div>
                        </div>
                        <div className="col-md-3">
                            <FormItem className="form-group">
                                <label>毕业院校中文名*</label>
                                {getFieldDecorator('degree', {
                                    rules: [{
                                        required: true,
                                        message: '请输入毕业院校'
                                    }],
                                })(
                                    <Input
                                        placeholder="毕业院校"
                                        className="form-control border-input"
                                    />
                                )}
                                <div/>
                            </FormItem>
                        </div>
                        <div className="col-md-3">
                            <div className="form-group">
                                <label>学位*</label>
                                <input type="text"
                                       onChange={e => {
                                           this.setState({passport: e.target.value})
                                       }}
                                       className="form-control border-input"
                                       placeholder=""
                                />
                                <div/>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="form-group">
                                <label>毕业时间*</label>
                                <DatePicker/>
                                <div/>
                            </div>
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
                                getUrl={this.getPhotoUrl}
                            />
                            <div/>
                        </div>
                    </div>
                </div>
                <hr/>
                <div className="text-center">
                    <button type="button"
                            className="btn btn-info btn-fill btn-wd"
                            onClick={e => {
                                this.update(e)
                            }}
                            disabled={loading}
                    >{loading &&
                    <i style={{marginRight: 5}} className="anticon anticon-spin anticon-loading"/>}
                        录入
                    </button>
                </div>
            </Form>
        )
    }
}

const Hq_lxContent = Form.create()(Hq_lxContentForm);
export default Hq_lxContent;

