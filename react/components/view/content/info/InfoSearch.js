import React from 'react';
import {Input, Select, message, Table, Modal, Button} from 'antd';
import 'whatwg-fetch';
import FormContent from "./formContent/FormContent";

const Search = Input.Search;
const Option = Select.Option;
let columns;
let rowSelection;
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
            selectedRows: [],
            selectedRowKeys: [],
        };
        this.search = this.search.bind(this);
        this.showData = this.showData.bind(this);
        this.exportXlsx = this.exportXlsx.bind(this);
        columns = [{
            title: '姓名',
            dataIndex: 'ch_name',
            key: 'ch_name',
            width: '150px'
        }, {
            title: '护照号',
            dataIndex: 'passport_no',
            key: 'passport_no',
            width: '150px'
        }, {
            title: '性别',
            dataIndex: 'sex',
            key: 'sex',
            width: '150px'
        }, {
            width: '150px',
            title: '查看',
            key: 'action',
            render: (record) => (
                <span><a onClick={e => {
                    this.showData(record)
                }}>查看</a></span>
            ),
        }];
    }

    exportXlsx() {
        const {selectedRows,type} = this.state;
        this.setState({loading:true});
        fetch(`/export${type}`, {
            method: 'post',
            credentials: 'include',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(selectedRows)
        }).then(response => response.blob()
            .then(blob => {
                let a = document.createElement('a');
                let url = window.URL.createObjectURL(blob);
                a.href = url;
                a.download = `${new Date().toTimeString()}_${type}.xlsx`;
                a.click();
                window.URL.revokeObjectURL(url);
                this.setState({loading:false});
            }))
    }

    componentDidMount() {
    }

    componentWillReceiveProps(nextProps) {
    }

    showData(record) {
        let sData = {};
        sData.value = record;
        sData.relationList = record.relationList;
        delete sData.value.info;
        this.setState({
            previewVisible: true,
            showData: sData,
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
                    let data = [];
                    json.result.forEach(e => {
                        e.value.relationList = e.relationList;
                        data.push(e.value);
                    });
                    this.setState({
                        show: true,
                        data: data,
                        loading: false,
                        selectedRowKeys: [],
                        selectedRows: [],
                    })
                } else {
                    message.error(json.info, 5);
                }
            })

    }

    render() {
        const {display} = this.props;
        const {type, col, show, loading, previewVisible, showData, selectedRows, selectedRowKeys} = this.state;
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
                    <Option value="passport_no">护照号或身份证号码</Option>
                    <Option value="hq_id">华侨护照号</Option>
                    <Option value="lx_id">留学生护照号</Option>
                </Select>
        }
        let hasSelected = selectedRowKeys.length > 0;
        rowSelection = {
            onChange: (selectedRowKeys, selectedRows) => {
                this.setState({
                    selectedRowKeys: selectedRowKeys,
                    selectedRows: selectedRows,
                })
            },
            selectedRowKeys,
            selectedRows,
        };
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">信息查询</h4>
                        </div>
                        <div className="content">
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <Search addonBefore={
                                            <div>
                                                <Select onChange={value => {
                                                    this.setState({
                                                        type: value,
                                                        col: 'ch_name',
                                                        data: [],
                                                        selectedRowKeys: [],
                                                        selectedRows: [],
                                                    });

                                                }
                                                }
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
                                rowSelection={rowSelection}
                                columns={columns}
                                rowKey={`${type}_id`}
                                dataSource={this.state.data}
                                loading={loading}
                                scroll={{y: 240}}
                                footer={() =>
                                    <Button
                                        type="primary"
                                        onClick={this.exportXlsx}
                                        disabled={!hasSelected||loading}
                                        loading={loading}
                                    >
                                        导出
                                    </Button>}
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
                <Modal width="1080px" visible={previewVisible} footer={null}
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