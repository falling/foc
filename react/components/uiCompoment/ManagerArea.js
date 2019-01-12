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

    componentWillReceiveProps(nextProps){
        this.setState({value:nextProps.value});
    }

    handleChange(value){
        const {onChange} = this.props
        let v;
        if(value instanceof Array){
            v = value.join("/");
        }
        onChange(v);
    }

    render() {
        const {value} = this.state;
        return (
            <Cascader
                // disabled={mode === 'search'}
                options={zj_pc_code}
                placeholder="所属侨联"
                value={value.split("/")}
                onChange={this.handleChange}
            />
        );
    }
}