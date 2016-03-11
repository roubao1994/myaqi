define(function (require, exports) {
    // require引入依赖
    require("materialize");
    require("mapbox");
    var geoModel = require('geoModel');

    //exports提供对外接口,地图初始化
    exports.init = function () {
        L.mapbox.accessToken = 'pk.eyJ1IjoibHVsZWluanUiLCJhIjoiY2lsYnppeWM4MXl5aHR5bHgwMGV3NDVhaCJ9.CcVHdQD-JTUiKPfp0tRryw';
        var map = L.mapbox.map('map', 'luleinju.pajg0dam', {
            // 是否允许滚轮控制
            scrollWheelZoom: false,
            zoomControl: false,
            minZoom: 4,
            maxZoom: 8,
            maxBounds: new L.LatLngBounds(new L.LatLng(15, 58), new L.LatLng(58, 150))
        }).setView([34, 108.9], 5).locate();

        L.control.zoom({
            position: 'bottomleft'
        }).addTo(map);


        geoModel.getMapFeature(function (geoJson) {
            map.featureLayer.setGeoJSON(geoJson);

            $('#loading').remove();
        });
    };

});