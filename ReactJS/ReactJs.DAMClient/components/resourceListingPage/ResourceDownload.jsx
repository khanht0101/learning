import React, { useState } from 'react';
import PropTypes from 'prop-types';
import ResourceListingApi from 'apis/ResourceListingApi';
import { RESOURCE_LISTING_CONSTANT } from '../../constants/resourceListing/ResourceListingConstant';
import Spinner from '../common/Spinner';

const ResourceDownload = ({
    id,
    itemsDownloading,
    setItemsDownloading,
    setMessage,
    setShowAlert,
    setDownloadFailed,
}) => {
    const [currentDownloading, setCurrentDownloading] = useState(false);

    function handleDownloadClick(resourceId) {
        setCurrentDownloading(true);
        setItemsDownloading([...itemsDownloading, resourceId]);
        ResourceListingApi.startDownloadFile(resourceId)
            .catch(() => {
                setMessage({
                    status: 'failed',
                    message: 'An error occurred downloading the file',
                });
                setDownloadFailed(true);
                setShowAlert(true);
            })
            .finally(() => {
                setCurrentDownloading(false);
                if (itemsDownloading) {
                    setItemsDownloading(
                        itemsDownloading.filter((item) => item !== resourceId)
                    );
                }
            });
    }

    if (currentDownloading) return <Spinner isSmall={true} />;

    return (
        <button
            disabled={itemsDownloading.includes(id)}
            type="button"
            className="btn btn-link p-0"
            onClick={() => handleDownloadClick(id)}
        >
            <img
                src={RESOURCE_LISTING_CONSTANT.download}
                alt="download"
                width="24"
                height="24"
            />
        </button>
    );
};

ResourceDownload.propTypes = {
    id: PropTypes.number,
    itemsDownloading: PropTypes.array,
    setItemsDownloading: PropTypes.func,
    setMessage: PropTypes.func,
    setShowAlert: PropTypes.func,
    setDownloadFailed: PropTypes.func,
};

export default ResourceDownload;
