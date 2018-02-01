import React from 'react';
import {Input, Select, message, Table, Modal} from 'antd';
import 'whatwg-fetch';
import FormContent from "./formContent/FormContent";

const Search = Input.Search;
const Option = Select.Option;
let columns;

export default class InfoSearch extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            col: 'ch_name',
            type: 'hq',
            show: false,
            data: [],
            loading: false,
            previewVisible: false,
            showData: {},
        };
        this.search = this.search.bind(this);
        this.showData = this.showData.bind(this);

        columns = [{
            title: '姓名',
            dataIndex: 'ch_name',
            key: 'ch_name',
            fixed: 'true'
        }, {
            title: '护照号',
            dataIndex: 'passport_no',
            key: 'passport_no',
            fixed: 'true',
        }, {
            title: '性别',
            dataIndex: 'sex',
            key: 'sex',
            fixed: 'true',
        }, {
            title: '查看',
            key: 'action',
            render: (record) => (
                <span><a onClick={e => {
                    this.showData(record)
                }}>查看</a></span>
            ),
        }];
    }

    componentDidMount() {
        //get user info
    }

    componentWillReceiveProps(nextProps) {
    }

    showData(record) {
        console.log(record)
        this.setState({
            previewVisible: true,
            showData: record,
        })
    }

    search(value) {
        if (!value) return;
        const {type, col} = this.state;

        let formData = new FormData();
        formData.append("value", value);
        formData.append("type", type);
        formData.append("col", col);
        this.setState({loading: true});
        fetch('/searchTable', {
            method: 'post',
            credentials: 'include',
            body: formData
        }).then(response => response.json())
            .then(json => {
                if (json.status > 0) {
                    this.setState({
                        show: true,
                        data: json.result,
                        loading: false,
                    })
                } else {
                    message.error(json.info, 5);
                }
            })

    }

    render() {
        const {display} = this.props;
        const {type, col, show, loading, previewVisible, showData} = this.state;
        let secondSelect;
        if (type === 'hq') {
            secondSelect =
                <Select
                    style={{paddingLeft: 20}}
                    dropdownMatchSelectWidth={false}
                    onChange={value => this.setState({col: value})}
                    value={col}
                >
                    <Option value="ch_name">中文姓名</Option>
                    <Option value="py_name">拼音</Option>
                    <Option value="passport_no">护照号</Option>
                    <Option value="nationality">国籍</Option>
                    <Option value="native_place">籍贯</Option>
                    <Option value="residence">旅居地</Option>
                </Select>
        } else {
            secondSelect =
                <Select
                    style={{paddingLeft: 20}}
                    dropdownMatchSelectWidth={false}
                    onChange={value => this.setState({col: value})}
                    value={col}>
                    <Option value="ch_name">中文姓名</Option>
                    <Option value="py_name">拼音</Option>
                    <Option value="passport_no">护照号</Option>
                    <Option value="nationality">国籍</Option>
                    <Option value="native_place">籍贯</Option>
                    <Option value="ch_cname">学校中文名</Option>
                    <Option value="en_cname">学校英文名</Option>
                </Select>
        }

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
                                        <Search addonBefore={
                                            <div>
                                                <Select onChange={value => this.setState({type: value, col: 'ch_name'})}
                                                        defaultValue="hq">
                                                    <Option value="hq">华侨</Option>
                                                    <Option value="lx">留学</Option>
                                                    <Option value="qj">侨眷</Option>
                                                </Select>
                                                {secondSelect}
                                            </div>
                                        }
                                                onSearch={value => this.search(value)}
                                                enterButton
                                        />
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            {show &&
                            <Table
                                columns={columns}
                                rowKey={`${type}_id`}
                                dataSource={this.state.data}
                                loading={loading}
                                pagination={{
                                    total: this.state.data.count,
                                    pageSize: 50,
                                    pageSizeOptions: ['10', '50', '100', '200', '500', '1000'],
                                    showSizeChanger: true,
                                    showTotal: e => '共 ' + this.state.data.length + ' 条数据'
                                }}
                            />
                            }
                        </div>
                    </div>
                </div>
                <Modal width="720px" visible={previewVisible} footer={null}
                       onCancel={e => this.setState({previewVisible: false})}>
                    {previewVisible &&
                        <FormContent
                            type={type}
                            info={showData}
                            mode="search"
                        />
                    }
                </Modal>
            </div>
        )
    }
}