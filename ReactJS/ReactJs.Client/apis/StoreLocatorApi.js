import axios from 'axios';

class StoreLocatorApi {
  static getMapSettings(contentID) {
    return axios.get(`${AppConfig.apis.getMapSettings}?id=${contentID}`);
  }
  static searchNearbyStores(lat, long, contentID, numberOfStoresForDisplay, categories, searchedRadius) {
    return axios.post(`${AppConfig.apis.searchNearbyStores}?latitude=${lat}&longitude=${long}&id=${contentID}&numberOfStoresForDisplay=${numberOfStoresForDisplay}&categories=${categories??''}&searchedRadius=${searchedRadius}`);
  }
}

export default StoreLocatorApi;