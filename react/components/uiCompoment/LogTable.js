import {Table, Modal, message, Input} from 'antd';
import React from 'react';
import 'whatwg-fetch';
import {KeyNameMap} from "../config/KeyNameMap"

const {TextArea} = Input;


export default class LogTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            loading: false,
            previewVisible: false,
            content: '',
        };
        this.showData = this.showData.bind(this);
        this.getAddStringLog = this.getAddStringLog.bind(this);
        this.getModifyStringLog = this.getModifyStringLog.bind(this);
        LogTable.formatDate = LogTable.formatDate.bind(this);
    }

    static formatDate(timestamp) {
        let date = new Date(timestamp);
        let y = date.getFullYear();
        let m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        let h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        let minute = date.getMinutes();
        minute = minute < 10 ? ('0' + minute) : minute;
        let second = date.getSeconds();
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    };

    componentWillReceiveProps(nextProps) {
        if (nextProps.o_id && nextProps.type && nextProps.fresh!==this.props.fresh) {
            this.setState({loading: true});
            const {o_id, type} = nextProps;
            let formData = new FormData();
            formData.append("o_id", o_id);
            formData.append("type", type);
            fetch("/loadLog", {
                method: 'post',
                credentials: 'include',
                body: formData
            }).then(response => response.json())
                .then(json => {
                    if (json.status < 0) {
                        message.error(json.info, 5);
                        this.setState({loading: false});
                        return;
                    }
                    let data = [];
                    json.forEach(log => {
                        log.log_date = LogTable.formatDate(log.log_date);
                        data.push(log);
                    });
                    this.setState({loading: false, data: data});
                });
        }else if(!nextProps.o_id){
            this.setState({data:[]});
        }
    }

    componentDidMount() {
        this.columns = [{
            title: '操作人',
            dataIndex: 'operating_user',
            key: 'operating_user',
            width: '150px'
        }, {
            title: '操作时间',
            dataIndex: 'log_date',
            key: 'date',
            width: '150px'
        }, {
            title: '操作类型',
            dataIndex: 'operating',
            key: 'operation',
            width: '150px'
        }, {
            title: '操作内容',
            key: 'content',
            width: '150px',
            render: (record) => (
                <span><a onClick={e => {
                    this.showData(record)
                }}>查看</a></span>
            )
        }];

    }

    showData(record) {
        let content = '';
        if (record.operating === "添加") {
            content = `创建者：${record.operating_user}\n${this.getAddStringLog(record.new_value)}`
        } else if (record.operating === '修改') {
            content = `修改者：${record.operating_user}\n${this.getModifyStringLog(record.old_value,record.new_value)}`
        }
        this.setState({content: content, previewVisible: true});
    }

    getAddStringLog(value) {
        let tmp = '';
        let arr = value.split('$$');
        let relation = arr[arr.length-1];
        delete arr[arr.length-1];
        let bean = arr.join('');

        let obj = JSON.parse(bean);
        let objKey = Object.keys(obj);
        objKey.forEach(key => {
            if (KeyNameMap[key]) {
                tmp += `${KeyNameMap[key]}：${obj[key]}\n`
            }
        });
        tmp += '家庭信息：\n';
        relation = JSON.parse(relation);
        relation.forEach(e=>{
            tmp += `${e.relation}：${e.ch_name}\n`;
        });
        return tmp;
    }

    getModifyStringLog(old_Value, new_Value) {
        let tmp = '';
        let oldArr = old_Value.split('$$');
        let old_relation = oldArr[oldArr.length-1];
        delete oldArr[oldArr.length-1];
        let old_bean = oldArr.join('');

        let newArr = new_Value.split('$$');
        let new_relation = newArr[newArr.length-1];
        delete newArr[newArr.length-1];
        let new_bean = newArr.join('');

        let oldValue = JSON.parse(old_bean);
        let newValue = JSON.parse(new_bean);
        let arr = Object.keys(oldValue);
        arr.forEach(key => {
            if (oldValue[key] !== newValue[key]
                && KeyNameMap[key]) {
                tmp += `将 ${KeyNameMap[key]} 字段的值从 ${oldValue[key]} 改为 ${newValue[key]}\n`
            }
        });
        old_relation = JSON.parse(old_relation);
        new_relation = JSON.parse(new_relation);
        old_relation.forEach(e=>{
            e.id =null;
        });
        new_relation.forEach(e=>{
            e.id= null;
        });
        if(JSON.stringify(old_relation)!==JSON.stringify(new_relation)){
            tmp += '将原家庭信息：\n';
            old_relation.forEach(e=>{
                tmp += `${e.relation}：${e.ch_name}\n`;
            });
            tmp += '改为：\n';
            new_relation.forEach(e=>{
                tmp += `${e.relation}：${e.ch_name}\n`;
            });
        }

        return tmp;
    }


    render() {
        const {loading, data, previewVisible, content} = this.state;
        return (
            <div>
                <Table
                    dataSource={data}
                    columns={this.columns}
                    scroll={{y: 240}}
                    loading={loading}
                    rowKey="log_id"
                />
                <Modal width="720px" visible={previewVisible} footer={null}
                       onCancel={e => this.setState({previewVisible: false})}>
                    <TextArea
                        style={{marginTop: 30}}
                        disabled
                        autosize={{minRows: 2, maxRows: 15}}
                        value={content}
                    />
                </Modal>
            </div>

        )
    }
}
