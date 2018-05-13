import React from 'react';
import Hq_lxContent from "./Hq_lxContent";
import QjContent from "./QjContent";

export default class FormContent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
        };
        this.fresh = 1;
    }
    componentWillReceiveProps(nextProps) {

    }

    render() {
        const {type,info,mode} = this.props;
        const {} = this.state;
        this.fresh +=1;
        return (
            <div>
                {(type === 'hq' || type === 'lx') &&
                <Hq_lxContent
                    fresh={this.fresh}
                    info={info}
                    type={type}
                    mode={mode}
                />
                }

                {type === 'qj_hq'&&
                <QjContent
                    fresh={this.fresh}
                    mode={mode}
                    info={info}
                    type='qj_hq'
                />
                }

                {type === 'qj_lx'&&
                <QjContent
                    fresh={this.fresh}
                    mode={mode}
                    info={info}
                    type='qj_lx'
                />
                }
            </div>
        )
    }

}