/* global Enumerable, toastr, fecthData, alasql */

var countForQuery = [];
var consWaitForQuery = 8;

var arrTable=[];
var columns=[];
var table;

$(document).ready(function () {
    $("body").toggleClass("mini-navbar");
    SmoothlyMenu();
    waitForQuery=consWaitForQuery;
    /* Initiate variables */
    (function fecthData() {
        /* Don't refresh the pages if query still working */
        for (var i=countForQuery.length-1; i>=0; i--) waitForQuery+=countForQuery[i];
        if (doesConnectionExist&&waitForQuery===consWaitForQuery) {
            setDashboard();
            console.log("Conn. OK: " + dateFormat((new Date()),"yyyy-mm-dd HH:MM:ss"));
        }
        waitForQuery=0;
        setTimeout(fecthData, 300000);
    }());

});

function setDashboard(){

    refreshData();
}

function refreshData(){

    var table = $('.list-user').DataTable({
        ajax: {
            type : "GET",
            url : "?dataUsers",
            dataSrc: function ( json ) {
                //Make your callback here.
                return json;
            }
        },
        pageLength: 25,
        columns: [
            { "data": "id", "title" : "id", "targets": 0,  "visible": false },
            { "data": "username", "title" : "Username", "targets": 1,  "width": "5%" },
            { "data": "fullName", "title" : "Name", "targets": 2,  "width": "10%" },
            { "data": "gender", "title" : "Gender", "targets": 3,  "width": "5%" },
            { "data": "address", "title" : "Address", "targets": 4,  "width": "10%" },
            { "data": "postalCode", "title" : "Zip code", "targets": 5,  "width": "5%" },
            { "data": "email", "title" : "Email", "targets": 6,  "width": "10%" },
            { "data": "profession", "title" : "Job", "targets": 7,  "width": "10%" },
            { "data": "education", "title" : "Education", "targets": 8,  "width": "5%" },
            { "data": "status", "title" : "Status", "targets": 9,  "width": "5%" },
            { "data": "createAt",  "title" : "Create At", "targets": 10, "width": "10%",
                "render": function (data, type, row, meta) {
                    if (type === 'display') {
                        data = formatDate(data);
                    }
                    return data;
                }
            }
        ],
        order: [[10, 'desc']],
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'csv'},
            {extend: 'excel'}],
    });
}

function formatDate(data) {
    if (data!=null){
        var date = new Date(data);
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var ampm = hours >= 12 ? 'pm' : 'am';
        hours = hours % 12;
        hours = hours ? hours : 12; // the hour '0' should be '12'
        minutes = minutes < 10 ? '0'+minutes : minutes;
        var strTime = hours + ':' + minutes + ' ' + ampm;
        return date.getDate() + "-" + (date.getMonth()+1) + "-" + date.getFullYear() + "  " + strTime;
    }else{
        return "";
    }
}

function doesConnectionExist() {
    var xhr = new XMLHttpRequest();
    var file = "img/dot.gif"; //image path in your project
    var randomNum = Math.round(Math.random() * 10000);
    xhr.open('HEAD', file + "?rand=" + randomNum, false);
    try {
        xhr.send(null);
        if (xhr.status >= 200 && xhr.status < 304) {
            return true;
        } else {
            return false;
        }
    } catch (e) {
        return false;
    }
}
