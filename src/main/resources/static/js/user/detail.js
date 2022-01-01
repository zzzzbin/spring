'use strict';
// Processing when loading the screen
$(function (){
    function updateUser() {
        //get the value of the form
        var formData = $('#user-detail-form').serializeArray();

        //ajax communication
        $.ajax({
            type:'PUT',
            cache: false,
            url: '/user/update',
            data: formData,
            dataType: 'json'
        }).done(function (data){
            //ajax success
            alert('Updated user');

            //redirect to user list screen
            window.location.href= '/user/list';
        }).fail(function(jqXHR, textStatus, errorThrown){
            //ajax failed
            alert('Failed to update user');
        }).always(function () {
            //Process to always execute
        });
    }

    //Update button processing
    $('#btn-update').click(function (){
        updateUser();
    })

    function deleteUser() {
        //get the value of the form
        var formData = $('#user-detail-form').serializeArray();

        //ajax communication
        $.ajax({
            type:'DELETE',
            cache: false,
            url: '/user/delete',
            data: formData,
            dataType: 'json'
        }).done(function (data){
            //ajax success
            alert('Deleted user');

            //redirect to user list screen
            window.location.href= '/user/list';
        }).fail(function(jqXHR, textStatus, errorThrown){
            //ajax failed
            alert('Failed to delete user');
        }).always(function () {
            //Process to always execute
        });
    }

    $('#btn-delete').click(function (){
        deleteUser();
    })
})