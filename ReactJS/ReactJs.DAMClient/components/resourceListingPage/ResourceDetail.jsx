import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import ResourceListingApi from '../../apis/ResourceListingApi';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Divider from '../common/Divider';
import ReactPlayer from 'react-player';
import parse from 'html-react-parser';
import {
    RESOURCE_LISTING_CONSTANT,
    VIDEO_EXTENSIONS,
} from '../../constants/resourceListing/ResourceListingConstant';
import Spinner from '../common/Spinner';

const ResourceDetail = ({
    id,
    show,
    handleClose,
    itemsDownloading,
    setItemsDownloading,
    setMessage,
    setShowAlert,
    setDownloadFailed,
}) => {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);
    const [isPlaying, setIsPlaying] = useState(false);
    const [isMobile, setIsMobile] = useState(false);
    const [currentDownloading, setCurrentDownloading] = useState(false);

    const handlePlayButtonClick = () => {
        setIsPlaying(true);
    };

    const handleDownloadClick = () => {
        if (!id) return;

        setCurrentDownloading(true);
        setItemsDownloading([...itemsDownloading, id]);
        ResourceListingApi.startDownloadFile(id)
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
                setItemsDownloading(
                    itemsDownloading.filter((item) => item !== id)
                );
            });
    };

    useEffect(() => {
        const mediaQuery = window.matchMedia('(max-width: 768px)');

        setIsMobile(mediaQuery.matches);

        const handleResize = (e) => setIsMobile(e.matches);

        mediaQuery.addEventListener('change', handleResize);

        return () => {
            mediaQuery.removeEventListener('change', handleResize);
        };
    }, []);

    useEffect(() => {
        if (!id) return;

        ResourceListingApi.getResourceDetail(id)
            .then((response) => {
                if (response.status !== 200) return;
                setData(response.data);
            })
            .catch((err) => {
                setError(err);
            });
    }, [id]);

    if (!data) return null;

    return (
        <div id="resourceDetail">
            <Modal
                show={show}
                onHide={handleClose}
                fullscreen={isMobile}
                dialogClassName="file-preview-modal"
            >
                <Modal.Header>
                    <Modal.Title>
                        <p className="file-preview-modal-title">{data.name}</p>
                    </Modal.Title>
                    <Button
                        variant="link"
                        onClick={handleClose}
                        aria-label="Close"
                    >
                        <img src="/assets/images/icons/close.svg" alt="test" />
                    </Button>
                </Modal.Header>
                <Modal.Body>
                    {error ? (
                        <p>Failed to load detail data.</p>
                    ) : (
                        <>
                            <div
                                className="file-preview-modal__preview"
                                onContextMenu={(e) => e.preventDefault()}
                            >
                                {VIDEO_EXTENSIONS.includes(data.extension) ? (
                                    <ReactPlayer
                                        url={data.mediaLink}
                                        playing={isPlaying}
                                        controls
                                        width="100%"
                                        height="100%"
                                        light={false}
                                        config={{
                                            file: {
                                                attributes: {
                                                    controlsList: 'nodownload',
                                                    preload: 'auto',
                                                    poster: data.thumbnail,
                                                },
                                            },
                                        }}
                                        playIcon={
                                            <button
                                                className="btn btn-link"
                                                onClick={handlePlayButtonClick}
                                            >
                                                <img
                                                    src={
                                                        RESOURCE_LISTING_CONSTANT.play
                                                    }
                                                    alt="play"
                                                />
                                            </button>
                                        }
                                    />
                                ) : (
                                    <img src={data.thumbnail} alt={data.name} />
                                )}
                            </div>
                            <div className="file-preview-modal__details">
                                <div className="file-preview-modal__details__header">
                                    <p className="file-preview-modal-title mb-1">
                                        File details
                                    </p>
                                    <p>
                                        Please refer to our{' '}
                                        <a
                                            target="_blank"
                                            href="/branding-portal-site-terms/"
                                        >
                                            Terms and Conditions
                                        </a>
                                    </p>
                                    <p>
                                        File format: {data.extension.toUpperCase()}
                                    </p>
                                    <p>
                                        File size: {data.sizeText}
                                    </p>
                                </div>
                                <Divider />
                                <div className="file-preview-modal__description__container">
                                    <div className="file-preview-modal__description scrollable">
                                        {data.rawDescription && parse(data.rawDescription)}
                                    </div>
                                    <div className="d-none d-xl-block">
                                        {data.extension && (
                                            <>
                                                {!currentDownloading &&
                                                !itemsDownloading.includes(
                                                    id
                                                ) ? (
                                                    <Button
                                                        variant="primary"
                                                        onClick={
                                                            handleDownloadClick
                                                        }
                                                    >
                                                        Download
                                                    </Button>
                                                ) : (
                                                    <Spinner
                                                        isSmall={true}
                                                        justify="start"
                                                    />
                                                )}
                                            </>
                                        )}
                                    </div>
                                </div>
                            </div>
                        </>
                    )}
                </Modal.Body>
                <Modal.Footer className="d-xl-none">
                    {data.extension && (
                        <>
                            {!currentDownloading &&
                            !itemsDownloading.includes(id) ? (
                                <Button
                                    variant="primary"
                                    onClick={handleDownloadClick}
                                >
                                    Download
                                </Button>
                            ) : (
                                <Spinner isSmall={true} justify="start" />
                            )}
                        </>
                    )}
                </Modal.Footer>
            </Modal>
        </div>
    );
};

ResourceDetail.propTypes = {
    id: PropTypes.number,
    show: PropTypes.bool,
    handleClose: PropTypes.func,
    itemsDownloading: PropTypes.array,
    setItemsDownloading: PropTypes.func,
    setMessage: PropTypes.func,
    setShowAlert: PropTypes.func,
    setDownloadFailed: PropTypes.func,
};

export default ResourceDetail;
