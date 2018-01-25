import React from 'react';
import Hq_lxContent from "./Hq_lxContent";
import QjContent from "./QjContent";

export default class FormContent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
        };
        this.fresh = 1;
        this.update = this.update.bind(this);
        this.getContent = this.getContent.bind(this);
    }

    getContent(contentFormData) {

    }

    update() {

    }
    componentWillReceiveProps(nextProps) {

    }

    render() {
        const {type,info} = this.props;
        const {} = this.state;
        this.fresh +=1;
        return (
            <div>
                {(type === 'hq' || type === 'lx') &&
                <Hq_lxContent
                    fresh={this.fresh}
                    info={info}
                    type={type}
                    mode={info?'view':'add'}
                    getContent={this.getContent}
                />
                }

                {type === 'qj' &&
                <QjContent
                    getContent = {this.getContent}
                />
                }

            </div>
        )
    }

}