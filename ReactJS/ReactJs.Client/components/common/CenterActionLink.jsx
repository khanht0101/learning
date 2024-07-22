import React from 'react';

class CenterActionLink extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="page__view-all cmp-btn">
        <a type="button" className="btn" href="#" role="button" onClick={this.props.onClick}>
          <span className="button__text">{this.props.title} </span>          
        </a>
      </div>
    );
  }
}

export default CenterActionLink;