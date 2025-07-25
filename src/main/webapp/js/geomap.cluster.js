/*
 * Refer to:
 * https://googlemaps.github.io/js-marker-clusterer/examples/simple_example.html
 * https://googlemaps.github.io/js-marker-clusterer/examples/advanced_example.html
 * 
 * Source:
 * https://github.com/googlemaps/js-marker-clusterer
 */

/* global google */

var dataMapClusterer;
var imageUrl = 'img/marker.png';
$(document).ready(function () {
    initSettings();

    $('.input-daterange').datepicker({
        keyboardNavigation: true,
        forceParse: false,
        autoclose: true,
        format: "yyyy-mm-dd",
        todayHighlight: true
    });
    $('.navbar-minimalize').on('click', function () {
        window.setTimeout(function () {
            $("#map").width($(".ibox").width() * 0.97);
        }, 500);
    });
    $("body").toggleClass("mini-navbar");
//    $('#lastLoginFrom').val("2017-01-01");
//    $('#lastLoginTo').val("2017-02-10");

    SmoothlyMenu();
    window.setTimeout(function () {
        $("#map").width($(".ibox").width() * 0.97);
    }, 1000);

    hWrapper=$("body").height() - $(".navbar-static-top").height() - $(".footer").height() - 100;
    $("#map").css("min-height",hWrapper);
    window.onresize = function (event) {
        hWrapper=$("body").height() - $(".navbar-static-top").height() - $(".footer").height() - 100;
        $("#map").css("min-height",hWrapper);
    };

    window.onresize = function (event) {
        var maxHeight = window.screen.height,
            curHeight = window.innerHeight;

        if (maxHeight === curHeight) {
            $("#map").height(555 * 1.05);
        } else {
            $("#map").height(555 * 0.93);
        }
    };

    dataMapClusterer = $.parseJSON(getDataMapCluster(
        $('#status').val(),
        $('#user').val(),
        $('#uploadFrom').val(), $('#uploadTo').val()));
    initMap();
    google.maps.event.addDomListener(window, 'load');
});

function updateSettings() {
    if (($('#uploadFrom').val()===''&&$('#uploadTo').val()!=='')) {
        swal({
            title: "Invalid Date Input",
            text: "Please fill the \"from\" date first before continuing fill the \"to\" date.",
            confirmButtonColor: "#DD6B55"
        }, function(){$('#modFilter').modal('show');});
    } else {
        dataMapClusterer = $.parseJSON(getDataMapCluster(
            $('#status').val(),
            $('#user').val(),
            $('#uploadFrom').val(),
            $('#uploadTo').val()));
        initMap();
        google.maps.event.addDomListener(window, 'load');
    }
}

function initSettings() {
    //$('#dateJointFrom').val(dateFormat((new Date()).setDate(1),"yyyy-mm-dd"));
    //$('#dateJointTo').val(dateFormat(new Date(), "yyyy-mm-dd"));

    $('#checkboxUnverified').click(function () {
        var temp = '';
        if ($(this).is(":checked")) {
            temp = $('#status').val().includes('0,') ? $('#status').val() : '0,'+$('#status').val();
        } else {
            temp = $('#status').val().replace('0,', '')
        }
        $('#status').val(temp);
    });
    $('#checkboxPass').click(function () {
        var temp = '';
        if ($(this).is(":checked")) {
            temp = $('#status').val().includes('1,') ? $('#status').val() : '1,'+$('#status').val();
        } else {
            temp = $('#status').val().replace('1,', '')
        }
        $('#status').val(temp);
    });
    $('#checkboxFail').click(function () {
        var temp = '';
        if ($(this).is(":checked")) {
            temp = $('#status').val().includes('2,') ? $('#status').val() : '2,'+$('#status').val();
        } else {
            temp = $('#status').val().replace('2,', '')
        }
        $('#status').val(temp);
    });
}

function getDataMapCluster(status, user, uploadFrom, uploadTo) {
    var tmp;
    var param = "";
    if (status !== null && status !== undefined)
        param += "&status=" + status;
    if (user !== null && user !== undefined)
        param += "&user=" + user;
    if (uploadFrom !== null && uploadFrom !== undefined) {
        param += "&uploadFrom=" + uploadFrom;
        if (uploadTo !== null && uploadTo !== undefined)
            param += "&uploadTo=" + uploadTo;
    }
    $.ajax({
        url: "?dataMapClusterer=" + param,
        async: false,
        success: function (data) {
            tmp = data;
        }
    });
    return tmp;
}

function initMap() {
    var markerImage = new google.maps.MarkerImage(imageUrl, new google.maps.Size(24, 24));
    var center = new google.maps.LatLng(-0.364089, 119.607828);
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 5,
        grid: 10,
        center: center,
        scaleControl: true,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    var markers = [];
    var arr = [];
    var infowindow = new google.maps.InfoWindow({content:''});
    for (var i = 0; i < dataMapClusterer.count; i++) {

        var dataContent = dataMapClusterer.content[i];
        var latLng = new google.maps.LatLng(dataContent.latitude, dataContent.longitude);

        //Check Markers array for duplicate position and offset a little
//        if(markers.length != 0) {
//            for (i=0; i < markers.length; i++) {
//                var existingMarker = markers[i];
//                var pos = existingMarker.getPosition();
//                if (pos.lat().toFixed(5)===latLng.lat().toFixed(5) && pos.lng().toFixed(4)===latLng.lng().toFixed(4)){
//                    var a = 360.0 / markers.length;
//                    var newLat = pos.lat() + -.00003 * Math.cos((+a*i) / 180 * Math.PI);  //x
//                    var newLng = pos.lng() + -.00003 * Math.sin((+a*i) / 180 * Math.PI);  //Y
//                    latLng = new google.maps.LatLng(newLat,newLng);
//                }
//            }
//        }

        var pos = latLng.lat() + "#" + latLng.lng();
        if( arr[pos] === undefined ) {
            arr[pos] = " ";
        } else {
            var a = 360.0 / markers.length;
            var newLat = latLng.lat() + -.00003 * Math.cos((+a*markers.length) / 180 * Math.PI);  //x
            var newLng = latLng.lng() + -.00003 * Math.sin((+a*markers.length) / 180 * Math.PI);  //Y
            latLng = new google.maps.LatLng(newLat,newLng);
        }

        var images = '';
        for (var j = 0; j<dataContent.images.length; j++){
            images = images + '<img width=\'200\' height=\'200\' src=\''+dataContent.images[j].url+'\'/>';
        }
        var marker = new google.maps.Marker({
            map: map,
            position: latLng,
            draggable: false,
            icon: markerImage,
            title: 'Metadata: </b><br/>' + images
                + '<br/>Details: <b>' + (dataContent.metadata!==null ? dataContent.metadata : '')
                + '</b><br/>Uploader: <b>' + (dataContent.user!==null ? dataContent.user.fullName : '')
                + '</b><br/>Uploaded: <b>' + (dataContent.createAt!==null ? dateFormat(dataContent.createAt,"yyyy-mm-dd HH:MM:ss") : '')
                + '</b>'
        });

        google.maps.event.addListener(marker, 'click', function(){
            infowindow.setContent(this.title);
            infowindow.open(map, this);
        });
        markers.push(marker);
    }
    var markerCluster = new MarkerClusterer(map, markers, {imagePath: 'img/m', maxZoom: 20});
}
