// todo : js파일 분리 방식 고민해보기
var event_main = {
    init: function () {
        var _this = this;
        $('#btn-event-save').on('click', function () {
            _this.save();
        });
    },
    save: function () {
        var data = {
            hallId: $('#hallId').val(),
            ticketReservations: $('#ticketReservations').val(),
            eventRegistrations: $('#eventRegistrations').val(),
            name: $('#name').val(),
            detail: $('#detail').val(),
            price: $('#price').val(),
            recruitAmount: $('#recruitAmount').val(),
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
            recruitStartDate: $('#recruitStartDate').val(),
            recruitEndDate: $('#recruitEndDate').val(),
            field: $('#field').val(),
            hitCount: $('#hitCount').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/events/event/new',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사가 등록되었습니다.');
            window.location.href = '/events/event';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

var hall_main = {
    init : function () {
        var _this = this;
        $('#btn-hall-save').on('click', function () {
            _this.save();
        });
    },
    save: function () {
        var data = {
            name: $('#name').val(),
            capacity: $('#capacity').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/events/hall/new',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사장이 등록되었습니다.');
            window.location.href = '/events/hall';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

var ticket_main = {
    init : function () {
        var _this = this;
        $('#btn-hall-save').on('click', function () {
            _this.saveHall();
        });
    },
    save: function () {
        var data = {
            name: $('#name').val(),
            capacity: $('#capacity').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/events/hall/new',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사장이 등록되었습니다.');
            window.location.href = '/events.hall';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

event_main.init();
hall_main.init();
ticket_main.init();