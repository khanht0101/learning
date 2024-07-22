import axios from 'axios';

if (AppConfig && AppConfig.isBrowserSupportWebP) {
    let webp = AppConfig.isBrowserSupportWebP ? ', image/webp' : '';
    axios.defaults.headers.common['Accept'] = axios.defaults.headers.common.Accept + webp;
}

class ContentApi {
    static getProducts(searchRequest, pageNumber) {
        pageNumber = pageNumber || 1;
        return axios.post(`${AppConfig.apis.getProductsUrl}?pageNumber=${pageNumber}`, searchRequest);
    }

    static getArticles(searchRequest, pageNumber) {
        pageNumber = pageNumber || 1;
        return axios.post(`${AppConfig.apis.getArticlesUrl}?pageNumber=${pageNumber}`, searchRequest);
    }

    static getOthers(searchRequest, pageNumber) {
        pageNumber = pageNumber || 1;
        return axios.post(`${AppConfig.apis.getOthersUrl}?pageNumber=${pageNumber}`, searchRequest);
    }

    static searchContents(searchRequest, pageNumber, pageSize, contentType, imageCropProfileName) {
        pageNumber = pageNumber || 1;
        return axios.post(`${AppConfig.apis.searchContents}?pageNumber=${pageNumber}&pageSize=${pageSize}&contentType=${contentType}&imageCropProfileName=${imageCropProfileName}`, searchRequest);
    }

    static searchContentsByFiltersAndGrandParentId(searchRequest, pageSize, contentType, imageCropProfileName) {
        return axios.post(`${AppConfig.apis.searchContentsByFiltersAndGrandParentId}?pageSize=${pageSize}&contentType=${contentType}&imageCropProfileName=${imageCropProfileName}`, searchRequest);
    }
}

export default ContentApi;