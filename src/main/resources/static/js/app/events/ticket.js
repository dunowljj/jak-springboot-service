var main = {
    init : function () {
        var _this = this;
        $('#btn-ticket-reserve').on('click', function () {
            _this.save();
        });
    },
    save: function () {
        var data = {
            eventId: $('#eventId').val(),
            price: $('#price').val(),
            amount: $('#amount').val(),
            reservationDate: $('#reservationDate').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/events/ticket/reserve',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사가 예약되었습니다.');
            window.location.href = '/events/ticket/list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

main.init();