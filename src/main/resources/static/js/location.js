// use navigation.geolocation.getcurrentPosition to get longitude/latitude
window.onload = function(){ 

    const success = (pos) => {

        let cordinates = pos.coords;

        document.getElementById("longitude").value = cordinates.longitude;
        document.getElementById("latitude").value = cordinates.latitude;

        document.getElementById("form").submit();
    }

    navigator.geolocation.getCurrentPosition(success /* error callback */);
    // pass values to hidden form fields through element id

    // use button on click to queue

};

/** TODO: change this into a cookie variable
 * 
 * check for cookie variable
 *      if it doesn't exist, ask for geolocation
 *          save geolocation in cookie;
 */