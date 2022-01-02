'use strict';
var userData = null;
var table = null;

function search() {
    var formData = $('#user-search-form').serialize();
    $.ajax({
        type: 'GET',
        url: '/user/get/list',
        data: formData,
        dataType: 'json',
        contentType: 'application/json; charset=UTF-8',
        cache: false,
        timeout: 15000,
    }).done(function (data) {
        //ajax success
        console.log(data);
        userData = data;

        createDataTables();
    }).fail(function (jqXHR, textStatus, errorThrown) {
        //ajax failed
        alert('Search process failed');
    }).always(function () {
        //Process to always execute
    });
}

$(function () {
    createDataTables();
    $('#btn-search').click(function () {
        search();
    });

    /** Event when the download button (JavaScript) is pressed. */
    $('#download-java-script').click(function () { // Cancel normal action event.preventDefault();
        var xhr = new XMLHttpRequest();
        // Request destination
        xhr.open('POST', '/user/list/download', true);
        // Response type
        xhr.responseType = 'blob';
        // Define processing when request is successful
        xhr.onload = function (e) {
            // File name
            var fileName = 'userListJavaScript.csv';
            // Calling a file save function
            fileSave(this.response, fileName);
        };
        // CSRF measures
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";

        xhr.setRequestHeader(header, token);
        // Request execution
        xhr.send();
    });

    /** Event when the download button (jQuery) is pressed. */
    $('#download-jquery').click(function (e) {
        // Cancel normal action
        e.preventDefault();
        // Get form value (CSRF measures)
        var formData = $('#download-form').serializeArray();
        // File download with ajax
        $.ajax({
            type: 'post',
            url: '/user/list/download',
            data: formData,
            xhrFields: {
                responseType: 'blob'
            },
        })
            // Processing when ajax is successful
            .done(function (data, status, jqXHR) {
                // File name
                var fileName = 'userListJQuery.csv';
                // Blob creation
                const blob = new Blob([data], {type: data.type});
                // Calling a file save function
                fileSave(data, fileName);
            })
            // Processing when ajax fails
            .fail(function (jqXHR, status, errorThrown) {
                alert('File download failure');
            })
            // Processing to be executed regardless of success or failure
            .always(function (data, status, errorThrown) { // None
            })
    });

    /** Event when the download button (zip) is pressed. */
    $('#download-zip').click(function(e) {
        // Cancel normal action
        e.preventDefault();
        // Get form value (CSRF measures)
        var formData = $('#download-form').serializeArray();
        // File download with ajax
        $.ajax({
            type: 'post',
            url: '/user/list/download/zip',
            data: formData,
            xhrFields:{
                responseType: 'blob'
            },
        })
            // Processing when ajax is successful
            .done(function( data, status, jqXHR ) { // File name
                var fileName = 'sample.zip'; // Blob creation
                const blob = new Blob([data], {type: data.type});
                // Calling a file save function
                fileSave(data, fileName);
            })
                 // Processing when ajax fails
            .fail(function( jqXHR, status, errorThrown ) {
                alert('File download failure');
            })
            // Processing to be executed regardless of success or failure
            .always(function( data, status, errorThrown ) {
                // None
            })

    });
});

/** File save function. */
function fileSave(blob, fileName) {
// Separate processing depending on whether it is IE
    if (window.navigator.msSaveBlob) {
        // In the case of IE
        window.navigator.msSaveBlob(blob, fileName);
    } else {
        // In the case of non-IE
        // a tag generation
        var a = document.createElement('a');
        // Blob object & URL generation from response
        var blobUrl = window.URL.createObjectURL(blob);
        // Add the a tag generated above to HTML
        document.body.appendChild(a);
        a.style = 'display: none';
        // Set the URL of the Blob object
        a.href = blobUrl;
        // Generate file name to download
        a.download = fileName;
        // Click event firing
        a.click();
    }
}

    function createDataTables() {
        //if datatables has already been created
        if (table !== null) {
            table.destroy();
        }

        //create datatables
        table = $('#user-list-table').DataTable({
            //display dta
            data: userData,
            //data and column mapping
            columns: [
                {data: 'userId'},
                {data: 'userName'},
                {
                    data: 'birthday',
                    render: function (data, type, row) {
                        var date = new Date(data);
                        var year = date.getFullYear();
                        var month = date.getMonth() + 1;
                        var date = date.getDate();
                        return date + '/' + month + '/' + year;
                    }
                },
                {data: 'age'},
                {
                    data: 'gender',
                    render: function (data, type, row) {
                        var gender = '';
                        if (data === 1) {
                            gender = 'Male';
                        } else {
                            gender = 'Female';
                        }
                        return gender;
                    }
                },
                {
                    data: 'userId',
                    render: function (data, type, row) {
                        var url = '<a href="/user/detail/' + data + '">Detail</a>';
                        return url;
                    }
                },
            ]
        });
    }

