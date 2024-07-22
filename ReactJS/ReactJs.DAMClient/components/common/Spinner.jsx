import React from 'react';
import { RESOURCE_LISTING_CONSTANT } from '../../constants/resourceListing/ResourceListingConstant';

export default function Spinner({ isSmall = false, justify = 'center' }) {
    return (
        <div className={`w-100 d-flex align-items-center ${isSmall ? "" : "my-4"} justify-content-${isSmall && justify === 'center' ? 'end' : justify}`}>
            <img
                className="loader"
                src={RESOURCE_LISTING_CONSTANT.loader}
                alt="loading"
                width={isSmall ? 24 : 48}
                height={isSmall ? 24 : 48}
            />
        </div>
    );
}
