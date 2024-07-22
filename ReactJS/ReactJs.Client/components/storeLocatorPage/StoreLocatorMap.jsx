import React from 'react';
import PropTypes from 'prop-types';
import Checkbox from '../common/Checkbox';

export default class StoreLocatorMap extends React.Component {
  constructor(props) {
    super(props);

    this.onKeyPress = this.onKeyPress.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  onKeyPress(e) {
    if (e && e.key === 'Enter') {
      this.props.onSubmitValue();
    }
  }

  handleChange(e){
    if(e.currentTarget.value===''){
      this.props.handleRemove();
    }
  }

  render() {
    return (
      <React.Fragment>
          <div className="find-store__submit">
            <input onChange={this.handleChange} onKeyPress={this.onKeyPress} type="text" id="search-box" placeholder="Enter your postcode or suburb" className="search-bar__stockists" required autoComplete="off" />
            <span id="searchclear" className="icon icon-close" onClick={this.props.handleRemove}></span>
            <button className="find-store__btn"><a onClick={() => this.props.onSubmitValue()}>SEARCH</a></button>
          </div>
        <div className="filter-container">
          <span className="find-store__filter">Filter</span>
          {this.props.categories.map(item => (
          <div className="filter-option" key={item.category}>
            <Checkbox id="categories" value={item.category} displayValue={item.category} onChecked={this.props.onFilter} />
          </div>  
          ))}
        </div>
        <p><a className="international-dealer" href={this.props.internationalLink} alt={this.props.internationalText}>{this.props.internationalText}</a></p>
      </React.Fragment>
    );
  }
}

StoreLocatorMap.propTypes = {
  onSubmitValue: PropTypes.func,
  internationalText: PropTypes.string,
  internationalLink: PropTypes.string
};