import React from 'react';
import PropTypes from 'prop-types';
import { RESOURCE_LISTING_CONSTANT } from '../../constants/resourceListing/ResourceListingConstant';

const Info = ({ isSmall = false, justify = 'center' }) => {
    return (
        <div className={`w-100 d-flex align-items-center ${isSmall ? "" : "my-4"} justify-content-${isSmall && justify === 'center' ? 'end' : justify}`}>
            <img
                className="info"
                src={RESOURCE_LISTING_CONSTANT.info}
                alt="info"
                width={isSmall ? 24 : 48}
                height={isSmall ? 24 : 48}
            />
        </div>
    )
}

Info.propTypes = {
    isSmall: PropTypes.bool,
    justify: PropTypes.string,
}

export default Info;