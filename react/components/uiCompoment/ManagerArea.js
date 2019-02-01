import {zj_pc_code} from "../config/pca_code";
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
    // componentDidMount(props) {
    //     this.setState({value:props.value});
    // }

    componentWillReceiveProps(nextProps){
        this.setState({value:nextProps.value});
    }

    handleChange(value){
        const {onChange} = this.props
        if(value instanceof Array){
            value = value.join("/");
        }
        onChange(value);
    }

    render() {
        const {disabled} = this.props;
        const {value} = this.state;
        return (
            <Cascader
                disabled={disabled}
                changeOnSelect
                options={zj_pc_code}
                placeholder="所属侨联"
                value={value?value.split("/"):""}
                onChange={this.handleChange}
            />
        );
    }
}