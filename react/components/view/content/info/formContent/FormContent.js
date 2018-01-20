import React from 'react';
import Hq_lxContent from "./Hq_lxContent";
import QjContent from "./QjContent";

export default class FormContent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            pinyin: '',
            used_name: '',
            sex: '男',
            nationality: '',
            passport: '',
            passport_date: '',
        };
        this.update = this.update.bind(this);
        this.getContent = this.getContent.bind(this);
    }

    getContent(contentFormData) {

    }

    update() {

    }

    render() {
        const {type} = this.props;
        const {name, nationality, passport, passport_date} = this.state;

        return (
            <div>
                {(type === 'hq' || type === 'lx') &&
                <Hq_lxContent
                    type={type}
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