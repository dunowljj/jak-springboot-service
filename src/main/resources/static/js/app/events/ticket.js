

var reserve_main = {
    init : function () {
        var _this = this;

        $('#btn-ticket-reserve').on('click', function () {
            _this.save();
        });

        $('.btn-ticket-cancel').on('click', function () {
            var id = $(this).next().val();
            _this.cancel(id);
        })
    },
    save: function () {
        var data = {
            eventId: $('#eventId').val(),
            price: $('#price').val(),
            amount: $('#amount').val(),
            reservationDate: $('#reservationDate').val()
        };

        $.ajax({
            type: 'PUT',
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

    cancel: function (id) {

        $.ajax({
            type: 'DELETE',
            url: '/api/events/ticket/' + id + '/reserve',
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

var pay_main = {
    init : function () {
        var _this = this;

        $('#btn-ticket-pay').on('click', function () {
            _this.save();
        })
        $('.btn-ticket-refund').on('click', function () {
            var id = $(this).next().val();
            console.log('외안되?');
            console.log(id);
            _this.refund(id);
        });
    },
    save: function () {

        var data = {
            reservationId: $('#reservationId').val(),
            total: $('#total').val(),
            payGroup: $('#payGroup').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/events/ticket/pay',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사가 결제되었습니다.');
            window.location.href = '/events/ticket/reserveList';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    refund: function (id) {
        $.ajax({
            type: 'PUT',
            url: '/api/events/ticket/' + id + '/pay',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('행사가 환불 처리 되었습니다.');
            window.location.href = '/events/ticket/payList';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}


reserve_main.init();
pay_main.init();



