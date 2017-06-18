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
    var channels = [];
    $('input:checked').each(function(){
        channels.push($(this).val());
    });
    var subject = $('#subject').val() === "" ? null : $('#subject').val();
    var country = $('#country').val() === "" ? null : $('#country').val();
    var minAge = $('#minAge').val() === undefined ? null : parseInt($('#minAge').val());
    var maxAge = $('#maxAge').val() === undefined ? null : parseInt($('#maxAge').val());

    return {
        'provider': { id: providerId},
        'requester': { id: 1},
        'subscription': {
            'frequency': frequency,
            'channels': channels
        },
        'survey': {
            'subject': subject,
            'country': country,
            'minAge': minAge,
            'maxAge': maxAge
        }
    };
}
