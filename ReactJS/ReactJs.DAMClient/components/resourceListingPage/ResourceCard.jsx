import React from 'react';
import PropTypes from 'prop-types';
import Divider from '../common/Divider';
import ResourceDownload from './ResourceDownload';
import ResourceExtension from './ResourceExtension';
import {
    RESOURCE_LISTING_CONSTANT,
    VIDEO_EXTENSIONS,
} from '../../constants/resourceListing/ResourceListingConstant';

const ResourceCard = ({
    id,
    name,
    thumbnail,
    extension,
    handleSelectFile,
    handleClickFile,
    itemsDownloading,
    setItemsDownloading,
    setMessage,
    setShowAlert,
    setDownloadFailed
}) => {
    return (
        <div className="file-card card border-1 border-solid">
            <div className="file-card-body d-flex flex-column">
                <div
                    className="file-card-image-container position-relative d-flex flex-column justify-content-center align-items-center mb-4"
                    onClick={() => handleClickFile(id)}
                >
                    <img
                        src={thumbnail}
                        alt=""
                        className="file-image img-fluid"
                    />
                    {extension && VIDEO_EXTENSIONS.includes(extension) ? (
                        <img
                            src={RESOURCE_LISTING_CONSTANT.play02}
                            alt="Play"
                            className="position-absolute top-50 start-50 translate-middle"
                        />
                    ) : null}
                    {extension ? (
                        <ResourceExtension extension={extension} />
                    ) : null}
                </div>
                <Divider />
                <div className="h-100 d-flex flex-column justify-content-center">
                    <p
                        className="h-100 file-card-title card-title mb-4 overflow-hidden"
                        onClick={() => handleClickFile(id)}
                    >
                        {name}
                    </p>
                    <div className="d-flex flex-row justify-content-between align-items-center">
                        {extension && extension !== '' ? (
                            <>
                                <div className="form-check mb-0">
                                    <input
                                        onChange={() => handleSelectFile(id)}
                                        className="file-select form-check-input"
                                        type="checkbox"
                                        value=""
                                        id={id}
                                    />
                                    <label
                                        className="form-check-label"
                                        htmlFor={id}
                                    >
                                        Select
                                    </label>
                                </div>
                                <ResourceDownload
                                    id={id}
                                    itemsDownloading={itemsDownloading}
                                    setItemsDownloading={setItemsDownloading}
                                    setMessage={setMessage}
                                    setShowAlert={setShowAlert}
                                    setDownloadFailed={setDownloadFailed}
                                />
                            </>
                        ) : null}
                    </div>
                </div>
            </div>
        </div>
    );
};

ResourceCard.propTypes = {
    id : PropTypes.number,
    name : PropTypes.string,
    thumbnail : PropTypes.string,
    extension : PropTypes.string,
    handleSelectFile : PropTypes.func,
    handleClickFile : PropTypes.func,
    itemsDownloading : PropTypes.array,
    setItemsDownloading : PropTypes.func,
    setMessage : PropTypes.func,
    setShowAlert : PropTypes.func,
    setDownloadFailed : PropTypes.func,
}

export default ResourceCard;
