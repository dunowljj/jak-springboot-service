var main = {
    init : function () {
        var _this = this;

        $('#btn-ticket-reserve').on('click', function () {
            _this.save();
        });

        $('#btn-ticket-cancelReservation').on('click', function () {
            _this.delete();
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
    },

    delete : function () {
        var id = $('#id').val();

        console.log(id + 'ddfdsfdfdsfasd왜안돼');

        $.ajax({
            type: 'DELETE',
            url: '/api/events/ticket/' + id + '/ticket-reserve',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('예약이 취소되었습니다.');
            window.location.href = '/events/ticket/reserveList';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

main.init();