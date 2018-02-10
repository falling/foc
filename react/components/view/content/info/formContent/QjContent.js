import React from 'react';
import 'whatwg-fetch';
import {Form, Input, Select,message} from 'antd';
import ReferenceTable from "../../../../uiCompoment/ReferenceTable";

const FormItem = Form.Item;
const Option = Select.Option;
const Search = Input.Search;

class QjContentForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: false,
            deleteLoading: false,
        };
        this.relation = [];
        this.add = this.add.bind(this);
        this.update = this.update.bind(this);
    }

    add(){
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                console.log(this.relation);
                console.log(values);
                this.setState({loading: true});
                let postData={};
                postData.value = values;
                postData.relationList = values.relation;
                delete postData.value["relation"];
                console.log(JSON.stringify(postData));
                fetch('/addQjInfo', {
                    method: 'post',
                    credentials: 'include',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(postData)
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
    update(){

        this.props.getContent();
    }
    render(){
        const {loading,deleteLoading} = this.state;
        const {mode} = this.props;
        const {getFieldDecorator} = this.props.form
        return(
            <Form>
                {getFieldDecorator('qj_id')(
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
                                }, {max: 10, message: '长度最长为10'}],
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
                        <FormItem className="form-group" label="身份证号或护照号">
                            {getFieldDecorator('id_num', {
                                initialValue: '',
                                validateTrigger: 'onBlur',
                                rules: [{
                                    required: true,
                                    message: '身份证号或护照号',
                                }],
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
                                rules: [{
                                    required: true,
                                    pattern: /^[\u4e00-\u9fa5]+$/,
                                    message: '请输入民族'
                                }],
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
                                initialValue: '男',
                                rules: [{
                                    required: true,
                                }],
                            })(
                                <Select disabled={mode === 'search'}>
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
                        <FormItem className="form-group" label="联系电话">
                            {getFieldDecorator('tel1', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="联系电话1"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                    <div className="col-md-3">
                        <FormItem className="form-group" label="海外联系电话">
                            {getFieldDecorator('tel2', {
                                initialValue: '',
                            })(
                                <Input
                                    disabled={mode === 'search'}
                                    placeholder="联系电话2"
                                    className="form-control border-input"
                                />
                            )}
                            <div/>
                        </FormItem>
                    </div>
                </div>
                <hr/>
                <div>
                    <FormItem className="form-group" label="海外联系电话">
                        {getFieldDecorator('relation', {
                            initialValue: [],
                        })(
                            <ReferenceTable
                                type='qj'
                            />
                        )}
                        <div/>
                    </FormItem>

                </div>
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