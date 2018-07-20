// use navigation.geolocation.getcurrentPosition to get longitude/latitude


    const success = (pos) => {

        let cordinates = pos.coords;

        document.cookie = "latitude=" + cordinates.latitude.toString();
        document.cookie = "longitude=" + cordinates.longitude.toString();

        document.getElementById("longitude").value = cordinates.longitude;
        document.getElementById("latitude").value = cordinates.latitude;

        document.getElementById("form").submit();
    }

    navigator.geolocation.getCurrentPosition(success /* error callback */);
    // pass values to hidden form fields through element id

    // use button on click to queue



/** TODO: Save latitude, longitude, and timezone in cookies to use in location controller
 * 
 * check for cookie variable
 *      if it doesn't exist, ask for geolocation
 *          save geolocation in cookie;
 */