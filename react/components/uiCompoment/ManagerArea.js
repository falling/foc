import {pc_code} from "../config/pca_code";
import {Cascader} from 'antd';
import React from 'react';

export default class ManagerArea extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value: ""
        };
        this.handleChange = this.handleChange.bind(this);
    }

    componentWillMount() {
        let user = sessionStorage.getItem("user");
        if (user) {
            this.area = JSON.parse(user).manager_area;
            let arr = this.area.split("/");
            if (arr[0]){
                this.pc_code_manager = pc_code.filter(e=>e.name===arr[0]);
            }
            if (arr[1]){
                this.pc_code_manager[0].children = this.pc_code_manager[0].children.filter(e=>e.name===arr[1]);
            }
            if (arr[2]){
                this.pc_code_manager[0].children[0].children = this.pc_code_manager[0].children[0].children.filter(e=>e.name===arr[2]);
            }
        }
    }

    componentWillReceiveProps(nextProps){
        this.setState({value:nextProps.value});
    }

    handleChange(value){
        const {onChange} = this.props
        if(value instanceof Array){
            value = value.join("/");
        }
        if (value.startsWith(this.area)){
            onChange(value);
        }else {
            onChange(this.area)
        }
    }
    render() {
        const {disabled} = this.props;
        const {value} = this.state;
        return (
            <Cascader
                disabled={disabled}
                changeOnSelect
                fieldNames={{label: 'name', value: 'name'}}
                options={this.pc_code_manager}
                expandTrigger="hover"
                placeholder="所属侨联"
                value={value?value.split("/"):""}
                onChange={this.handleChange}
            />
        );
    }
}