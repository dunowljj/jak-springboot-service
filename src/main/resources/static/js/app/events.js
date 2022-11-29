// todo : js파일 분리 방식 고민해보기
var event_main = {
    init: function () {
        var _this = this;
        $('#btn-event-save').on('click', function () {
            _this.save();
        });

        $('#btn-event-update').on('click', function () {
            _this.update();
        });

        $('#btn-event-delete').on('click', function () {
            _this.delete();
        });
    },

    save: function () {
        var data = {
            hallId: $('#hallId').val(),
            name: $('#name').val(),
            detail: $('#detail').val(),
            price: $('#price').val(),
            recruitAmount: $('#recruitAmount').val(),
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
            recruitStartDate: $('#recruitStartDate').val(),
            recruitEndDate: $('#recruitEndDate').val(),
            field: $('#field').val(),
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
    },

    update : function () {
        var data = {
            name: $('#name').val(),
            hallId: $('#hallId').val(),
            field: $('#field').val(),
            detail: $('#detail').val(),
            price: $('#price').val(),
            recruitAmount: $('#recruitAmount').val(),
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
            recruitStartDate: $('#recruitStartDate').val(),
            recruitEndDate: $('#recruitEndDate').val(),
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/events/event/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사 정보가 수정되었습니다.');
            window.location.href = '/events/event/list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/events/event/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('행사 정보가 삭제되었습니다.');
            window.location.href = '/events/event/list';
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
        }),

        $('#btn-hall-update').on('click', function () {
            _this.update();
        });

        $('#btn-hall-delete').on('click', function () {
            _this.delete();
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
            alert('행사장 정보가 등록되었습니다.');
            window.location.href = '/events/hall';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update : function () {
        var data = {
            name: $('#name').val(),
            capacity: $('#capacity').val(),
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/events/hall/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사장 정보가 수정되었습니다.');
            window.location.href = '/events/hall/list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/events/hall/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('행사장 정보가 삭제되었습니다.');
            window.location.href = '/events/hall/list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

var ticket_main = {
    init : function () {
        var _this = this;
        $('#btn-ticket-save').on('click', function () {
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
            url: '/api/events/ticket/new',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사장이 등록되었습니다.');
            window.location.href = '/events/ticket';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

event_main.init();
hall_main.init();
ticket_main.init();