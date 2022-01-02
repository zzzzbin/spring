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
        timeout: 5000,
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
    })
});

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
                    var url = '<a href="/user/detail/'+data+'">Detail</a>';
                    return url;
                }
            },
        ]
    });
}

