import React from 'react';

export default class InfoManage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
        };
        console.log("Info manage Init")

    }

    componentDidMount() {
        //get user info
    }

    componentWillReceiveProps(nextProps) {
    }

    render(){
        const {display} =this.props;
        return(
            <div style={{display: !display&&'none'}}>
                InfoManager
            </div>
        )
    }
}