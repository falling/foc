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
            <div style={{display: !display && 'none'}}>
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
                                            <option value="hq">华侨</option>
                                            <option value="lx">留学</option>
                                            <option value="qj">侨眷</option>
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
