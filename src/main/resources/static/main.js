$(function() {
    activateSubscriptionButton();
})

function activateSubscriptionButton() {

   $('#subscribe').click(function() {
        var surveyRequest = buildSurveyRequestObject();

        $.post({url: '/api/v1/surveys/subscribe',
            data: JSON.stringify(surveyRequest),
            dataType: "text",
            contentType: "application/json"
        }).success(function(response) {
            alert(response);
            $('#success-message').show();
        }).fail(function (response) {
            console.log(response);
        })
    })
}

function buildSurveyRequestObject() {

    var providerId = $('#provider').val()
    var frequency =  $('#subscription').val();
    var subject = $('#subject').val() === "" ? null : $('#subject').val();
    var minAge = $('#minAge').val() === undefined ? null : parseInt($('#minAge').val());
    var maxAge = $('#maxAge').val() === undefined ? null : parseInt($('#maxAge').val());
    var country = $('#country').val() === "" ? null : $('#country').val();

    return {
        'provider': { id: providerId},
        'requester': { id: 1},
        'subscription': {
            'frequency': frequency,
            'channels': ["email", "api", "postal", "ftp"]
        },
        'queries': {
            'country': country,
            'subject': subject,
            'minAge': minAge,
            'maxAge': maxAge
        }
    };
}
