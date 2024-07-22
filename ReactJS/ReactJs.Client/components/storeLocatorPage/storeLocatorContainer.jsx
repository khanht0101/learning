import React from 'react';
import StoreLocatorMap from './StoreLocatorMap';
import StoreLocatorList from './storeLocatorList';
import StoreLocatorApi from 'apis/StoreLocatorApi';
import PropTypes from 'prop-types';

export default class StoreLocatorContainer extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      storesList: {},
      apiKey: '',
      countryCode: '',
      defaultCoordinates: '',
      maxStore: 15,
      zoomLevel: 11,
      postalCode: '',
      mapIsReady: false,
      currentCoordinates: {},
      map: {},
      markerCluster: {},
      originMarker: {}
    };

    this.callMapService = this.callMapService.bind(this);
    this.searchStores = this.searchStores.bind(this);
    this.initMap = this.initMap.bind(this);
    this.getCurrentPosition = this.getCurrentPosition.bind(this);
    this.detectUserCurrentLocation = this.detectUserCurrentLocation.bind(this);
    this.initInfoWindow = this.initInfoWindow.bind(this);
    this.clearMarkers = this.clearMarkers.bind(this);
    this.registerAutoSuggestionForPlaceName = this.registerAutoSuggestionForPlaceName.bind(this);
    this.zoomIntoCertainArea = this.zoomIntoCertainArea.bind(this);
    this.getAutocompletePredictions = this.getAutocompletePredictions.bind(this);
    this.getAutocompleteRequest = this.getAutocompleteRequest.bind(this);
    this.getPlaceDetails = this.getPlaceDetails.bind(this);
    this.setLocationMarker = this.setLocationMarker.bind(this);
    this.onSubmitValue = this.onSubmitValue.bind(this);
    this.handleRemove = this.handleRemove.bind(this);
    this.filterCategory = [];
    this.firstLoad = true;
    this.stockistCategories=[
      {category:'Hire And Rental',icon:'grn-circle.png', class: 'checkbox-green'},
      {category:'Repair And Services',icon:'ylw-circle.png', class: 'checkbox-yellow'},
      {category:'Sales',icon:'orange-circle.png', class: 'checkbox-orange'},
      {category:'MQ Whiteman Dealer',icon:'blu-circle.png', class: 'checkbox-blue'}
    ];
    this.isSearched = false;
  }


  componentDidMount() {
    StoreLocatorApi.getMapSettings(this.props.contentId)
                   .then(({ data }) => {
                      this.setState({
                        apiKey: data.apiKey,
                        maxStore: data.maxStoresShown,
                        defaultCoordinates: data.defaultCoordinates,
                        countryCode: data.defaultCountryCode,
                        zoomLevel: data.defaultZoomLevel
                      }, () => {
                        this.callMapService();
                      });
                   });
  }

  callMapService() {
    const scriptMarkerCluster = document.createElement('script');
    const urlMarkerCluster = `https://unpkg.com/@google/markerclustererplus@5.1.0/dist/markerclustererplus.min.js`;
    scriptMarkerCluster.src = urlMarkerCluster;
    document.body.appendChild(scriptMarkerCluster);

    const script = document.createElement('script');
    const APIKey = this.state.apiKey;
    const url = `https://maps.googleapis.com/maps/api/js?key=` + APIKey + `&libraries=places`;

    script.src = url;
    script.async = true;
    script.defer = true;
    script.addEventListener('load', () => {
      this.setState({ mapIsReady: true }, () => {
        this.initMap();
      });
    });

    document.body.appendChild(script);
  }

  zoomIntoCertainArea(originLocation) {
    this.state.map.setCenter(originLocation);
    this.state.map.setZoom(this.state.zoomLevel);
  }

  registerAutoSuggestionForPlaceName(autocomplete) {
    autocomplete.addListener('place_changed', async () => {
    this.isSearched = true;

      const place = autocomplete.getPlace();
      if (place && place.geometry) {
        const postalCode = place.name;
        if (this.state.postalCode === postalCode)
          return

        this.setState({ postalCode: postalCode });
        
        const originLocation = place.geometry.location;
        this.setLocationMarker(originLocation);
        this.searchStores(originLocation.lat(), originLocation.lng(), this.state.maxStore);
        this.zoomIntoCertainArea(originLocation);
      }
    });
  }

  onSubmitValue() {
    const input = document.getElementById('search-box');
    const postalCode = input.value;

    if (parseInt(postalCode) && postalCode && this.state.postalCode !== postalCode) {
      this.isSearched = true;
      this.setState({ postalCode: postalCode });
      this.getAutocompletePredictions(postalCode);
      return;
    }
  }

  getAutocompletePredictions(inputValue) {
    if (inputValue) {
      const autocompleteService = new window.google.maps.places.AutocompleteService();
      const placeRequest = this.getAutocompleteRequest(inputValue);
      autocompleteService.getPlacePredictions(placeRequest, (predictionsArr) => {
        if (predictionsArr) {
          const validPlace = Object.values(predictionsArr)[0];
          if (validPlace && (validPlace.terms.length > 3 && validPlace.terms[2].value === inputValue)
            || (validPlace.terms.length == 3 && validPlace.terms[1].value === inputValue)) {
            this.getPlaceDetails(validPlace);
            return;
          }
        }

        const defaultLatitude = parseFloat(this.state.defaultCoordinates.split(",")[0]);
        const defaultLongitude = parseFloat(this.state.defaultCoordinates.split(",")[1]);
        this.state.map.setCenter({ lat: defaultLatitude, lng: defaultLongitude });

        this.state.map.setZoom(this.state.zoomLevel);
        this.setState({ storesList: {} });
        this.clearMarkers(this.state.map);
      });
    }
  }

  getPlaceDetails(place) {
    const placeDetailsRequest = { placeId: place.place_id, fields: ['name', 'geometry'] };
    const placesService = new window.google.maps.places.PlacesService(this.state.map);
    placesService.getDetails(placeDetailsRequest, (placeDetailsResult) => {
      if (placeDetailsResult.geometry) {
        const originLocation = placeDetailsResult.geometry.location;
        this.searchStores(originLocation.lat(), originLocation.lng(), this.state.maxStore);
        this.zoomIntoCertainArea(originLocation);
      }
    });
  }

  getAutocompleteRequest(inputValue) {
    return { input: inputValue, componentRestrictions: { country: this.state.countryCode.split(",") }, types: ['(regions)'] };
  }

  initMap() {
    const defaultLatitude = parseFloat(this.state.defaultCoordinates.split(",")[0]);
    const defaultLongitude = parseFloat(this.state.defaultCoordinates.split(",")[1]);

    const map = new window.google.maps.Map(document.getElementById('map'), {
      zoom: this.state.zoomLevel,
      center: { lat: defaultLatitude, lng: defaultLongitude },
      styles: [
        {
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#e9e9e9"
            }
          ]
        },
        {
          "elementType": "labels.icon",
          "stylers": [
            {
              "visibility": "off"
            }
          ]
        },
        {
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#616161"
            }
          ]
        },
        {
          "elementType": "labels.text.stroke",
          "stylers": [
            {
              "color": "#f5f5f5"
            }
          ]
        },
        {
          "featureType": "administrative.land_parcel",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#bdbdbd"
            }
          ]
        },
        {
          "featureType": "poi",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#eeeeee"
            }
          ]
        },
        {
          "featureType": "poi",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#757575"
            }
          ]
        },
        {
          "featureType": "poi.park",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#e5e5e5"
            }
          ]
        },
        {
          "featureType": "poi.park",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#9e9e9e"
            }
          ]
        },
        {
          "featureType": "road",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#ffffff"
            }
          ]
        },
        {
          "featureType": "road.arterial",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#757575"
            }
          ]
        },
        {
          "featureType": "road.highway",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#dadada"
            }
          ]
        },
        {
          "featureType": "road.highway",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#616161"
            }
          ]
        },
        {
          "featureType": "road.local",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#9e9e9e"
            }
          ]
        },
        {
          "featureType": "transit.line",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#e5e5e5"
            }
          ]
        },
        {
          "featureType": "transit.station",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#eeeeee"
            }
          ]
        },
        {
          "featureType": "water",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#c9c9c9"
            }
          ]
        },
        {
          "featureType": "water",
          "elementType": "labels.text.fill",
          "stylers": [
            {
              "color": "#9e9e9e"
            }
          ]
        }
      ]
    });

    const icon = {
      url: '/images/location-solid-60x66.svg',
      scaledSize: new google.maps.Size(60, 66),
      size: new google.maps.Size(60, 66)
    };

    map.data.setStyle({
      icon: icon
    });

    let markerCluster = new MarkerClusterer(map, null,
      {maxZoom: 17,styles: [{width:35, height:35, className:'cluster-gray'}]});


    this.setState({ map: map, markerCluster }, () => {
      const infoWindow = new window.google.maps.InfoWindow();
      this.initInfoWindow(infoWindow);
      this.initAutocomplete();

      this.detectUserCurrentLocation(map);
    });
  }

  initAutocomplete() {
    const input = document.getElementById('search-box');
    const options = {
      types: ['(regions)'],
      componentRestrictions: { country: this.state.countryCode.split(",") }
    };

    const autocomplete = new window.google.maps.places.Autocomplete(input, options);
    autocomplete.setFields(
      ['address_components', 'geometry', 'name']);
    this.registerAutoSuggestionForPlaceName(autocomplete, this.state.map);
  }

  initInfoWindow(infoWindow) {
    this.state.map.data.addListener('click', (event) => {
      const name = event.feature.getProperty('name');
      const phone = event.feature.getProperty('address').phone;
      const position = event.feature.getGeometry().get();
      const address = event.feature.getProperty('address').formattedAddress;
      const content = `
          <div class="find-store-marker-container">
            <h4 class="find-store__store-name">${name}</h4>
            <ul class="find-store__store-address">
              <li class="is-flex align-items-center">
                <i class="icon-location-solid"></i>
                ${address}
              </li>
              <li class="is-flex align-items-center">
                <i class="icon-phone"></i>
                <a href="tel:${phone}">${phone ? phone : 'Not Available'}</a>
              </li>
            </ul>
        </div>
        `;
      infoWindow.setContent(content);
      infoWindow.setPosition(position);
      infoWindow.setOptions({ pixelOffset: new window.google.maps.Size(0, -30) });
      infoWindow.open(this.state.map);
    });
  }

  getCurrentPosition(map,globalState) {
    return new Promise((resolve) => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(async function (position) {
          const pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          };
          map.setCenter(pos);
          resolve(pos);
        }, function(error) {
          // can not detect use default
            let defaultLatitude = parseFloat(globalState.state.defaultCoordinates.split(",")[0]);
            let defaultLongitude = parseFloat(globalState.state.defaultCoordinates.split(",")[1]);
            globalState.searchStores(defaultLatitude, defaultLongitude);
      });
      }
    });
  }

  async detectUserCurrentLocation(map) {
    const currentPos = await this.getCurrentPosition(map,this);
    this.setState({
      currentCoordinates: currentPos
    }, () => {
      this.searchStores(this.state.currentCoordinates.lat, this.state.currentCoordinates.lng);
    });
  }

  clearMarkers(map) {
    this.state.map.data.forEach(function (feature) {
      map.data.remove(feature);
    });
  }

  createMarker(data){
    var infoWindow = new google.maps.InfoWindow();
    let iconBase = 'https://maps.google.com/mapfiles/kml/paddle/'
    let arrIcons = this.stockistCategories;
    
    const days = ['sunday','monday','tuesday','wednesday','thursday','friday','saturday'];
    const now = days[new Date().getDay()];

    var markers = data.features.map(function(feature, i) {
      const latitude = feature.geometry.coordinates[1];
      const longitude = feature.geometry.coordinates[0];

      let category = feature.properties.categories[0];
      let iconResult = arrIcons.filter(s=>s.category == category)[0];
      if(iconResult === undefined){
        iconResult = arrIcons.filter(s=>s.category == 'Sales')[0]
      }

      const marker = new google.maps.Marker({
        position: {lat: latitude,lng: longitude},
        icon: {url:iconBase + iconResult.icon, scaledSize: new google.maps.Size(30, 30)}
      });

      (function (marker, data) {
        google.maps.event.addListener(marker, "click", function (e) {
          const {name, categories, website, daysOfWeek, imageUrl} = feature.properties;
          const {formattedAddress: address, phone, fax} = feature.properties.address;
          
          const categoriesArr=categories.map((category)=>{
            if(category!='')
              return `<span>${category}</span>`
          })

          const categoriesHtml=categoriesArr.join('')!==''?'<li class="is-flex align-items-center categories">'+categoriesArr.join('')+'</li>':'';
          const daysOfWeekArr=daysOfWeek!==null && Object.keys(daysOfWeek).map((key, i) => {
            if(daysOfWeek[key]!==""){
              const stockistToday=now===key?'stockist-today':'';
              return `<p key=${i} class=${stockistToday}>
              <span class=${stockistToday}>${key}</span>
              ${daysOfWeek[key]}
            </p>`  
            }
          })

          const logoHtml = imageUrl && `<img src ="${imageUrl}" style="margin-left:50px; width:180px; height:auto;">`;
          const daysOfWeekHtml=daysOfWeekArr && daysOfWeekArr.join('')!==''?'<li><div class="hours-container">'+ daysOfWeekArr.join('')+'</div></li>':'';
          const phoneHtml = phone && `<i class="icon icon-phone"></i><a href="tel:${phone}">${phone}</a>`;
          const faxHtml = fax && `<i class="fa fa-fax"></i><a href="fax:${fax}">${fax}</a>`;
          const websiteHtml = website && `<i class="icon icon-earth"></i><a href="${website}" target="_blank">${website}</a>`;

          const content = `
            <div class="find-store-marker-container">
              <h4 class="find-store__store-name">${name}</h4>
              <ul class="find-store__store-address">
                <li class="is-flex align-items-center">
                  ${logoHtml}
                </li>
                ${categoriesHtml}
                <li class="is-flex align-items-center">
                  <i class="icon icon-location-solid"></i>
                  ${address}
                </li>
                <li class="is-flex align-items-center">
                  ${phoneHtml}
                </li>
                <li class="is-flex align-items-center">
                  ${faxHtml}
                </li>
                <li class="is-flex align-items-center">
                  ${websiteHtml}
                </li>
                ${daysOfWeekHtml}
                <li class="is-flex align-items-center">
                  <a class="stockists-direction" href="https://www.google.com/maps/dir/?api=1&destination=${encodeURIComponent(address)}" target="_blank">Directions</a>
                </li>
              </ul>
          </div>
        `;
          infoWindow.setContent(content);
          infoWindow.open(map, marker);
        });
      })(marker, data);
        return marker;
      });

      let markerCluster = this.state.markerCluster;
      markerCluster.clearMarkers();
      markerCluster.addMarkers(markers);
      this.setState({markerCluster});
        
      if (this.state.storesList.features.length === 1) {
        this.state.map.setZoom(18);
      } else {
        this.isSearched ? this.state.map.setZoom(10):this.state.map.setZoom(this.state.zoomLevel);
      }

      const map = this.state.map;
      $('.marker-link').off().on('click', function () {
        const marker = markers[$(this).data('markerid')];
        google.maps.event.trigger(marker, 'click');
        map.setZoom(18);
        map.setCenter(marker.getPosition());
      });
  }

  searchStores(lat, lng) {
    this.setState({currentCoordinates:{lat, lng}}, ()=>{
      const radiusInMeter = 50000;
      let searchedRadius = this.isSearched ? radiusInMeter : 0;

      StoreLocatorApi.searchNearbyStores(this.state.currentCoordinates.lat, this.state.currentCoordinates.lng, this.props.contentId, this.state.maxStore, this.filterCategory, searchedRadius)
        .then(({ data }) => {
            this.setState({ storesList: data }, () => {
              // this.clearMarkers(this.state.map);
              if (this.state.storesList.features.length > 0) {
                const firstStoreCoordinates = data.features[0].geometry.coordinates;
                const latitude = parseFloat(firstStoreCoordinates[1]);
                const longitude = parseFloat(firstStoreCoordinates[0]);
                  this.state.map.setCenter({ lat: latitude, lng: longitude });

                this.createMarker(data);
              }
            });
        });
    })
    
  }

  getStores(category){
    if(this.state.currentCoordinates.lat===undefined || this.state.currentCoordinates.lng===undefined){
      let defaultLatitude = parseFloat(this.state.defaultCoordinates.split(",")[0]);
      let defaultLongitude = parseFloat(this.state.defaultCoordinates.split(",")[1]);
      this.setState({currentCoordinates:{lat:defaultLatitude,lng:defaultLongitude}})
    }
    StoreLocatorApi.searchNearbyStores(this.state.currentCoordinates.lat, this.state.currentCoordinates.lng,this.props.contentId,this.state.maxStore, category)
      .then(({ data }) => {
        this.setState({ storesList: data }, () => {
          if (this.state.storesList.features.length > 0) {
            const firstStoreCoordinates = data.features[0].geometry.coordinates;
            const latitude = parseFloat(firstStoreCoordinates[1]);
            const longitude = parseFloat(firstStoreCoordinates[0]);
            !this.firstLoad && this.state.map.setCenter({ lat: latitude, lng: longitude });

            this.createMarker(data);
          }
          this.firstLoad = false;
        });
      });
  }

  setLocationMarker(location) {
    let originMarker = this.state.originMarker;
    if (Object.keys(originMarker).length === 0) {
      originMarker = new window.google.maps.Marker({
        position: location,
        map: this.state.map,
        visible: true
      });
      this.setState({ originMarker: originMarker });
    } else {
      originMarker.setPosition(location);
      this.setState({ originMarker: originMarker });
    }
  }

  handleFilter(category, event){
    if(event.currentTarget.checked && !this.filterCategory.includes(category)){
      this.filterCategory.push(category);
    } else {
      this.filterCategory = this.filterCategory.filter(item=>{
        return item !== category;
      })
    }
    this.searchStores(this.state.currentCoordinates.lat, this.state.currentCoordinates.lng);
  }

  handleRemove(){
    this.isSearched = false;
    const input = document.getElementById('search-box');
    input.value='';
    this.setState({ postalCode: 0 });
  }

  render() {
    const { storesList } = this.state;
    return (
      <div className="map-container">
        <div className="dealer-location-page">
          <div className="find-stores-stockist">
            <div className="find-store__search-bar">
              <StoreLocatorMap handleRemove={this.handleRemove} onSubmitValue={this.onSubmitValue} onFilter={(category, event) => this.handleFilter(category, event)} categories={this.stockistCategories} internationalLink={this.props.internationalLink} internationalText={this.props.internationalText}/>
            </div>
            <div className="flex-tool-stock-location">
              <div className="find-store__map">
                <div id="map" className="map-find-stockist"></div>
              </div>
              <div className="find-store__result find-store-stockist__result">
                <StoreLocatorList item={storesList} />
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

StoreLocatorContainer.propTypes = {
  contentId: PropTypes.string,
  internationalText: PropTypes.string,
  internationalLink: PropTypes.string
};