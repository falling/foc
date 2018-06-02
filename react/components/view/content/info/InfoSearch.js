import React from 'react';
import {Input, Select, message, Table, Modal, Button} from 'antd';
import 'whatwg-fetch';
import FormContent from "./formContent/FormContent";
import LogTable from "../../../uiCompoment/LogTable";

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
            show: true,
            data: [],
            loading: false,
            previewVisible: false,
            showData: {},
            selectedRows: [],
            selectedRowKeys: [],
            hqNumber: 0,
            lxNumber: 0,
            countryNumber: 0,
        };
        this.search = this.search.bind(this);
        this.showData = this.showData.bind(this);
        this.exportXlsx = this.exportXlsx.bind(this);
        this.getStatistics = this.getStatistics.bind(this);
        this.searchValue = '';

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
        const {selectedRows, type} = this.state;
        this.setState({loading: true});
        fetch(`/export${type}`, {
            method: 'post',
            credentials: 'include',
            headers: {'Content-Type': 'application/json;charset=utf-8'},
            body: JSON.stringify(selectedRows)
        }).then(response => response.blob()
            .then(blob => {
                let a = document.createElement('a');
                let url = window.URL.createObjectURL(blob);
                a.href = url;
                a.download = `${LogTable.formatDate(new Date().getTime())}_${type}.xlsx`;
                a.click();
                window.URL.revokeObjectURL(url);
                this.setState({loading: false});
            }))
    }

    componentDidMount() {
    }

    componentWillReceiveProps(nextProps) {
        //get number
        this.getStatistics();
        if(nextProps.type){
            this.setState({
                type:nextProps.type,
                col: 'ch_name',
                data: [],
                selectedRowKeys: [],
                selectedRows: [],
            });
        }
    }

    getStatistics() {
        fetch('/statistics', {
            method: 'post',
            credentials: 'include',
        }).then(response => response.json())
            .then(json => {
                this.setState({
                    hqNumber: json[0],
                    lxNumber: json[1],
                    countryNumber: json[2],
                });
            })
    }

    showData(record) {
        delete record.info;
        this.setState({
            previewVisible: true,
            showData: record,
        })
    }

    search(value) {
        if (!value) return;
        this.searchValue = value;
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
                    let data = json.result;
                    this.setState({
                        show: true,
                        data: data,
                        loading: false,
                        selectedRowKeys: [],
                        selectedRows: [],
                    })
                } else {
                    message.error(json.info, 5);
                    this.setState({
                        loading: false
                    })
                }
            })

    }

    render() {
        const {display, user} = this.props;
        const {type, col, show, loading, previewVisible, showData, selectedRows, selectedRowKeys, hqNumber, lxNumber, countryNumber} = this.state;
        let selectHTML;
        let titleName;
        if (type === 'hq') {
            titleName = "华侨";
            selectHTML =
                <Select
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
        } else if (type === 'lx') {
            titleName = "留学生";
            selectHTML =
                <Select
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
        } else {
            if (type==='qj_hq'){
                titleName = "侨眷";
            }else{
                titleName = "留学生家属";
            }

            selectHTML =
                <Select
                    dropdownMatchSelectWidth={false}
                    onChange={value => this.setState({col: value})}
                    value={col}>
                    <Option value="ch_name">中文姓名</Option>
                    <Option value="passport_no">护照号</Option>
                    <Option value="id_num">身份证号码</Option>
                    <Option value="o_passport">海外直系亲属护照号</Option>
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
                            <h4 className="title">{`${titleName}信息查询`}</h4>
                        </div>
                        <div className="content">
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <Search addonBefore={
                                            <div>
                                                {selectHTML}
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
                                    <div style={{lineHeight: '32px'}}>
                                        <Button
                                            type="primary"
                                            onClick={this.exportXlsx}
                                            disabled={!hasSelected || loading}
                                            loading={loading}
                                        >
                                            导出
                                        </Button>
                                        <div className='pull-right'>
                                            {`华侨总人数：${hqNumber}，留学总人数：${lxNumber}，覆盖的国家数：${countryNumber}`}
                                        </div>
                                    </div>}
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
                       onCancel={() =>
                           this.setState({previewVisible: false})
                       }
                       afterClose={() =>
                           this.search(this.searchValue)
                       }
                >
                    {previewVisible &&
                    <FormContent
                        type={type}
                        info={showData}
                        mode={user.power === 'user' ? 'search' : 'view'}
                    />
                    }
                </Modal>
            </div>
        )
    }
}