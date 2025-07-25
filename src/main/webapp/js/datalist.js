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

    var table = $('.data-list').DataTable({
        ajax: {
            type : "GET",
            url : "?data",
            dataSrc: function ( json ) {
                //Make your callback here.
                return json;
            }
        },
        pageLength: 25,
        columns: [
            { "data": "id", "title" : "id", "targets": 0,  "visible": false },
            { "data": "no", "title" : "No", "targets": 1,  "width": "2%" },
            { "data": "startDate",  "title" : "Create at", "targets": 2, "width": "10%",
                "render": function (data, type, row, meta) {
                    if (type === 'display') {
                        data = formatDate(data);
                    }
                    return data;
                }
            },
            { "data": "finishDate",  "title" : "Finish at", "targets": 3, "width": "10%",
                "render": function (data, type, row, meta) {
                    if (type === 'display') {
                        data = formatDate(data);
                    }
                    return data;
                }
            },
            { "data": "createAt",  "title" : "Uploaded", "targets": 4, "width": "10%",
                "render": function (data, type, row, meta) {
                    if (type === 'display') {
                        data = formatDate(data);
                    }
                    return data;
                }
            },
            { "data": "user.fullName", "title" : "Uploader", "targets": 5, "width": "10%" },
            { "data": "latitude", "title" : "Latitude", "targets": 6, "width": "3%" },
            { "data": "longitude", "title" : "Longitude", "targets": 7, "width": "3%" },
            { "data": "imagePath",  "title" : "Image", "targets": 8, "width": "5%",
                "render": function (data, type, row, meta) {
                    if (type === 'display') {
                        data = '<a href="' + data + '">Link</a>';
                    }
                    return data;
                }
            },
            {
                "data": "status",
                "title": "Verification",
                "targets":9,
                "orderable": true,
                "width": "15%",
                "className": "text-right",
                "render": function (data, row, type, meta) {
                    if (data == '0') {
                        data = '<div class=\"btn-group btn-group-toggle\" data-toggle=\"buttons\"> ' +
                            '<label class=\"btn btn-warning active\" id=\"unknown\">Unknown</label> ' +
                            '<label class=\"btn\" id=\"pass\">Pass</label> ' +
                            '<label class=\"btn\" id=\"fail\">Fail</label> </div>';
                    }
                    else if (data == '1') {
                        data = '<div class=\"btn-group btn-group-toggle\" data-toggle=\"buttons\"> ' +
                            '<label class=\"btn\" id=\"unknown\">Unknown</label> ' +
                            '<label class=\"btn btn-success active\" id=\"pass\">Pass</label> ' +
                            '<label class=\"btn\" id=\"fail\">Fail</label> </div>';
                    }
                    else if (data == '2') {
                        data = '<div class=\"btn-group btn-group-toggle\" data-toggle=\"buttons\"> ' +
                            '<label class=\"btn\" id=\"unknown\">Unknown</label> ' +
                            '<label class=\"btn\" id=\"pass\">Pass</label> ' +
                            '<label class=\"btn btn-danger active\" id=\"fail\">Fail</label> </div>';
                        }

                        return data;
                    }
            },
            { "data": "verificator", "title" : "Verificator", "targets": 10, "width": "5%" },
            { "data": "verificationTime",  "title" : "Verification Time", "targets": 11, "width": "5%",
                "render": function (data, type, row, meta) {
                    if (type === 'display') {
                        data = formatDate(data);
                    }
                    return data;
                }
            }
        ],
        order: [[4, 'desc']],
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            {extend: 'copy'},
            {extend: 'csv'},
            {extend: 'excel'}],
    });

    $('.data-list tbody').on('click', '#pass', function () {
        var data = table.row($(this).parents('tr')).data();
        $.ajax({
            url: "?verify&status=1&id="+data.id,
            dataType: "json",
            async: false,
            success: function (tmp) {
                table.ajax.reload();
            },
            timeout: 5000
        });
    });

    $('.data-list tbody').on('click', '#fail', function () {
        var data = table.row($(this).parents('tr')).data();
        $.ajax({
            url: "?verify&status=2&id="+data.id,
            dataType: "json",
            async: false,
            success: function (tmp) {
                table.ajax.reload();
            },
            timeout: 5000
        });
    });
    $('.data-list tbody').on('click', '#yes', function () {
        var data = table.row($(this).parents('tr')).data();
        $.ajax({
            url: "?indicate&habs=1&id="+data.id,
            dataType: "json",
            async: false,
            success: function (tmp) {
                table.ajax.reload();
            },
            timeout: 5000
        });
    });

    $('.data-list tbody').on('click', '#no', function () {
        var data = table.row($(this).parents('tr')).data();
        $.ajax({
            url: "?indicate&habs=2&id="+data.id,
            dataType: "json",
            async: false,
            success: function (tmp) {
                table.ajax.reload();
            },
            timeout: 5000
        });
    });
    $('.data-list tbody').on('click', '#unsure', function () {
        var data = table.row($(this).parents('tr')).data();
        $.ajax({
            url: "?indicate&habs=0&id="+data.id,
            dataType: "json",
            async: false,
            success: function (tmp) {
                table.ajax.reload();
            },
            timeout: 5000
        });
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
