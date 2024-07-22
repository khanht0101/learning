import React from 'react';
import { GEO_JSON } from "constants/EntityTypes";
import { STRING_RESOURCES } from "constants/AppConstants";

class StoreLocatorList extends React.Component {
  constructor(props) {
    super(props);
  }

  renderResultItems() {
    return this.props.item.features.map((item, index) => {
      return (
        <div className="find-store__store find-store-stockist__store marker-link" key={index} data-markerid={index} >
          <h4 className="find-store__store-name">
            {item.properties.name}
          </h4>
          <ul className="find-store__store-address">
            <li className="is-flex align-items-center"><i className="icon icon-location-solid "></i>{item.properties.address.formattedAddress}</li>
            {item.properties.address.phone &&
              <li className="is-flex align-items-center">
                <i className="icon icon-phone"></i>
                <a href={`tel:${item.properties.address.phone}`}>{item.properties.address.phone}</a>
              </li>
            }
            <li>
              <i className="icon icon-compass2"></i>
              {item.properties.distanceFromOrigin} km
              </li>
          </ul>
        </div>);
    });
  }

  render() {
    if (Object.keys(this.props.item).length !==0 && this.props.item.features.length > 0) {
      return (<div id="map-search-results" className="map-search-results">{this.renderResultItems()}</div>);
    }

    return (
      <div id="map-search-results" className="find-store__stores find-store__stores--empty text-center map-search-results">
        {STRING_RESOURCES.noStoresToDisplay}
      </div>
    );
  }
}

StoreLocatorList.propTypes = {
  item: GEO_JSON
};

export default StoreLocatorList;