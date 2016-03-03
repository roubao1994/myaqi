define(function (require, exports) {
    require("https://api.mapbox.com/mapbox.js/v2.3.0/mapbox.js");

    L.mapbox.accessToken = 'pk.eyJ1IjoibHVsZWluanUiLCJhIjoiY2lsYnppeWM4MXl5aHR5bHgwMGV3NDVhaCJ9.CcVHdQD-JTUiKPfp0tRryw';
    var map = L.mapbox.map('map', 'mapbox.streets')
        .setView([40, -74.50], 9);

});