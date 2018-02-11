import {Table, Select, message,Input, Popconfirm} from 'antd';
import React from 'react';
import 'whatwg-fetch';

const Search = Input.Search;
const Option = Select.Option;

export default class RelationTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            loading:false,
            type:this.props.type==='qj'?'hq':'qj'
        };
        this.handleChange = this.handleChange.bind(this);
        this.onDelete = this.onDelete.bind(this);
        this.handleAdd = this.handleAdd.bind(this);
        this.columns = [{
            title: '姓名',
            dataIndex: 'ch_name',
            key: 'ch_name',
            width: '150px'
        }, {
            title: '护照号或身份证号',
            dataIndex: 'passport_no',
            key: 'passport_no',
            width: '150px'
        },{
            title:'类别',
            dataIndex:'type',
            key:'type',
            width:'150px'
        }, {
            title: '性别',
            dataIndex: 'sex',
            key: 'sex',
            width: '150px'
        }, {
            width: '150px',
            title: '关系',
            key: 'relation',
            render: (record) => (
                <Select
                    defaultValue={record.relation}
                    dropdownMatchSelectWidth={false}
                    onChange={value => this.handleChange(value, record.key)}
                >
                    <Option value="祖孙">祖孙</Option>
                    <Option value="外祖孙">外祖孙</Option>
                    <Option value="兄弟姐妹">兄弟姐妹</Option>
                    <Option value="父子">父子（女）</Option>
                    <Option value="母子">母子（女）</Option>
                    <Option value="夫妻">配偶</Option>
                </Select>
            ),
        }, {
            width: '150px',
            title: '删除',
            key: 'action',
            render: (record) => (
                <Popconfirm title="确定要删除？" onConfirm={() => this.onDelete(record.key)}>
                    <a>删除</a>
                </Popconfirm>
            ),
        }]

    }

    componentWillReceiveProps(nextProps){
        this.setState({data:nextProps.value,loading:nextProps.loading})
    }

    componentDidMount() {
    }


    onDelete(key) {
        const {data} = this.state;
        let newData = data.filter(item => item.key !== key)
        this.setState({data: newData});
        if (this.props.onChange) {
            this.props.onChange(newData);
        }
    }

    handleChange(value, key) {
        const {data} = this.state;
        const target = data.filter(item => key === item.key)[0];
        if (target) {
            target.relation = value;
            this.setState({data: data});
            if (this.props.onChange) {
                this.props.onChange(data);
            }
        }
    }

    handleAdd(value){
        const {type} = this.props;
        let formData = new FormData();
        formData.append("passport_no", value);
        formData.append("type", this.state.type);
        this.setState({loading:true})
        fetch('/loadByPassport',{
            method: 'post',
            credentials: 'include',
            body: formData
        }).then(response => response.json())
            .then(json => {
                if (json.status > 0) {
                    let result={};
                    const {data} = this.state;
                    if(type==='qj'){
                        result.o_id = json[`${this.state.type}_id`];
                    }else{
                        result.qj_id = json.qj_id;
                    }
                    if(this.state.type==='qj'){
                        result.type = '侨眷';
                    }else if(this.state.type==='lx'){
                        result.type = '留学';
                    }else{
                        result.type = '华侨';
                    }
                    if(data.filter(e=>e.o_id===result.o_id&&e.type===result.type)[0]){
                        message.error("不能重复添加",5);
                        this.setState({loading:false})
                        return;
                    }
                    result.ch_name = json.ch_name;
                    result.passport_no = json.passport_no;
                    result.sex = json.sex;
                    result.relation = '父子';

                    result.key = result.o_id+result.type;
                    data.push(result);
                    this.setState({data:data});
                    if (this.props.onChange) {
                        this.props.onChange(data);
                    }

                } else {
                    message.error(json.info, 5);
                }
                this.setState({loading:false})
            })
    }

    render() {
        const {loading,data} = this.state;
        const {type} = this.props;
        return (
            <div>
                <h5>家庭成员</h5>
                <Table
                    title={()=><Search
                        className="editable-add-btn"
                        onSearch={this.handleAdd}
                        enterButton
                        placeholder={type==='qj'?"请输入华侨或者留学生的护照号码":"请输入侨眷的护照号码或者身份证号码"}
                        addonBefore={
                            <Select onChange={value=>this.setState({type:value})}
                                    defaultValue={type==='qj'?'hq':'qj'}>
                                {type==='qj'&&<Option value="hq">华侨</Option>}
                                {type==='qj'&&<Option value="lx">留学</Option>}
                                {type==='lx'|| type==='hq'&&<Option value="qj">侨眷</Option>}
                            </Select>}
                    />}
                    dataSource={data}
                    columns={this.columns}
                    loading={loading}
                />
            </div>
        )
    }
}