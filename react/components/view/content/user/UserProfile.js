import React from 'react';

export default class UserProfile extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
        };
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
                UserProfile
            </div>
        )
    }
}