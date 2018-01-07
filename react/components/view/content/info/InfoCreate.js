import React from 'react';

export default class InfoCreate extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
        };
        console.log("Info create Init")

    }

    componentDidMount() {
        //get user info
    }

    componentWillReceiveProps(nextProps) {
    }

    render(){
        const {display} =this.props;

        return(
            <div style={{display:!display&&'none'}}>CreateInfo</div>
        )
    }
}