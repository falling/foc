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
        const {display} = this.props;
        const {type} = this.state;
        return (
            <div className="container-fluid" style={{display: !display && 'none'}}>
                <div className="col-lg-12 col-md-12">
                    <div className="card">
                        <div className="header">
                            <h4 className="title">录入信息</h4>
                        </div>
                        <div className="content">
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group">
                                        <label>类别</label>
                                        <select
                                            onChange={e => {
                                                this.setState({type: e.target.value})
                                            }}
                                            className="form-control border-input">
                                            <option value="hq">华侨华人</option>
                                            <option value="lx">留学人员</option>
                                            <option value="qj_hq">归侨侨眷</option>
                                            <option value="qj_lx">留学生家属</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <hr/>
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
