import UrlHelper from '../helpers/UrlHelper';
import axios from 'axios';

axios.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        if (error.response.status === 401) {
            // redirect to login page
            window.location = AppConfig.urls.login;
        }

        return error;
    }
);

class ResourceListingApi {
    static searchResources(contentID, searchText, pageNumber, pageSize) {
        pageNumber = pageNumber || 0;
        pageSize = pageSize || 10;

        return axios.get(
            `${AppConfig.apis.SearchResources}?starterNode=${contentID}&searchText=${searchText}&pageIndex=${pageNumber}&pageSize=${pageSize}`
        );
    }

    static getResources(contentID, pageNumber, pageSize) {
        pageNumber = pageNumber || 0;
        pageSize = pageSize || 10;

        return axios.get(
            `${AppConfig.apis.getResources}?starterNode=${contentID}&pageIndex=${pageNumber}&pageSize=${pageSize}`
        );
    }

    static getResourceDetail(contentID) {
        return axios.get(`${AppConfig.apis.getResourceDetail}?id=${contentID}`);
    }

    static startDownloadSelected(selectedFiles, categoryId, connectionId) {
        return new Promise((resolve, reject) => {
            if (!Array.isArray(selectedFiles)) {
                reject({
                    errorMsg: 'Invalid argument',
                    data: null,
                    status: 400,
                });
                return;
            }

            let resources = selectedFiles.join(',');

            axios
                .get(
                    categoryId != undefined && categoryId != null ? 
                    `${AppConfig.apis.checkDownloadSelected}?resources=${resources}&categoryId=${categoryId}&clientId=${connectionId}` : 
                    `${AppConfig.apis.checkDownloadSelected}?resources=${resources}&categoryId=${0}&clientId=${connectionId}`
                )
                .then((checkDownloadSelectedResponse) => {
                    if (
                        !checkDownloadSelectedResponse.data ||
                        typeof checkDownloadSelectedResponse.data.valid !==
                            'boolean'
                    ) {
                        reject({
                            errorMsg: 'Invalid response from server',
                            data: null,
                            status: 400,
                        });

                        return;
                    }

                    if (!checkDownloadSelectedResponse.data.valid) {
                        reject({
                            errorMsg: 'Max 2GB per download',
                            data: null,
                            status: 400,
                        });
                    } else {
                        const fileName = checkDownloadSelectedResponse.data.filename || '';
                        let fileUrl =
                            categoryId != null
                                ? `${AppConfig.apis.downloadSelected}?resources=${resources}&categoryId=${categoryId}`
                                : `${AppConfig.apis.downloadSelected}?resources=${resources}&categoryId=0`;
                        
                        return resolve({ fileUrl, fileName });
                    }
                }).catch(() => {
                    reject({
                        errorMsg: 'Invalid response from server',
                        data: null,
                        status: 500
                    });
                });
        });
    }

    static startDownloadAll(categoryId, connectionId) {
        return new Promise((resolve, reject) => {
            axios
                .get(
                    `${AppConfig.apis.checkDownloadAll}?categoryId=${categoryId}&clientId=${connectionId}`
                )
                .then((checkDownloadAllResponse) => {
                    if (!checkDownloadAllResponse.data.valid) {
                        reject({
                            errorMsg: checkDownloadAllResponse.data.message,
                            data: null,
                            status: 400,
                        });
                    } else {
                        const fileName = checkDownloadAllResponse.data.filename || '';
                        let fileUrl = `${AppConfig.apis.downloadAll}?categoryId=${categoryId}`;

                        return resolve({ fileUrl, fileName });
                    }
                }).catch(() => {
                    reject({
                        errorMsg: 'Invalid response from server',
                        data: null,
                        status: 500,
                    });
                });;
        });
    }

    static startDownloadFile(resourceId) {
        var path = `${AppConfig.apis.checkDownloadResource}?id=${resourceId}`;

        return new Promise((resolve, reject) => {
            axios
                .get(
                    path
                )
                .then((checkDownloadResponse) => {
                    if (!checkDownloadResponse.data.valid) {
                        reject({
                            errorMsg: checkDownloadResponse.data.message,
                            data: null,
                            status: 400,
                        });
                    } else {
                        const fileName = checkDownloadResponse.data.filename || '';

                        if (fileName && UrlHelper.isValidUrl(fileName) && fileName.includes('blob.')) {
                            setTimeout(function(){
                                ResourceListingApi.saveFileWithUrl(fileName);
                            }, 1000);

                            resolve({
                                errorMsg: "",
                                data: null,
                                status: 200,
                            });

                            return;
                        }
                        else{
                            reject({
                                errorMsg: 'Invalid response from server',
                                data: null,
                                status: 500
                            });
                        }
                    }
                }).catch(() => {
                    reject({
                        errorMsg: 'Invalid response from server',
                        data: null,
                        status: 500
                    });
                });
        });
    }

    static saveFileWithUrl(fileUrl){
        let success = false;
        // Get the blob url creator
        // Try to use a download link
        var link = document.createElement('a');
        if ('download' in link) {
            // Try to simulate a click
            try {
                if(AppConfig.isDownloadDebugging){
                    console.log('Save file by link');
                }

                link.setAttribute('href', fileUrl);
                // Set the download attribute (Supported in Chrome 14+ / Firefox 20+)
                link.setAttribute('download', "download.bin");
    
                // Simulate clicking the download link
                var event = document.createEvent('MouseEvents');
                event.initMouseEvent(
                    'click',
                    true,
                    true,
                    window,
                    1,
                    0,
                    0,
                    0,
                    0,
                    false,
                    false,
                    false,
                    false,
                    0,
                    null
                );
    
                link.dispatchEvent(event);
                success = true;
            } catch (ex) {
                if(AppConfig.isDownloadDebugging){
                    console.log('Download link method with simulated click failed with the following exception:');
    
                    console.log(ex);
                }
            }
        }
    
        if (!success) {
            // Fallback to window.location method
            try {
                if(AppConfig.isDownloadDebugging){
                    console.log('Save file by changing location');
                }

                window.location = fileUrl;
                success = true;
            } catch (ex) {
                if (AppConfig.isDownloadDebugging) {
                    console.log(
                        'Download link method with window.location failed with the following exception:'
                    );
                    console.log(ex);
                }
            }
        }
    
        return success;
    }
}

export default ResourceListingApi;
