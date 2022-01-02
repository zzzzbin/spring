'use strict';
jQuery(function ($) {
    $('#btn-signup').click(function () {
        signupUser();
    });
});

function removeValiateResult() {
    $('.is-invalid').removeClass('is-invalid');
    $('.invalid-feedback').remove();
    $('.text-danger').remove();
}

/** Reflection of the validation result */
function reflectValidResult(key, value) {
    // Add error message
    if (key === 'gender') { // For gender fields
        // Apply CSS
        $('input[name=' + key + ']').addClass('is-invalid');
        // Add error message
        $('input[name=' + key + ']')
            .parent().parent()
            .append('<div class="text-danger">' + value + '</div>');
    } else { // For fields other than gender
        // Apply CSS
        $('input[id=' + key + ']').addClass('is-invalid');
        // Add error message
        $('input[id=' + key + ']')
            .after('<div class="invalid-feedback">' + value + '</div>');
    }

}

function signupUser() {
    //clear the validation result
    removeValiateResult();

    //get the value of the form
    var formData = $('#signup-form').serializeArray();
    $.ajax({
        type: 'POST',
        cache: false,
        url: '/user/signup/rest',
        data: formData,
        dataType: 'json'
    }).done(function (data) {
        //ajax success
        console.log(data);
        if (data.result === 90) {
            //when a validation error occur
            $.each(data.errors, function (key, value) {
                reflectValidResult(key, value);
            })
        } else if (data.result === 0) {
            alert('Signed up user');
            window.location.href = '/login';
        }

    }).fail(function (jqXHR, textStatus, errorThrown) {
        //ajax failed
        alert('User signup failed');
    }).always(function () {
        //Process to always execute
    });
}