import React from 'react';
import ReactDOM from 'react-dom';

export default class Manager extends React.Component {

    constructor() {
        super();
        this.state = {
            username: '',
            password: ''
        };
    }
    componentDidMount() {
    }

    render() {
        return (
            <div>{`login info:${localStorage.getItem("user")}`}</div>
        )
    }
}