import React, { Component } from 'react';

class Checkbox extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        const {className, value, onChecked, displayValue} = this.props;
        return (
            <React.Fragment>
                <label className='checkbox-color'>
                    <input type="checkbox" className={className} id={value} name={value} value={value} onChange={(event) => onChecked(value, event)} />
                    <span htmlFor={value} > {displayValue}</span>
                </label>
            </React.Fragment>
        );
    }
}

export default Checkbox;