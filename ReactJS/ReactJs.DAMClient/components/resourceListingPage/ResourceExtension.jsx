import React from 'react';

const ResourceExtension = ({extension}) => {
    return (
        <span className="extension badge position-absolute bottom-0 end-0 mb-2 me-2 text-uppercase">
            {extension}
        </span>
    );
};

export default ResourceExtension;
