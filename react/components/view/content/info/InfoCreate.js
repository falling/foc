import React from 'react';
import FormContent from "./formContent/FormContent";

export default class InfoCreate extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            type:'hq'
        };
        this.update = this.update.bind(this);

    }

    update() {

    }

    componentDidMount() {
        //get user info
    }

    componentWillReceiveProps(nextProps) {
    }

    render() {
        const {display,type} = this.props;
        let title;
        if(type === 'hq'){
            title = '华侨';
        }else if(type === 'lx'){
            title = '留学生';
        }else if(type === 'qj_hq'){
            title = '侨眷';
        }else{
            title = '留学生家属';
        }
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">{`录入${title}信息`}</h4>
                        </div>
                        <div className="content">
                            <FormContent
                                type={type}
                                mode= "add"
                            />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
