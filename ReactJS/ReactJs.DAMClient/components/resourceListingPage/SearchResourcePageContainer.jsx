import React, { useState, useEffect, useRef } from 'react';
import ResourceListingApi from 'apis/ResourceListingApi';
import Spinner from '../common/Spinner';
import ResourceCard from './ResourceCard';
import ResourceDetail from './ResourceDetail';
import ResourceAlert from '../common/Alert';
import qs from 'query-string';
import { sumSelectedSizes } from '../../helpers/DownloadHelper';
import { MAX_FILE_DOWNLOAD_SIZE } from '../../constants/resourceListing/ResourceListingConstant';
import UrlHelper from '../../helpers/UrlHelper';

function SearchResourcePageContainer({rootId}) {
    const [pageSize] = useState(36);
    const [pageNumber, setPageNumber] = useState(1);
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const [downloading, setDownloading] = useState(false);
    const [downloadFailed, setDownloadFailed] = useState(false);
    const [itemsDownloading, setItemsDownloading] = useState([]);
    const [isLastPage, setIsLastPage] = useState(false);
    const [selectedFile, setSelectedFile] = useState(null);
    const [selectedFiles, setSelectedFiles] = useState([]);
    const [totalCount, setTotalCount] = useState(0);
    const [searchText, setSearchText] = useState('');
    const [isMobile, setIsMobile] = useState(false);

    const [message, setMessage] = useState({
        message: '',
        status: 'loading',
    });

    const [showAlert, setShowAlert] = useState(false);

    const [show, setShow] = useState(false);
    const [downloadFilesInfo, setDownloadFilesInfo] = useState(null);

    const isFetchingRef = useRef(false);

    const handleClose = () => setShow(false);

    useEffect(() => {
        const mediaQuery = window.matchMedia('(max-width: 768px)');

        setIsMobile(mediaQuery.matches);

        const handleResize = (e) => setIsMobile(e.matches);

        mediaQuery.addEventListener('change', handleResize);

        return () => {
            mediaQuery.removeEventListener('change', handleResize);
        };
    }, []);

    const handleSelectFile = (id) => {
        if (!id) return;

        const itemExists = selectedFiles.includes(id);

        if (itemExists) {
            setSelectedFiles(selectedFiles.filter((fileId) => fileId !== id));
        } else {
            const selectedSizes = sumSelectedSizes(items, selectedFiles);
            const selectedItem = items.find((item) => item.id === id);
            const currentTotalSize = selectedSizes + selectedItem.size;

            if (currentTotalSize > MAX_FILE_DOWNLOAD_SIZE) {
                // eslint-disable-next-line no-undef
                $(`#${id}`).prop('checked', false);

                setDownloading(true);
                setDownloadFailed(true);
                setMessage({
                    message: 'You can only download up to 2GB at a time.',
                    status: 'info',
                });

                return;
            }

            setSelectedFiles([...selectedFiles, id]);
        }
    };

    const handleClickFile = (id) => {
        if (!id) return;

        setSelectedFile(id);
        setShow(true);
    };

    const handleHideModel = () => {
        setDownloading(false);
        setDownloadFailed(false);
        setMessage({
            message: '',
            status: 'loading',
        });
        setShowAlert(false);
    };

    useEffect(()=>{
        function processCompressed(evt){
            if(AppConfig.isDownloadDebugging){
                console.log(evt);
            }

            const isCompressed = evt.detail?.compressed ?? false;
            
            if(downloadFilesInfo){
                const { isDownloadSelected } = downloadFilesInfo;

                if (isCompressed) {
                    if (evt?.detail?.filename && UrlHelper.isValidUrl(evt.detail.filename) && evt.detail.filename.includes('blob.')) {
                        setDownloadFilesInfo(null);
                        setDownloading(false);
                        setDownloadFailed(false);
                        if (isDownloadSelected) {
                            // eslint-disable-next-line no-undef
                            $('input:checked').prop('checked', false);
                            setSelectedFiles([]);
                        }
                        setShowAlert(false);
                        setMessage({
                            message: '',
                            status: 'loading',
                        });
    
                        setTimeout(function(){
                            ResourceListingApi.saveFileWithUrl(evt.detail.filename);
                        }, 1000);
                    } else {
                        setDownloadFilesInfo((prevData) => ({
                            ...prevData,
                            compressed: true,
                        }));
                        return;
                    }
                } else {
                    setDownloadFilesInfo(null);
                    setDownloading(false);
                    setDownloadFailed(true);
                 
                    setMessage({
                        message: 'An error occurred downloading the files',
                        status: 'failed',
                    });
                }
            }
        }

        document.addEventListener("downloadCompressed", processCompressed);

        return (()=>{
            document.removeEventListener("downloadCompressed", processCompressed);
        });
    }, [downloadFilesInfo]);

    useEffect(() => {
        if (downloading || message.status === 'info') {
            setShowAlert(true);
        }
    }, [downloading, message.status]);

    useEffect(() => {
        const downloadSelectedBtn = document.querySelector(
            '#downloadSelectedBtn'
        );

        if (!downloadSelectedBtn) return;

        if (selectedFiles.length > 0) {
            downloadSelectedBtn.classList.remove('disabled');
        } else {
            downloadSelectedBtn.classList.add('disabled');
        }
    }, [selectedFiles]);

    useEffect(() => {
        const handleScroll = () => {
            const lastCard = document.querySelector('.file-card:last-child');

            if (!lastCard) return;

            const lastCardOffset = lastCard.offsetTop + lastCard.clientHeight;
            const pageOffset = window.pageYOffset + window.innerHeight;

            if (
                loading ||
                isLastPage ||
                pageOffset <= lastCardOffset ||
                isFetchingRef.current
            )
                return;

            isFetchingRef.current = true;

            setLoading(true);

            var params = qs.parse(window.location.search, {
                ignoreQueryPrefix: true,
            });

            ResourceListingApi.searchResources(
                rootId,
                decodeURI(params.search),
                pageNumber + 1,
                pageSize
            )
            .then((response) => {
                const { data } = response;

                setIsLastPage(data.isLastPage);
                if (data.items && data.items.length > 0) {
                    setItems((prevItems) => {
                        const newItems = data.items.filter(
                            (item) =>
                                !prevItems.some(
                                    (prevItem) => prevItem.id === item.id
                                )
                        );
                        return prevItems.concat(newItems);
                    });
                    setPageNumber(pageNumber + 1);
                }

                isFetchingRef.current = false;
                setLoading(false);
            })
            .catch(() => {
                isFetchingRef.current = false;
                setLoading(false);
            });
        };

        window.addEventListener('scroll', handleScroll);

        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, [loading, isLastPage, pageNumber, pageSize]);

    useEffect(() => {
        var params = qs.parse(window.location.search, {
            ignoreQueryPrefix: true,
        });

        setSearchText(decodeURI(params.search));

        ResourceListingApi.searchResources(
            rootId,
            decodeURI(params.search),
            pageNumber,
            pageSize
        )
        .then((response) => {
            const { data } = response;
            if (data.items && data.items.length > 0) {
                setItems(data.items);
                setIsLastPage(data.items.length < pageSize);
                setTotalCount(data.totalItemCount);
            }
            
            setLoading(false);
        })
        .catch(() => {
            setIsLastPage(true);
            setLoading(false);
        });
    }, []);

    useEffect(() => {
        // eslint-disable-next-line no-undef
        $('[data-bs-toggle="popover"]').each(function () {
            // eslint-disable-next-line no-undef
            new bootstrap.Popover(this);
        });
    });

    useEffect(() => {
        const beforeUnload = (ev) => {
            ev.preventDefault();
            return (ev.returnValue = 'Downloading...');
        };

        if (!downloading && itemsDownloading.length <= 0) {
            window.removeEventListener('beforeunload', beforeUnload);
            return;
        }

        window.addEventListener('beforeunload', beforeUnload);
        return () => {
            window.removeEventListener('beforeunload', beforeUnload);
        };
    }, [downloading, itemsDownloading]);

    useEffect(() => {
        function downloadSelected() {
            setDownloading(true);
            setMessage({
                message: 'Downloading...',
                status: 'loading',
            });

            const selectedSizes = sumSelectedSizes(items, selectedFiles);

            if (selectedSizes > MAX_FILE_DOWNLOAD_SIZE) {
                setDownloadFailed(true);
                setMessage({
                    message: 'You can only download up to 2GB at a time.',
                    status: 'failed',
                });
                
                return;
            }

            // Get SignalR connection from jquery
            let connection = getSignalRConnection();

            if(AppConfig.isDownloadDebugging){
                console.log("Call startDownloadSelected: " + connection.connectionId);
            }

                ResourceListingApi.startDownloadSelected(
                    selectedFiles,
                    null,
                    connection.connectionId
                )
                .then(({ fileUrl, fileName }) => {
                    setDownloadFilesInfo({ fileUrl, fileName, isDownloadSelected: true });
                })
                .catch((err) => {
                    setDownloading(false);
                    setDownloadFailed(true);
                    setDownloadFilesInfo(null);
                    setMessage({
                        message: err.errorMsg ?? 'An error occurred downloading the files',
                        status: 'failed',
                    });
                });
            }

        const downloadSelectedBtn = document.querySelector(
            '#downloadSelectedBtn'
        );

        if (!downloadSelectedBtn) return;

        downloadSelectedBtn.addEventListener('click', downloadSelected);

        return () => {
            if (!downloadSelectedBtn) return;

                downloadSelectedBtn.removeEventListener(
                    'click',
                    downloadSelected
                );
            };
        },
        [selectedFiles]
    );

    if (!items) return null;

    if (loading && totalCount <= 0) return <Spinner />;

    return (
        <div className="listing-container mb-10 pt-4">
            <>
                {totalCount > 0 ? (
                    <>
                        <div className="d-sm-flex flex-sm-row justify-content-center align-items-lg-center mb-6 text-center">
                            <h1>
                                Search results for <strong>{searchText}</strong>
                            </h1>
                        </div>
                        <div className="d-flex flex-row justify-content-end align-items-center mb-6">
                            {totalCount > 1 ? (
                                <p className="text-bold">{totalCount} assets</p>
                            ) : (
                                <p className="text-bold">{totalCount} asset</p>
                            )}
                            <div className={`d-flex flex-row justify-content-end align-items-center ${isMobile ? "ms-3" : ""}`}>
                                {
                                    !isMobile && <div className="branding-info d-flex flex-row justify-content-between align-items-center">
                                        <img src="/assets/images/icons/info.svg" alt="info" />
                                        <span className="branding-info">Each multiple download is limited to a combined file size of 2GB.</span>

                                    </div>
                                }
                                <a
                                    id="downloadSelectedBtn"
                                    className="btn btn-primary disabled me-4 btn-download"
                                >
                                    Download Selected
                                </a>
                            </div>
                        </div>
                        {
                            isMobile && <div className="branding-info-mobile d-flex flex-row justify-content-between align-items-center mb-6">
                                <img src="/assets/images/icons/info.svg" alt="info" />
                                <span className="branding-info">Each multiple download is limited to a combined file size of 2GB.</span>

                            </div>
                        }
                    </>
                ) : (
                    <div className="d-sm-flex flex-sm-row justify-content-center align-items-lg-center mb-6 text-center">
                        <h1>
                            No results found for <strong>{searchText}</strong>
                        </h1>
                    </div>
                )}
            </>

            <div className="d-flex flex-column justify-content-center">
                <div className="w-100 resource-container">
                    {items.map((item) => (
                        <ResourceCard
                            key={item.id}
                            {...item}
                            handleSelectFile={handleSelectFile}
                            handleClickFile={handleClickFile}
                            itemsDownloading={itemsDownloading}
                            setItemsDownloading={setItemsDownloading}
                            setMessage={setMessage}
                            setShowAlert={setShowAlert}
                            setDownloadFailed={setDownloadFailed}
                        />
                    ))}
                </div>
                {loading && <Spinner />}
            </div>
            {show && (
                <ResourceDetail
                    id={selectedFile}
                    show={show}
                    handleClose={handleClose}
                    itemsDownloading={itemsDownloading}
                    setItemsDownloading={setItemsDownloading}
                    setMessage={setMessage}
                    setShowAlert={setShowAlert}
                    setDownloadFailed={setDownloadFailed}
                />
            )}

            <ResourceAlert
                showModal={showAlert}
                backDropStatic={downloading && !downloadFailed}
                onHide={handleHideModel}
                message={message}
            />
        </div>
    );
}

export default SearchResourcePageContainer;
