/* thymeleaf throws error on script, work around to manually inject */
let mapScript = document.createElement("script");
mapScript.async = true;
mapScript.src = "https://maps.googleapis.com/maps/api/js?key=AIzaSyCQ16IY4VPnmLOt4OiQIIIzBNCMXgvulgg&libraries=places&callback=initMap";
let s0 = document.getElementsByTagName('script')[0];
s0.parentNode.insertBefore(mapScript, s0);



let map;
let success = (pos) => {
    let cordinates = pos.coords
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: cordinates.latitude, lng: cordinates.longitude},
        zoom: 8,
        styles: [
            {
                "featureType": "administrative",
                "elementType": "labels.text.fill",
                "stylers": [
                    {
                        "color": "#444444"
                    }
                ]
            },
            {
                "featureType": "landscape",
                "elementType": "all",
                "stylers": [
                    {
                        "color": "#f2f2f2"
                    }
                ]
            },
            {
                "featureType": "poi",
                "elementType": "all",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "road",
                "elementType": "all",
                "stylers": [
                    {
                        "saturation": -100
                    },
                    {
                        "lightness": 45
                    }
                ]
            },
            {
                "featureType": "road.highway",
                "elementType": "all",
                "stylers": [
                    {
                        "visibility": "simplified"
                    }
                ]
            },
            {
                "featureType": "road.arterial",
                "elementType": "labels.icon",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "transit",
                "elementType": "all",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "water",
                "elementType": "all",
                "stylers": [
                    {
                        "color": "#46bcec"
                    },
                    {
                        "visibility": "on"
                    }
                ]
            }
        ]        
      });
      
}         

navigator.geolocation.getCurrentPosition(success);

function getCoords() {
    
    let pos = map.getCenter()
    console.log(pos.lat());
    
    document.getElementById("longitude").value = pos.lng();
    document.getElementById("latitude").value = pos.lat();

    document.getElementById("form").submit(); 
}